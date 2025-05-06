package com.khanhppn.utils.dataProviders;

import com.khanhppn.constants.FrameworkConstants;
import com.khanhppn.model.LoginData;
import com.khanhppn.model.SearchData;
import com.khanhppn.model.TestData;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DataProviderUtils {

    private static List<Map<String, String>> list = new ArrayList<>();

    @DataProvider
    public static Object[][] getData(Method method) {
        LoginData loginData;
        SearchData searchData;
        TestData testData = null;
        String testName = method.getName();

        if (list.isEmpty())
            list = ExcelUtils.getTestDetails(FrameworkConstants.TEST_DATA_SHEET);

        List<Map<String, String>> smallList = new ArrayList<>(list);

        Predicate<Map<String, String>> isTestNameNotMatching = map -> !map.get("TestCaseName").equalsIgnoreCase(testName);

        smallList.removeIf(isTestNameNotMatching);

        for (Map<String, String> mapData : smallList) {

            loginData = LoginData.builder()
                    .setLoginUsername(mapData.get("username"))
                    .setLoginPassword(mapData.get("password"))
                    .build();

            searchData = SearchData.builder()
                    .setSearchText(mapData.get("searchTerm"))
                    .build();

            testData = TestData.builder()
                    .setLoginData(loginData)
                    .setSearchData(searchData)
                    .build();
        }
        return new Object[][]{
                {testData}
        };
    }
}

