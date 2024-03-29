
import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.DashboardPageObject;
import pageObjects.FamiliarQuestionHNPageObject;
import pageObjects.LoginPageObject;
import pageObjects.PageGeneratorManager;
import utilities.DataProviderFactory;


public class FAQ_HN extends BaseTest{
    LoginPageObject loginPage;
    DashboardPageObject dashboardPage;
    FamiliarQuestionHNPageObject familiarQuestionHNPage;
    String browserName;


    @Parameters({"browserName", "serverName"})
    @BeforeClass
    public void beforeClass(String browserName, String serverName) {
        String userNameLogin = "tuoi.tb@iigvietnam.edu.vn";
        String passwordLogin = "1234567899";
        this.browserName = browserName;
        WebDriver driver = getBrowserDriver(browserName, serverName);
        loginPage = PageGeneratorManager.getLoginPage(driver);
        dashboardPage = loginPage.loginToSystem(userNameLogin, passwordLogin);
    }

    //@Test(dataProvider = "FAQ_HN_Primary_Round_0", dataProviderClass = DataProviderFactory.class)
    public void FAQ_HN_01_Primary_Round_0(String Name, String Questions, String shortAnswers, String Answers) throws Exception {
        /*ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("D:/Automation/TEST/src/main/java/utilities/FAQ_TFC_HN_Round_0.xlsx", "Primary");*/
        familiarQuestionHNPage = dashboardPage.openFamiliarQuestionHaNoi();
        familiarQuestionHNPage.CreateQuestion();
        familiarQuestionHNPage.enterToNameByJS(Name);
        familiarQuestionHNPage.enterToQuestionByJS(Questions);
        familiarQuestionHNPage.enterToShortAnswersByJS(shortAnswers);
        familiarQuestionHNPage.enterToDetailAnswers(Answers);
        familiarQuestionHNPage.selectExamDropdown("TOEFL Primary Challenge");
        familiarQuestionHNPage.selectCompetitionRoundDropdown("Link trải nghiệm Online");
        familiarQuestionHNPage.clickToSaveButton();
        familiarQuestionHNPage.isMessageSavedDisplayed();
        familiarQuestionHNPage.clickToArrowLeft();
        familiarQuestionHNPage.refreshPage();
    }
    @Test()
    public void FAQ_HN_02_Primary_Round_0() throws Exception {
        /*ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("D:/Automation/TEST/src/main/java/utilities/FAQ_TFC_HN_Round_0.xlsx", "Primary");*/
        familiarQuestionHNPage = dashboardPage.openFamiliarQuestionHaNoi();
        familiarQuestionHNPage.CreateQuestion();
        familiarQuestionHNPage.enterToNameByJS("Nội dung name ở đây");
        familiarQuestionHNPage.enterToQuestionByJS("á á á á á á â");
        familiarQuestionHNPage.enterToShortAnswersByJS("á á á á á á â");
        familiarQuestionHNPage.enterToDetailAnswers("á á á á á á â");
        familiarQuestionHNPage.selectExamDropdown("TOEFL Primary Challenge");
        familiarQuestionHNPage.selectCompetitionRoundDropdown("Link trải nghiệm Online");
        familiarQuestionHNPage.clickToSaveButton();
        familiarQuestionHNPage.isMessageSavedDisplayed();
        familiarQuestionHNPage.clickToArrowLeft();
        familiarQuestionHNPage.refreshPage();
        //familiarQuestionHNPage.isTitleQuestionDisplayed(excel.getCellData("Name", 1));
    }
    @AfterClass
    public void afterClass() {
        //closeBrowserDriver();
    }

}
