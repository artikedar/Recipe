package com.recipe.demo.error;

public class ErrorDetails {

	private String source;
	private Integer reasoncode;
	private String description;
	private boolean recoverable;
	
	public ErrorDetails(String source, Integer reasoncode, String description, boolean recoverable) {
		super();
		this.source = source;
		this.reasoncode = reasoncode;
		this.description = description;
		this.recoverable = recoverable;
	}
	
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public Integer getReasoncode() {
		return reasoncode;
	}
	public void setReasoncode(Integer reasoncode) {
		this.reasoncode = reasoncode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isRecoverable() {
		return recoverable;
	}
	public void setRecoverable(boolean recoverable) {
		this.recoverable = recoverable;
	}
	
	
}
