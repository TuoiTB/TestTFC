package browserFactofy;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxDriverManager implements BrowserFactory{
	public WebDriver getBrowserDriver() {
		WebDriverManager.firefoxdriver().setup();
		FirefoxOptions fOptions = new FirefoxOptions();
		fOptions.addArguments("--disable-infobars");
		fOptions.addArguments("--disable-notifications");
		fOptions.setAcceptInsecureCerts(true);
		return new FirefoxDriver(fOptions);
	}
}
