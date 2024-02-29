package browserFactofy;

public class BrowserNotSupportException extends IllegalStateException{
	private static final long serialVersionUID = 1L;
	
	public BrowserNotSupportException(String browserName) {
		super(String.format("Browser is not support: %s", browserName));
	}

}
