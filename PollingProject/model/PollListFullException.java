package model;

public class PollListFullException extends Exception {

	public PollListFullException() {
		super();
	}

	public PollListFullException(String message) {
		super(message);
	}

	public PollListFullException(Throwable cause) {
		super(cause);
	}

	public PollListFullException(String message, Throwable cause) {
		super(message, cause);
	}

	public PollListFullException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
