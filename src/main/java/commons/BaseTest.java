package commons;

import browserFactofy.*;
import io.github.bonigarcia.wdm.managers.OperaDriverManager;
import io.github.bonigarcia.wdm.managers.SafariDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;

public class BaseTest {
	protected final Logger log;
	private WebDriver driver;

	public WebDriver getDriver() {
		return this.driver;
	}
	protected BaseTest() {
		log = LogManager.getLogger(getClass());
	}

	protected WebDriver getBrowserDriver(String browserName, String serverName) {
		BrowserList brList = BrowserList.valueOf(browserName.toUpperCase());
		switch (brList) {
			case FIREFOX:
				driver = new FirefoxDriverManager().getBrowserDriver();
				break;

			case CHROME:
				driver = new ChromeDriverManager().getBrowserDriver();
				break;

			case EDGE:
				driver = new EdgeDriverManager().getBrowserDriver();
				break;
			default:
				throw new BrowserNotSupportException(browserName);
		}

		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		driver.get(getAppUrl(serverName));
		return driver;
	}

	private String getAppUrl(String serverName) {
		String appUrl = null;
		ServerList serList = ServerList.valueOf(serverName.toUpperCase());
		switch (serList) {
		case DEV:
			appUrl = "https://dangkytoeflchallenge.iigvietnam.com/umbraco#/login/false?doctype=fAQ&create=true&returnPath=%252Fumbraco%2523%252Fcontent%252Fcontent%252Fedit%252F1247%253Fdoctype%253DfAQ%2526create%253Dtrue";
			break;

		case TESTING:
			appUrl = "https://dangkytoeflchallenge.iigvietnam.com/umbraco#/login/false?doctype=fAQ&create=true&returnPath=%252Fumbraco%2523%252Fcontent%252Fcontent%252Fedit%252F1247%253Fdoctype%253DfAQ%2526create%253Dtrue";
			break;
		default:
			break;
		}
		return appUrl;
	}

	protected void closeBrowserDriver() {
		String cmd = null;
		try {
			String osName = System.getProperty("os.name").toLowerCase();
			String driverInstanceName = driver.toString().toLowerCase();

			String browserDriverName = null;

			if (driverInstanceName.contains("chrome")) {
				browserDriverName = "chromedriver";
			} else if (driverInstanceName.contains("firefox")) {
				browserDriverName = "geckodriver";
			} else if (driverInstanceName.contains("edge")) {
				browserDriverName = "msedgedriver";
			} else {
				browserDriverName = "safaridriver";
			}
			if (osName.contains("window")) {
				cmd = "taskkill /F /FI \"IMAGENAME eq " + browserDriverName + "*\"";
			} else {
				cmd = "pkill " + browserDriverName;
			}

			if (driver != null) {
				driver.manage().deleteAllCookies();
				driver.quit();
			}
		} catch (Exception e) {

		} finally {
			try {
				Process process = Runtime.getRuntime().exec(cmd);
				process.waitFor();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	protected boolean verifyTrue(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertTrue(condition);
		} catch (Throwable e) {
			pass = false;
		}
		return pass;
	}
	
	protected boolean verifyFalse(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertFalse(condition);
		} catch (Throwable e) {
			pass = false;
		}
		return pass;
	}
	
	protected boolean verifyEquals(Object actual, Object expected) {
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expected);
		} catch (Throwable e) {
			pass = false;
		}
		return pass;
	}
}

