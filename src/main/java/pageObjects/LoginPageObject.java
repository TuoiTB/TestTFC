package pageObjects;

import pageUIs.LoginPageUI;
import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageObjects.DashboardPageObject;
import pageObjects.PageGeneratorManager;

public class LoginPageObject extends BasePage {

    WebDriver driver;

    public LoginPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public DashboardPageObject loginToSystem(String userName, String password){
        waitForElementVisible(LoginPageUI.USER_NAME_TEXTBOX);
        sendkeyToElement(LoginPageUI.USER_NAME_TEXTBOX, userName);
        waitForElementVisible(LoginPageUI.PASSWORD_TEXTBOX);
        sendkeyToElement(LoginPageUI.PASSWORD_TEXTBOX, password);
        waitForElementClickable(LoginPageUI.LOGIN_BUTTON);
        clickToElement(LoginPageUI.LOGIN_BUTTON);
        return PageGeneratorManager.getdashBoardPage(driver);
    }
}