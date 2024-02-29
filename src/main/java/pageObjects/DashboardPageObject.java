package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.DashBoardPageUI;
import pageUIs.FamiliarQuestionHNPageUI;

public class DashboardPageObject extends BasePage {
    public WebDriver driver;

    public DashboardPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
    public FamiliarQuestionHNPageObject openFamiliarQuestionHaNoi() {
        waitForElementClickable(DashBoardPageUI.CLOSE_BUTTON);
        clickToElement(DashBoardPageUI.CLOSE_BUTTON);
        waitForElementClickable(DashBoardPageUI.EXPAND_ICON_HOMEPAGE);
        clickToElement(DashBoardPageUI.EXPAND_ICON_HOMEPAGE);
        waitForElementClickable(DashBoardPageUI.EXPAND_ICON_HANOI);
        clickToElement(DashBoardPageUI.EXPAND_ICON_HANOI);
        waitForElementClickable(DashBoardPageUI.FAMILIAR_QUESTION_HANOI);
        clickToElement(DashBoardPageUI.FAMILIAR_QUESTION_HANOI);
        return PageGeneratorManager.familiarQuestionHNPageObject(driver);
    }
}
