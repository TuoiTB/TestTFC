package utilities;

import org.testng.annotations.DataProvider;

public class DataProviderFactory {

    @DataProvider(name = "data_provider_02", parallel = true)
    public Object[][] dataHRM() {
        return new Object[][]{{"anhtester", "123456", "Admin"}, {"joe.larson", "joe.larson", "Employee"}};
    }

    @DataProvider(name = "data_provider_03")
    public Object[][] dataLogin() {
        return new Object[][]{{"anhtester", "123456", "Admin"}};
    }
    @DataProvider(name = "FAQ_HN_Primary_Round_0")
    public Object[][] FAQ_HN_Primary_Round_0() {
        ExcelHelper excelHelper = new ExcelHelper();
        Object[][] data = excelHelper.getExcelData("D:/Automation/TEST/src/main/java/utilities/FAQ_TFC_HN_Round_0.xlsx", "Primary");
        System.out.println("FAQ_HN_Primary_Round_0" + data);
        return data;
    }
}