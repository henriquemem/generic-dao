package br.com.generic.dao.exception;

import br.com.generic.exceptions.BaseRuntimeException;

public class NotPossibleCreateEntityException extends BaseRuntimeException {

	private static final long serialVersionUID = 9041284665225950372L;

	public NotPossibleCreateEntityException() {
		super();
		System.out.println();
	}

	public NotPossibleCreateEntityException(String message, Throwable cause,
											boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NotPossibleCreateEntityException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotPossibleCreateEntityException(String message) {
		super(message);
	}

	public NotPossibleCreateEntityException(Throwable cause) {
		super(cause);
	}
	
}
