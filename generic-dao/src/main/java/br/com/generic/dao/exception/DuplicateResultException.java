package br.com.generic.dao.exception;

import br.com.generic.exceptions.BaseRuntimeException;

public class DuplicateResultException extends BaseRuntimeException {

	private static final long serialVersionUID = 9041284665225950372L;
	
	public DuplicateResultException() {
		super();
		System.out.println();
	}

	public DuplicateResultException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DuplicateResultException(String message, Throwable cause) {
		super(message, cause);
	}

	public DuplicateResultException(String message) {
		super(message);
	}

	public DuplicateResultException(Throwable cause) {
		super(cause);
	}
	
}
