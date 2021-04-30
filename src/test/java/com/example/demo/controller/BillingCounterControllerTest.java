package com.example.demo.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.billingcounter.demo.component.ProductDetailsValidator;
import com.billingcounter.demo.data.PurchasedItems;
import com.billingcounter.demo.data.TotalBill;
import com.recipe.demo.util.Constants;

@RunWith(MockitoJUnitRunner.class)
public class BillingCounterControllerTest {

	@InjectMocks
	BillingCounterController billingCounterController;
	
	@Mock 
	ProductDetailsValidator productDetailsValidator;
	
	@Mock
	BillingCounterService billingCounterService;
	
	@Test
	public void testGetItemizedBill() {
		PurchasedItems items = Mockito.mock(PurchasedItems.class);
		TotalBill totalBill = Mockito.mock(TotalBill.class);
		Mockito.doNothing().when(productDetailsValidator).validateInput(items);
		Mockito.when(billingCounterService.getItemizedBill(items)).thenReturn(totalBill);
		assertEquals(new ResponseEntity<>(totalBill, HttpStatus.OK), billingCounterController.getItemizedBill(items));
	}
	
	@Test(expected = ProductDoesNotExistException.class)
	public void testGetItemizedBillForProductDoesNotExistException() {
		PurchasedItems items = Mockito.mock(PurchasedItems.class);
		Mockito.doNothing().when(productDetailsValidator).validateInput(items);
		Mockito.when(billingCounterService.getItemizedBill(items)).thenThrow(new ProductDoesNotExistException(Constants.PRODCT_DOES_NOT_EXIST_MSG));
		billingCounterController.getItemizedBill(items);
	}
	
	@Test(expected = PurchasedDataNotFoundException.class)
	public void testGetItemizedBillForPurchsdDataNotFoundException() {
		PurchasedItems items = Mockito.mock(PurchasedItems.class);
		Mockito.doThrow(new PurchasedDataNotFoundException(Constants.PURCHSD_DATA_NOT_FOUND_MSG)).when(productDetailsValidator).validateInput(items);
		billingCounterController.getItemizedBill(items);
	}
	
	@Test(expected = ProductNotFoundException.class)
	public void testGetItemizedBillForProductNotFoundException() {
		PurchasedItems items = Mockito.mock(PurchasedItems.class);
		Mockito.doThrow(new ProductNotFoundException(Constants.PRODCT_CDE_EMPTY_MSG)).when(productDetailsValidator).validateInput(items);
		billingCounterController.getItemizedBill(items);
	}
	
	@Test(expected = QuantityNotFoundException.class)
	public void testGetItemizedBillForQuantityNotFoundException() {
		PurchasedItems items = Mockito.mock(PurchasedItems.class);
		Mockito.doThrow(new QuantityNotFoundException(Constants.QUANTITY_NOT_FOUND_MSG)).when(productDetailsValidator).validateInput(items);
		billingCounterController.getItemizedBill(items);
	}
	
	
	
	
}
