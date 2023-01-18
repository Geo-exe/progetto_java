package sourcecode;

/**
 * La classe Exception viene estesa con altre varie eccezioni.
 * 
 * @author Griffa Francesco
 * @author Peracini Fabio
 *
 */
public class UnavailabilityException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Costruttore della classe.
	 */
	public UnavailabilityException() {
		super();
	}

	/**
	 * Costruttore della classe.
	 * 
	 * @param message stringa da stampare
	 */
	public UnavailabilityException(String message) {
		super(message);
	}

	/**
	 * Costruttore della classe.
	 * 
	 * @param cause causa dell'eccezione
	 */
	public UnavailabilityException(Throwable cause) {
		super(cause);
	}

	/**
	 * Costruttore della classe.
	 * 
	 * @param message stringa da stampare
	 * @param cause   causa dell'eccezione
	 */
	public UnavailabilityException(String message, Throwable cause) {
		super(message, cause);
	}
}
