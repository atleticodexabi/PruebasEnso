package exceptions;

public class FacturaNoExistenteException extends Exception {

	private static final long serialVersionUID = -38360372115420430L;

	public FacturaNoExistenteException(String errorMessage) {
		super(errorMessage);
	}
}
