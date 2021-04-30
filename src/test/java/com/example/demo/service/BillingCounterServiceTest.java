package com.example.demo.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.billingcounter.demo.data.ItemizedBill;
import com.billingcounter.demo.data.ProductDetails;
import com.billingcounter.demo.data.PurchasedData;
import com.billingcounter.demo.data.PurchasedItems;
import com.billingcounter.demo.data.TotalBill;

@RunWith(MockitoJUnitRunner.class)
public class BillingCounterServiceTest {

	private static final String PRDCT_CDE = "F1";
	private static final String QUANTITY = "5";
	private static final Long ID = 1L;
	private static final Float TAX = 10.0F;
	private static final String CATGRY = "A";
	private static final String PRDCT_NAME = "OIL"; 
	private static final Float PRDCT_RATE = 170.0F;
	private static final Float PRDCT_COST = 935.0F; 
	 
	@InjectMocks 
	BillingCounterService billingCounterService;
	
	@Mock
	ProductRepository productRepository;
	
	@Test
	public void testGetItemizedBill() {
		PurchasedItems items = getPurchasedItems();
		Product product = getProduct(); 
		TotalBill totalBill = getTotalBill();
		Mockito.when(productRepository.findProductByProductCode(PRDCT_CDE)).thenReturn(product);
		assertEquals(totalBill.getTotal(), billingCounterService.getItemizedBill(items).getTotal());
	}
	
	@Test(expected = ProductDoesNotExistException.class)
	public void testGetItemizedBillProductDoesNotExistException() {
		PurchasedItems items = getPurchasedItems();
		Mockito.when(productRepository.findProductByProductCode(PRDCT_CDE)).thenReturn(null);
		billingCounterService.getItemizedBill(items);
	} 
	
	private TotalBill getTotalBill() {
		List<ItemizedBill> itemizedBill = getItemizedBill();
		TotalBill bill = new TotalBill();
		bill.setItemizedBill(itemizedBill);
		bill.setTotal(PRDCT_COST);
		return bill;
	}

	private PurchasedItems getPurchasedItems() {
		PurchasedData data = new PurchasedData();
		data.setProductCode(PRDCT_CDE);
		data.setQuantity(QUANTITY);
		List<PurchasedData> dataList = new ArrayList<>();
		dataList.add(data);
		PurchasedItems items = new PurchasedItems();
		items.setPurchasedDataList(dataList);
		return items;
	}

	private Product getProduct() {
		Product product = new Product();
		product.setId(ID);
		product.setProductCode(PRDCT_CDE);
		product.setProductName(PRDCT_NAME);
		product.setProductRate(PRDCT_RATE);
		Category category = new Category();
		category.setId(ID);
		category.setCategory(CATGRY);
		category.setTax(TAX);
		product.setCategory(category);
		return product;
	}


	private List<ItemizedBill> getItemizedBill() {
		ProductDetails productDetails = new ProductDetails();
		productDetails.setCategory(CATGRY);
		productDetails.setProductCode(PRDCT_CDE);
		productDetails.setProductName(PRDCT_NAME);
		productDetails.setRate(PRDCT_RATE);
		productDetails.setSalesTaxPercentage(TAX);
		ItemizedBill itemizedBill = new ItemizedBill();
		itemizedBill.setCost(PRDCT_COST);
		itemizedBill.setProductBase(productDetails);
		List<ItemizedBill> itemizedBills = new ArrayList<>();
		itemizedBills.add(itemizedBill);
		return itemizedBills;
	}
}
