package pageUIs;

public class FamiliarQuestionHNPageUI {
    public final static String NAME_TEXTBOX = "id=headerName";
    public final static String CREATE_QUESTION_BUTTON = "XPATH=//div[@ng-show='createAllowedButtonSingle']//button[contains(string(),'Câu hỏi')]";
    public final static String QUESTION_TEXTAREA = "XPATH=//textarea[@id='cauHoi']";
    public final static String SHORT_ANSWER_TEXTAREA = "XPATH=//textarea[@id='cauTraLoi']";
    public final static String DETAIL_ANSWER_IFRAME = "XPATH=//iframe[contains(@id,'cauTraLoiChiTiet')]";
    public final static String DETAIL_ANSWER_TEXTBOX = "CSS=#tinymce>p";
    public final static String EXAM_DEFAULT_DROPDOWN = "XPATH=//label[text()='Bài thi']//parent::div//following-sibling::div//select";
    public final static String COMPETITION_ROUND_DEFAULT_DROPDOWN = "XPATH=//label[text()='Vòng thi']//parent::div//following-sibling::div//select";
    public final static String SAVE_BUTTON = "XPATH=//button[contains(@class,'btn-info')]";
    public final static String TITLE_QUESTION = "XPATH=//a[text()='%s']";
    public final static String ARROW_LEFT = "XPATH=//i[contains(@class,'fa-arrow-left')]";
    public final static String MESSAGE_SAVED = "XPATH=//strong[text()='Content saved: ']";



}
