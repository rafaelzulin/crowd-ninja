package crowdcenter.ws.types;

public enum ResultCode {
	SUCCESS("success"),
	FAILURE("failure");
	
	String value;
	
	ResultCode(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}