
import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.DashboardPageObject;
import pageObjects.FamiliarQuestionHNPageObject;
import pageObjects.LoginPageObject;
import pageObjects.PageGeneratorManager;
import utilities.ExcelHelper;

import java.nio.charset.StandardCharsets;


public class FAQ_HN extends BaseTest{
    LoginPageObject loginPage;
    PageGeneratorManager pageGeneratorManager;
    DashboardPageObject dashboardPage;
    FamiliarQuestionHNPageObject familiarQuestionHNPage;
    private WebDriver driver;
    String browserName;
    private String userNameLogin, passwordLogin;


    @Parameters({"browserName", "serverName"})
    @BeforeClass
    public void beforeClass(String browserName, String serverName) {
        userNameLogin = "tuoi.tb@iigvietnam.edu.vn";
        passwordLogin = "1234567899";
        this.browserName = browserName;
        driver = getBrowserDriver(browserName, serverName);
        loginPage = PageGeneratorManager.getLoginPage(driver);
        dashboardPage = loginPage.loginToSystem(userNameLogin,passwordLogin);
    }
    @Test
    public void Employee_01_Primary_Round_0() throws Exception {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("D:/Automation/TEST/src/main/java/utilities/FAQ_TFC_HN_Round_0.xlsx", "Primary");
        familiarQuestionHNPage = dashboardPage.openFamiliarQuestionHaNoi();
        familiarQuestionHNPage.CreateQuestion();
        familiarQuestionHNPage.enterToName(String.valueOf(StandardCharsets.UTF_8.encode(excel.getCellData("Name", 1))));
        familiarQuestionHNPage.enterToQuestion(excel.getCellData("Questions", 1));
        familiarQuestionHNPage.enterToShortAnswers(excel.getCellData("Short answers", 1));
        familiarQuestionHNPage.enterToDetailAnswers(excel.getCellData("Answers", 1));
        familiarQuestionHNPage.clickToSaveButton();
    }
    @Test
    public void Employee_01_PersonalDetail() {
   

    }

    @Test
    public void Employee_02_ContactDetails() {
    }

    @Test
    public void Employee_03_EmergencyContacts() {

    }
    @AfterClass
    public void afterClass() {
        //closeBrowserDriver();
    }

}
