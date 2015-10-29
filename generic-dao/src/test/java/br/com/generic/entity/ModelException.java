package br.com.generic.entity;

public class ModelException extends RuntimeException {
	private static final long serialVersionUID = -4894345172719991249L;

	public ModelException(String message) {
		super(message);
		setStackTrace(new StackTraceElement[] {});
	}

	public ModelException(String message, Throwable cause) {
		super(message, cause);
	}
}
