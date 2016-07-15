package crowdcenter.ws.types;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "result")
public class Result implements Serializable {

	private static final long serialVersionUID = 1L;
	private String code;
	private String message;
	private Object body;
	
	public Result() {
		super();
	}
	
	public Result(ResultCode resultCode, Object body) {
		this(resultCode, new String(), body);
	}
	
	public Result(ResultCode resultCode, String message) {
		this(resultCode, message, null);
	}

	public Result(ResultCode resultCode, String message, Object body) {
		this.code = resultCode.getValue();
		this.message = message;
		this.body = body;
	}
	
	public String getCode() {
		return code;
	}
	
	@XmlElement
	public void setCode(String resultCode) {
		this.code = resultCode;
	}

	public String getMessage() {
		return message;
	}

	@XmlElement
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Object getBody() {
		return body;
	}
	
	@XmlElement
	public void setBody(Object body) {
		this.body = body;
	}
}
