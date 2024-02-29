package commons;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePage {

	protected WebDriver driver;

	public BasePage(WebDriver driver) {
		this.driver = driver;
	}

	// Browser Methods
	public void openPageUrl(String pageUrl) {
		driver.get(pageUrl);
	}

	public void navigateToUrlByJS(String pageUrl) {
		JavascriptExecutor jsExe = (JavascriptExecutor) driver;
		jsExe.executeScript("window.location = '" + pageUrl + "'");
		sleepInSecond(2);
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	public void refreshPage() {
		driver.navigate().refresh();
	}

	public String getPageSource() {
		return driver.getPageSource();
	}

	public String getWindowHandle() {
		return driver.getWindowHandle();
	}

	public void forwardToPage() {
		driver.navigate().forward();
	}

	public void backToPage() {
		driver.navigate().back();
	}

	// Element Methods
	public By getByLocator(String locatorValue) {
		By by = null;
		if (locatorValue.startsWith("xpath=") || locatorValue.startsWith("xPath=")
				|| locatorValue.startsWith("XPATH=") || locatorValue.startsWith("Xpath=")) {
			by = By.xpath(locatorValue.substring(6));
		} else if (locatorValue.startsWith("css=") || locatorValue.startsWith("Css=")
				|| locatorValue.startsWith("CSS=")) {
			by = By.cssSelector(locatorValue.substring(4));
		} else if (locatorValue.startsWith("id=") || locatorValue.startsWith("Id=")
				|| locatorValue.startsWith("ID=")) {
			by = By.id(locatorValue.substring(3));
		} else if (locatorValue.startsWith("name=") || locatorValue.startsWith("Name=")
				|| locatorValue.startsWith("NAME=")) {
			by = By.name(locatorValue.substring(5));
		} else if (locatorValue.startsWith("class=") || locatorValue.startsWith("Class=")
				|| locatorValue.startsWith("CLASS=")) {
			by = By.className(locatorValue.substring(6));
		} else if (locatorValue.startsWith("tagname=") || locatorValue.startsWith("Tagname=") || locatorValue.startsWith("tagName=")
				|| locatorValue.startsWith("TAGNAME=")) {
			by = By.tagName(locatorValue.substring(8));
		} else {
			throw new RuntimeException("Locator type is not valid");
		}

		return by;

	}

	public WebElement getElement(String locatorType) {
		return driver.findElement(getByLocator(locatorType));
	}

	public List<WebElement> getElements(String locatorType) {
		return driver.findElements(getByLocator(locatorType));
	}

	public void clickToElement(String locatorType) {
		getElement(locatorType).click();
	}

	public void clickToElementByJS(String locatorType) {
		WebElement we = getElement(locatorType);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", we);
	}

	public boolean isElementSelected(String locatorType) {
		return getElement(locatorType).isSelected();
	}

	public boolean isElementEnabled(String locatorType) {
		return getElement(locatorType).isEnabled();
	}

	public boolean isElementDisplayed(String locatorType) {
		return getElement(locatorType).isDisplayed();
	}

	// IN DOM or NOT IN DOM
	public boolean isElementUndisplayed(String locatorType) {
		boolean result = true;

		overrideGlobalTimeout(GlobalConstants.SHORT_TIMEOUT);
		List<WebElement> listElements = getElements(locatorType);
		overrideGlobalTimeout(GlobalConstants.LONG_TIMEOUT);
		if (listElements.size() == 0) {
			System.out.println("Element not in DOM");
			result = true;
		} else if (listElements.size() > 0 && !listElements.get(0).isDisplayed()) {
			System.out.println("Element in DOM and Invisible");
			result = true;
		} else {
			System.out.println("Element visible");
			result = false;
		}
		return result;
	}

	public void overrideGlobalTimeout(long timeOut) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeOut));
	}

	public void sendkeyToElement(String locatorType, String textValue) {
		getElement(locatorType).clear();
		getElement(locatorType).sendKeys(textValue);

	}

	public String getElementAttribute(String locatorType, String attributeValue) {
		return getElement(locatorType).getAttribute(attributeValue);
	}

	public void removeAttributeInDOM(String locatorType, String attributeRemove) {
		JavascriptExecutor jsExe = (JavascriptExecutor) driver;
		jsExe.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locatorType));
	}

	public String getElementText(String locatorType) {
		return getElement(locatorType).getText();
	}

	public int getElementSize(String locatorType) {
		return getElements(locatorType).size();
	}

	public String getHexaColorFromRGBA(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}

	// Alert Methods
	public Alert waitForAlertPresence() {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	public void acceptAlert() {
		waitForAlertPresence().accept();
	}

	public void dismissAlert() {
		waitForAlertPresence().dismiss();
	}

	public String getTextAlert() {
		return waitForAlertPresence().getText();
	}

	// Window Methods
	public void switchWindowById(String windowId) {
		Set<String> allWindowsId = driver.getWindowHandles();
		for (String id : allWindowsId) {
			if (!id.equals(allWindowsId)) {
				driver.switchTo().window(id);
			}
		}
	}

	public void switchWindowByTitle(String windowId, String tabTitle) {
		Set<String> allWindowsId = driver.getWindowHandles();
		for (String id : allWindowsId) {
			driver.switchTo().window(id);
			String currentTitle = driver.getTitle();
			if (currentTitle.equals(tabTitle)) {
				break;
			}
		}
	}

	public void closeAllTabsWithoutParent(String parentId) {
		Set<String> allWindowId = driver.getWindowHandles();
		for (String id : allWindowId) {
			if (!id.equals(parentId)) {
				driver.switchTo().window(id);
				driver.close();
			}
			driver.switchTo().window(parentId);
		}
	}

	// Dropdown Methods
	public boolean isDropdownMultiple(String locatorType) {
		Select select = new Select(getElement(locatorType));
		return select.isMultiple();
	}

	public void selectItemInDefaultDropdown(String locatorType, String textValue) {
		Select select = new Select(getElement(locatorType));
		select.selectByVisibleText(textValue);
	}

	public String getSelectedItemDefaultDropdown(String locatorType) {
		Select select = new Select(getElement(locatorType));
		return select.getFirstSelectedOption().getText();
	}

	public void selectItemInCustomDropdown(String parentLocator, String childLocator, String expectedItem) {
		getElement(parentLocator);
		sleepInSecond(2);

		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
		List<WebElement> allItems = explicitWait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childLocator)));

		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedItem)) {
				JavascriptExecutor jvExe = (JavascriptExecutor) driver;
				jvExe.executeScript("arguments[0].scrollIntoView(true);", item);

				item.click();
				sleepInSecond(1);
				break;
			}
		}
	}

	// Checkbox or Radio methods
	public void checkToCheckboxOrRadio(String locatorType) {
		WebElement we = getElement(locatorType);
		if (!we.isSelected()) {
			we.click();
		}
	}
	public void checkToCheckboxOrRadio(String locatorType, String restParams) {
		WebElement we = getElement(getDynamicLocator(locatorType));
		if (!we.isSelected()) {
			we.click();
		}
	}

	public void uncheckToCheckboxOrRadio(String locatorType) {
		WebElement we = getElement(locatorType);
		if (we.isSelected()) {
			we.click();
		}
	}

	// Frame or IFrame Method
	public void switchToFrameIframe(String locatorType) {
		driver.switchTo().frame(getElement(locatorType));
	}

	public void switchToDefaultContent() {
		driver.switchTo().defaultContent();
	}

	// Mouse - Action
	public void hoverMouseToElement(String locatorType) {
		Actions action = new Actions(driver);
		action.moveToElement(getElement(locatorType));
	}

	public void pressKeyToElement(String locatorType, Keys key) {
		Actions action = new Actions(driver);
		action.sendKeys(getElement(locatorType), key).perform();
	}

	public void rightClick(String locatorType) {
		Actions action = new Actions(driver);
		action.contextClick(getElement(locatorType));
	}

	public void clickAnHold(String locatorType) {
		Actions action = new Actions(driver);
		action.clickAndHold(getElement(locatorType));
	}

	// Scroll
	public void scrollToElement(String locatorType) {
		JavascriptExecutor jsExe = (JavascriptExecutor) driver;
		jsExe.executeScript("arguments[0].scrollIntoView(true);", getElement(locatorType));
	}

	public void scrollToBottonPage() {
		JavascriptExecutor jsExe = (JavascriptExecutor) driver;
		jsExe.executeScript("window.scrollBy(0,document.body.scrollHeight");
	}

	// Upload file
	public void uploadFiles(String... fileName) {
		String filePath = GlobalConstants.UPLOAD_FILE_FOLDER;
		String fullFileName = "";
		for (String file : fileName) {
			fullFileName = fullFileName + filePath + file + "\n";
		}
		fullFileName = fullFileName.trim();
		getElement(pageObjects.BasePageUI.UPLOAD_FILE_TYPE).sendKeys(fullFileName);

	}

	public boolean isImageUpLoaded(String locatorType) {
		JavascriptExecutor jsExe = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExe.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != \\\"undefined\\\" && arguments[0].naturalWidth > 0",
				getElement(locatorType));
		if (status) {
			return true;
		} else {
			return false;
		}
	}
	//Dynamic locator
	public String getDynamicLocator(String locator, String... restParams) {
		return String.format(locator, (Object[])restParams);
	}
	// Wait
	public void waitForElementVisible(String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorType)));
	}
	public void waitForElementVisible(String locatorType, String restParams) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicLocator(locatorType, restParams))));
	}

	public void waitForAllElementVisible(String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locatorType)));
	}
	public void waitForAllElementVisible(String locatorType, String restParams) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(getDynamicLocator(locatorType, restParams))));
	}

	public void waitForElementInvisible(String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
	}
	public void waitForElementInvisible(String locatorType, String restParams) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicLocator(locatorType,restParams))));
	}

	public void waitForAllElementInvisible(String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getElements(locatorType)));
	}

	public void waitForElementUndisplayed(String locatorType) {
		overrideGlobalTimeout(GlobalConstants.SHORT_TIMEOUT);
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
		overrideGlobalTimeout(GlobalConstants.LONG_TIMEOUT);
	}
	public void waitForElementUndisplayed(String locatorType, String restParams) {
		overrideGlobalTimeout(GlobalConstants.SHORT_TIMEOUT);
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicLocator(locatorType,restParams))));
		overrideGlobalTimeout(GlobalConstants.LONG_TIMEOUT);
	}

	public void waitForElementClickable(String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locatorType)));
	}
	public void waitForElementClickable(String locatorType, String restParams) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicLocator(locatorType,restParams))));
	}

	// Cookies
	public void setCookies(Set<Cookie> cookies) {
		for (Cookie cookie : cookies) {
			driver.manage().addCookie(cookie);
		}
	}

	// Dynamic params
	private String getDynamicXPath(String locatorType, String...values) {
		if(locatorType.startsWith("xpath=") || locatorType.startsWith("Xpath=") || locatorType.startsWith("XPATH=")) {
			locatorType = String.format(locatorType, values);
		}
		return locatorType;
			
	}
	
	public WebElement getElement(String locatorType, String...values) {
		return driver.findElement(getByLocator(getDynamicXPath(locatorType, values)));
	}

	public List<WebElement> getElements(String locatorType, String...values) {
		return driver.findElements(getByLocator(getDynamicXPath(locatorType, values)));
	}

	public void clickToElement(String locatorType, String...values) {
		getElement(getDynamicXPath(locatorType, values)).click();
	}

	public void clickToElementByJS(String locatorType, String...values) {
		WebElement we = getElement(getDynamicXPath(locatorType, values));
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", we);
	}

	public boolean isElementSelected(String locatorType, String...values) {
		return getElement(getDynamicXPath(locatorType, values)).isSelected();
	}

	public boolean isElementEnabled(String locatorType, String...values) {
		return getElement(getDynamicXPath(locatorType, values)).isEnabled();
	}

	public boolean isElementDisplayed(String locatorType, String...values) {
		return getElement(getDynamicXPath(locatorType, values)).isDisplayed();
	}

	// IN DOM or NOT IN DOM
	public boolean isElementUndisplayed(String locatorType, String...values) {
		boolean result = true;

		overrideGlobalTimeout(GlobalConstants.SHORT_TIMEOUT);
		List<WebElement> listElements = getElements(getDynamicXPath(locatorType, values));
		overrideGlobalTimeout(GlobalConstants.LONG_TIMEOUT);
		if (listElements.size() == 0) {
			System.out.println("Element not in DOM");
			result = true;
		} else if (listElements.size() > 0 && !listElements.get(0).isDisplayed()) {
			System.out.println("Element in DOM and Invisible");
			result = true;
		} else {
			System.out.println("Element visible");
			result = false;
		}
		return result;
	}

	public void sendkeyToElement(String locatorType, String textValue, String...values) {
		getElement(getDynamicXPath(locatorType, values)).clear();
		getElement(getDynamicXPath(locatorType, values)).sendKeys(textValue);

	}

	public String getElementAttribute(String locatorType, String attributeValue, String...values) {
		return getElement(getDynamicXPath(locatorType, values)).getAttribute(attributeValue);
	}

	public void removeAttributeInDOM(String locatorType, String attributeRemove, String...values) {
		JavascriptExecutor jsExe = (JavascriptExecutor) driver;
		jsExe.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(getDynamicXPath(locatorType, values)));
	}

	public String getElementText(String locatorType, String...values) {
		return getElement(getDynamicXPath(locatorType, values)).getText();
	}

	public int getElementSize(String locatorType, String...values) {
		return getElements(getDynamicXPath(locatorType, values)).size();
	}


	// Dropdown Methods
	public boolean isDropdownMultiple(String locatorType, String...values) {
		Select select = new Select(getElement(locatorType));
		return select.isMultiple();
	}

	public void selectItemInDefaultDropdown(String locatorType, String textValue, String...values) {
		Select select = new Select(getElement(locatorType));
		select.selectByVisibleText(textValue);
	}

	public String getSelectedItemDefaultDropdown(String locatorType, String...values) {
		Select select = new Select(getElement(locatorType));
		return select.getFirstSelectedOption().getText();
	}
	public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childLocator, String expectedItem) {
		getElement(parentLocator).click();
		sleepInSecond(1);

		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
		List<WebElement> allItems = explicitWait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childLocator)));

		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedItem)) {
				JavascriptExecutor jvExe = (JavascriptExecutor) driver;
				jvExe.executeScript("arguments[0].scrollIntoView(true);", item);

				item.click();
				sleepInSecond(1);
				break;
			}
		}
	}
	public void selectItemInCustomDropdown(String parentLocator, String childLocator, String expectedItem, String...values) {
		getElement(parentLocator).click();
		sleepInSecond(2);

		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
		List<WebElement> allItems = explicitWait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(getDynamicXPath(childLocator, values))));

		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedItem)) {
				JavascriptExecutor jvExe = (JavascriptExecutor) driver;
				jvExe.executeScript("arguments[0].scrollIntoView(true);", item);

				item.click();
				sleepInSecond(1);
				break;
			}
		}
	}


	// Checkbox or Radio methods
	public void checkToCheckboxOrRadio(String locatorType, String...values) {
		WebElement we = getElement(getDynamicXPath(locatorType, values));
		if (!we.isSelected()) {
			we.click();
		}
	}
	public void checkToCheckboxOrRadio(WebDriver driver, String locatorType) {
		WebElement we = getElement(locatorType);
		if (!we.isSelected()) {
			we.click();
		}
	}
	public void uncheckToCheckboxOrRadio(String locatorType, String...values) {
		WebElement we = getElement(getDynamicXPath(locatorType, values));
		if (we.isSelected()) {
			we.click();
		}
	}
	public void uncheckToCheckboxOrRadio(WebDriver driver, String locatorType) {
		WebElement we = getElement(locatorType);
		if (we.isSelected()) {
			we.click();
		}
	}

	// Frame or IFrame Method
	public void switchToFrameIframe(String locatorType, String...values) {
		driver.switchTo().frame(getElement(getDynamicXPath(locatorType, values)));
	}

	// Mouse - Action
	public void hoverMouseToElement(String locatorType, String...values) {
		Actions action = new Actions(driver);
		action.moveToElement(getElement(getDynamicXPath(locatorType, values)));
	}

	public void rightClick(String locatorType, String...values) {
		Actions action = new Actions(driver);
		action.contextClick(getElement(getDynamicXPath(locatorType, values)));
	}

	public void clickAnHold(String locatorType, String...values) {
		Actions action = new Actions(driver);
		action.clickAndHold(getElement(getDynamicXPath(locatorType, values)));
	}

	// Scroll
	public void scrollToElement(String locatorType, String...values) {
		JavascriptExecutor jsExe = (JavascriptExecutor) driver;
		jsExe.executeScript("arguments[0].scrollIntoView(true);", getElement(getDynamicXPath(locatorType, values)));
	}

	public boolean isImageUpLoaded(String locatorType, String...values) {
		JavascriptExecutor jsExe = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExe.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != \\\"undefined\\\" && arguments[0].naturalWidth > 0",
				getElement(getDynamicXPath(locatorType, values)));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	// Wait
	public void waitForElementVisible(String locatorType, String...values) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicXPath(locatorType, values))));
	}

	public void waitForAllElementVisible(String locatorType, String...values) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(getDynamicXPath(locatorType, values))));
	}

	public void waitForElementInvisible(String locatorType, String...values) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicXPath(locatorType, values))));
	}

	public void waitForAllElementInvisible(String locatorType, String...values) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getElements(getDynamicXPath(locatorType, values))));
	}

	public void waitForElementUndisplayed(String locatorType, String...values) {
		overrideGlobalTimeout(GlobalConstants.SHORT_TIMEOUT);
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicXPath(locatorType, values))));
		overrideGlobalTimeout(GlobalConstants.LONG_TIMEOUT);
	}

	public void waitForElementClickable(String locatorType, String...values) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXPath(locatorType, values))));
	}
	

	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
