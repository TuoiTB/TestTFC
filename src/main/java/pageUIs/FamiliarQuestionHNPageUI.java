package pageUIs;

public class FamiliarQuestionHNPageUI {
    public final static String NAME_TEXTBOX = "CSS=input#headerName";
    public final static String CREATE_QUESTION_BUTTON = "XPATH=//div[@ng-show='createAllowedButtonSingle']//button[contains(string(),'Câu hỏi')]";
    public final static String QUESTION_TEXTAREA = "XPATH=//textarea[@id='cauHoi']";
    public final static String SHORT_ANSWER_TEXTAREA = "XPATH=//textarea[@id='cauTraLoi']";
    public final static String DETAIL_ANSWER_IFRAME = "XPATH=//iframe[contains(@id,'cauTraLoiChiTiet')]";
    public final static String DETAIL_ANSWER_TEXTBOX = "CSS=#tinymce>p";
    public final static String EXAM_DEFAULT_DROPDOWN = "XPATH=//label[text()='Bài thi']//parent::div//following-sibling::div//select";
    public final static String COMPETITION_ROUND_DEFAULT_DROPDOWN = "XPATH=//label[text()='Vòng thi']//parent::div//following-sibling::div//select";
    public final static String SAVE_BUTTON = "XPATH=//button[contains(@class,'btn-info')]";



}
