package utilities;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Test1214234 {
    public static void main(String []args) throws Exception {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("D:/Automation/TEST/src/main/java/utilities/FAQ_TFC_HN_Round_0.xlsx", "Primary");

        System.out.println(excel.getCellData("Questions", 1));
        System.out.println(excel.getCellData("Short answers", 1));
        System.out.println(excel.getCellData("Answers", 1));
    }
    public static class LearnDataProvider {
        @DataProvider(name = "data_provider_01")
        public Object[][] dpMethod() {
            return new Object[][]{{"First-Value"}, {"Second-Value"}};
        }

        @Test(dataProvider = "data_provider_01")
        public void testDataProvider(String value) {
            System.out.println("Passed Parameter is: " + value);
        }
}
}
