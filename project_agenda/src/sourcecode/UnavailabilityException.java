package sourcecode;

public class UnavailabilityException extends Exception {

	private static final long serialVersionUID = 1L;

	public UnavailabilityException() {
		super();
	}

	public UnavailabilityException(String message) {
		super(message);
	}

	public UnavailabilityException(Throwable cause) {
		super(cause);
	}

	public UnavailabilityException(String message, Throwable cause) {
		super(message, cause);
	}
}
