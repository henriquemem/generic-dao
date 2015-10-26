package br.com.generic.dao.exception;

import br.com.generic.exceptions.BaseRuntimeException;

public class PredicateInvalidException extends BaseRuntimeException{

	private static final long serialVersionUID = -2924095733792929725L;

	public PredicateInvalidException() {
		super();
	}

	public PredicateInvalidException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PredicateInvalidException(String message, Throwable cause) {
		super(message, cause);
	}

	public PredicateInvalidException(String message) {
		super(message);
	}

	public PredicateInvalidException(Throwable cause) {
		super(cause);
	}
	
	
}
