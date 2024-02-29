package browserFactofy;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class EdgeDriverManager implements BrowserFactory {

	public WebDriver getBrowserDriver() {
		WebDriverManager.edgedriver().setup();
		EdgeOptions eOption = new EdgeOptions();

		return new EdgeDriver(eOption);
	}

}
