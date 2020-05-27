package exceptions;

public class PlatoIlegalException extends Exception {

	private static final long serialVersionUID = -38360372115420430L;

	public PlatoIlegalException(String errorMessage) {
		super(errorMessage);
	}
}
