package exceptions;

public class ValoracionIlegalException extends Exception {

	private static final long serialVersionUID = -383603721112320430L;

	public ValoracionIlegalException(String errorMessage) {
		super(errorMessage);
	}
}
