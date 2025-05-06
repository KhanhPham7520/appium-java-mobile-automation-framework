package com.khanhppn.pages;

import com.khanhppn.pages.screen.ScreenActions;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public final class MenuPage extends ScreenActions {

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Menu\"]/android.view.ViewGroup/android.widget.ImageView")
    private static MobileElement menuIcon;

    public SettingsPage pressMenuIcon() {
        click(menuIcon, "Menu icon");
        return new SettingsPage();
    }
}
