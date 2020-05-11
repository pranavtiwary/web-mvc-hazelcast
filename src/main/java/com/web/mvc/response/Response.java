package com.web.mvc.response;

public class Response {
	private Boolean isSuccessfull;
	private String message;
	
	public Response(boolean b, String msg) {
		this.isSuccessfull=b;
		this.message=msg;
	}
	public Boolean getIsSuccessfull() {
		return isSuccessfull;
	}
	public void setIsSuccessfull(Boolean isSuccessfull) {
		this.isSuccessfull = isSuccessfull;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "Response [isSuccessfull=" + isSuccessfull + ", message=" + message + "]";
	}
}
