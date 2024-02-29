package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.FamiliarQuestionHNPageUI;

public class FamiliarQuestionHNPageObject extends BasePage {
public WebDriver driver;

    public FamiliarQuestionHNPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
    public void CreateQuestion(){
        waitForElementClickable(FamiliarQuestionHNPageUI.CREATE_QUESTION_BUTTON);
        clickToElement(FamiliarQuestionHNPageUI.CREATE_QUESTION_BUTTON);
    }
    public void enterToName(String name){
        waitForElementVisible(FamiliarQuestionHNPageUI.NAME_TEXTBOX);
        sendkeyToElement(FamiliarQuestionHNPageUI.NAME_TEXTBOX, name);
    }
    public void enterToQuestion(String name){
        waitForElementVisible(FamiliarQuestionHNPageUI.QUESTION_TEXTAREA);
        sendkeyToElement(FamiliarQuestionHNPageUI.QUESTION_TEXTAREA, name);
    }
    public void enterToShortAnswers(String name){
        waitForElementVisible(FamiliarQuestionHNPageUI.SHORT_ANSWER_TEXTAREA);
        sendkeyToElement(FamiliarQuestionHNPageUI.SHORT_ANSWER_TEXTAREA, name);
    }

    public void enterToDetailAnswers(String detailAnswers){
        //Switch to Iframe
        switchToFrameIframe(FamiliarQuestionHNPageUI.DETAIL_ANSWER_IFRAME);

        //click to content texbox
        waitForElementClickable(FamiliarQuestionHNPageUI.DETAIL_ANSWER_TEXTBOX);
        clickToElement(FamiliarQuestionHNPageUI.DETAIL_ANSWER_TEXTBOX);

        //enter to content textbox
        waitForElementVisible(FamiliarQuestionHNPageUI.DETAIL_ANSWER_TEXTBOX);
        sendkeyToElement(FamiliarQuestionHNPageUI.DETAIL_ANSWER_TEXTBOX, detailAnswers);

        //switch to  default content
        switchToDefaultContent();
    }
    public void selectExamDropdown(String textValue){
        waitForElementVisible(FamiliarQuestionHNPageUI.EXAM_DEFAULT_DROPDOWN,textValue);
        selectItemInDefaultDropdown(FamiliarQuestionHNPageUI.EXAM_DEFAULT_DROPDOWN,textValue);
    }
    public void selectCompetitionRoundDropdown(String textValue){
        waitForElementVisible(FamiliarQuestionHNPageUI.COMPETITION_ROUND_DEFAULT_DROPDOWN,textValue);
        selectItemInDefaultDropdown(FamiliarQuestionHNPageUI.COMPETITION_ROUND_DEFAULT_DROPDOWN,textValue);
    }
    public void clickToSaveButton(){
        waitForElementClickable(FamiliarQuestionHNPageUI.SAVE_BUTTON);
        clickToElement(FamiliarQuestionHNPageUI.SAVE_BUTTON);
    }

}
