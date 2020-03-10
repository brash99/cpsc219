package model;

public class InvalidPartyDataException extends Exception {

	public InvalidPartyDataException() {
		super();
	}

	public InvalidPartyDataException(String message) {
		super(message);
	}

	public InvalidPartyDataException(Throwable cause) {
		super(cause);
	}

	public InvalidPartyDataException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidPartyDataException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
