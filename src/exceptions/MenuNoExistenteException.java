package exceptions;

public class MenuNoExistenteException extends Exception {

	private static final long serialVersionUID = -38360372115420430L;

	public MenuNoExistenteException(String errorMessage) {
		super(errorMessage);
	}
}
