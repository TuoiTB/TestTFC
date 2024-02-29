package pageObjects;

import org.openqa.selenium.WebDriver;


public class PageGeneratorManager {
	public static pageObjects.LoginPageObject getLoginPage(WebDriver driver) {
		return new LoginPageObject(driver);
	}
	public static DashboardPageObject getdashBoardPage(WebDriver driver) {
		return new DashboardPageObject(driver);
	}
	public static FamiliarQuestionHNPageObject familiarQuestionHNPageObject(WebDriver driver) {
		return new FamiliarQuestionHNPageObject(driver);
	}
}
