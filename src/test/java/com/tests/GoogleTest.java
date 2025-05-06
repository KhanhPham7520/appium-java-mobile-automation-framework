package com.tests;

import base.BaseTest;
import com.khanhppn.constants.StringConstants;
import com.khanhppn.driver.manager.DriverManager;
import com.khanhppn.model.TestData;
import com.khanhppn.enums.CategoryType;
import com.khanhppn.enums.ConfigJson;
import com.khanhppn.pages.GoogleSearchPage;
import custom.annotations.FrameworkAnnotation;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.khanhppn.utils.configs.JsonUtils.getConfig;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GoogleTest extends BaseTest {

    @FrameworkAnnotation(author = "User-1",
            category = {CategoryType.REGRESSION, CategoryType.SANITY, CategoryType.SMOKE})
    @Test(description = "Google search")
    public void googleSearch(TestData data) {
        DriverManager.getDriver().get(getConfig(ConfigJson.URL));
        String searchResultsPageTitle = new GoogleSearchPage()
                .performSearch(data.getSearchData().getSearchText())
                .getSearchResultsPageTitle();

        Assert.assertEquals(searchResultsPageTitle, StringConstants.SEARCH_RESULTS_PAGE_TITLE);
    }
}
