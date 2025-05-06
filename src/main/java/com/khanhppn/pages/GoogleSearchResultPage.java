package com.khanhppn.pages;

import com.khanhppn.driver.manager.DriverManager;
import com.khanhppn.pages.screen.ScreenActions;

public final class GoogleSearchResultPage extends ScreenActions {

    public String getSearchResultsPageTitle() {
        return DriverManager.getDriver().getTitle();
    }

}
