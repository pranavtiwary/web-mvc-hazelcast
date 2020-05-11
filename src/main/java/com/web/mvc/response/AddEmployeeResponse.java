package com.web.mvc.response;

public class AddEmployeeResponse extends Response {
	private Long employeeId;

	public AddEmployeeResponse(boolean b, String msg) {
		super(b, msg);
	}

	public AddEmployeeResponse(Long id, boolean b, String msg) {
		super(b, msg);
		this.employeeId=id;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	@Override
	public String toString() {
		return "AddEmployeeResponse [employeeId=" + employeeId + ", toString()=" + super.toString() + "]";
	}
	
}
