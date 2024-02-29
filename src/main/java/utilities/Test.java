package utilities;

public class Test {
    public static void main(String []args) throws Exception {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("D:/Automation/TEST/src/main/java/utilities/FAQ_TFC_HN_Round_0.xlsx", "Primary");

        System.out.println(excel.getCellData("Questions", 1));
        System.out.println(excel.getCellData("Short answers", 1));
        System.out.println(excel.getCellData("Answers", 1));
    }
}
