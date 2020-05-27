package exceptions;

public class MenuIlegalException extends Exception {

	private static final long serialVersionUID = -38360372115420430L;

	public MenuIlegalException(String errorMessage) {
		super(errorMessage);
	}
}
