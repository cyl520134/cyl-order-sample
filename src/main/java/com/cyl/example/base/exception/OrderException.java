package com.cyl.example.base.exception;


import com.cyl.example.base.ResCode;

public class OrderException extends RuntimeException {

	private static final long serialVersionUID = -4616830502974081779L;

	public ResCode res = ResCode.SUCCESS;

	private Object obj;

	public OrderException(String message, Throwable cause) {
		super(message, cause);
	}

	public OrderException(ResCode res) {
		super(res.getMsg());
		this.res = res;
	}
	public OrderException(String code, String msg) {
		super(msg);
		this.res.setCode(code);
		this.res.setMsg(msg);
	}

	public OrderException(Object obj) {
		this.res = res;
		this.obj = obj;
	}

	public String getCode(){
		return this.res.getCode();
	}

	public Object getObj() {
		return obj;
	}

	public OrderException setObj(Object obj) {
		this.obj = obj;
		return this;
	}
}

