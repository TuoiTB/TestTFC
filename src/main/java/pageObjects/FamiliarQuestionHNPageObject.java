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
    public void enterToNameAfterRemoveAttributeByJS(String name){
        waitForElementVisible(FamiliarQuestionHNPageUI.NAME_TEXTBOX);

        removeAttributeInDOM(FamiliarQuestionHNPageUI.NAME_TEXTBOX,"data-element");
        removeAttributeInDOM(FamiliarQuestionHNPageUI.NAME_TEXTBOX,"localize");
        removeAttributeInDOM(FamiliarQuestionHNPageUI.NAME_TEXTBOX,"class");
        //removeAttributeInDOM(FamiliarQuestionHNPageUI.NAME_TEXTBOX,"type");
        removeAttributeInDOM(FamiliarQuestionHNPageUI.NAME_TEXTBOX,"ng-model");
        removeAttributeInDOM(FamiliarQuestionHNPageUI.NAME_TEXTBOX,"ng-class");
        removeAttributeInDOM(FamiliarQuestionHNPageUI.NAME_TEXTBOX,"ng-disabled");
        removeAttributeInDOM(FamiliarQuestionHNPageUI.NAME_TEXTBOX,"ng-readonly");
        removeAttributeInDOM(FamiliarQuestionHNPageUI.NAME_TEXTBOX,"umb-auto-focus");
        removeAttributeInDOM(FamiliarQuestionHNPageUI.NAME_TEXTBOX,"focus-on-filled");
        removeAttributeInDOM(FamiliarQuestionHNPageUI.NAME_TEXTBOX,"val-server-field");
        removeAttributeInDOM(FamiliarQuestionHNPageUI.NAME_TEXTBOX,"required");
        removeAttributeInDOM(FamiliarQuestionHNPageUI.NAME_TEXTBOX,"aria-required");
        removeAttributeInDOM(FamiliarQuestionHNPageUI.NAME_TEXTBOX,"aria-invalid");
        removeAttributeInDOM(FamiliarQuestionHNPageUI.NAME_TEXTBOX,"autocomplete");

        sendkeyToElement(FamiliarQuestionHNPageUI.NAME_TEXTBOX, name);
    }
    public void enterToNameByJS(String name){
        waitForElementVisible(FamiliarQuestionHNPageUI.NAME_TEXTBOX);
        sendkeyToElementByJS(FamiliarQuestionHNPageUI.NAME_TEXTBOX, name);
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
    public void clickToArrowLeft(){
        waitForElementClickable(FamiliarQuestionHNPageUI.ARROW_LEFT);
        clickToElement(FamiliarQuestionHNPageUI.ARROW_LEFT);
    }
    public boolean isMessageSavedDisplayed(){
        waitForElementVisible(FamiliarQuestionHNPageUI.MESSAGE_SAVED);
        return isElementDisplayed(FamiliarQuestionHNPageUI.MESSAGE_SAVED);
    }
    public boolean isTitleQuestionDisplayed(String titleName){
        waitForElementVisible(FamiliarQuestionHNPageUI.TITLE_QUESTION, titleName);
        return isElementDisplayed(FamiliarQuestionHNPageUI.TITLE_QUESTION, titleName);
    }
    public void refreshPage(){
        refreshPage();
    }
}
