package br.com.generic.dao.exception;

import br.com.generic.exceptions.BaseRuntimeException;



public class NotedInvokeMethodException extends BaseRuntimeException {

	private static final long serialVersionUID = 5686907094471799832L;

	public NotedInvokeMethodException() {
		super();
	}

	public NotedInvokeMethodException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NotedInvokeMethodException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotedInvokeMethodException(String message) {
		super(message);
	}

	public NotedInvokeMethodException(Throwable cause) {
		super(cause);
	}

	
}
