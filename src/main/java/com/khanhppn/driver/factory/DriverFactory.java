package com.khanhppn.driver.factory;

import com.khanhppn.driver.Drivers;
import com.khanhppn.driver.manager.DriverManager;
import com.khanhppn.enums.MobilePlatformName;
import custom.exceptions.DriverInitializationException;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DriverFactory {

    public static void initializeDriver(MobilePlatformName mobilePlatformName, String deviceName, String udid, int port,
                                        String emulator) {
        AppiumDriver<MobileElement> driver;
        switch (mobilePlatformName) {
            case ANDROID:
                driver = Drivers.createAndroidDriverForNativeApp(deviceName, udid, port, emulator);
                break;
            case ANDROID_WEB:
                driver = Drivers.createAndroidDriverForWeb(deviceName, udid, port, emulator);
                break;
            case IOS:
                driver = Drivers.createIOSDriverForNativeApp(deviceName, udid, port);
                break;
            case IOS_WEB:
                driver = Drivers.createIOSDriverForWeb(deviceName, udid, port);
                break;
            default:
                throw new DriverInitializationException(
                        "Platform name " + mobilePlatformName + " is not found. Please check the platform name");
        }
        DriverManager.setAppiumDriver(driver);
    }

    public static void quitDriver() {
        if (Objects.nonNull(DriverManager.getDriver())) {
            DriverManager.getDriver().quit();
            DriverManager.unload();
        }
    }
}
