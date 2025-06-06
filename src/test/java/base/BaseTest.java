package base;

import com.khanhppn.driver.factory.DriverFactory;
import com.khanhppn.driver.manager.DeviceManager;
import com.khanhppn.driver.manager.DriverManager;
import com.khanhppn.driver.manager.PlatformManager;
import com.khanhppn.enums.MobilePlatformName;
import com.khanhppn.utils.AppiumServerManager;
import com.khanhppn.utils.screenRecording.ScreenRecordingService;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.util.Objects;

public class BaseTest {

    protected BaseTest() {
    }

    @BeforeSuite(alwaysRun = true)
    protected void beforeSuite() {
        AppiumServerManager.startAppiumServer();
    }

    @Parameters({"platformName", "udid", "deviceName", "systemPort", "chromeDriverPort", "emulator", "wdaLocalPort",
            "webkitDebugProxyPort"})

    @BeforeMethod
    protected void setUp(String platformName, String udid, String deviceName,
                         @Optional("androidOnly") String systemPort,
                         @Optional("androidOnly") String chromeDriverPort,
                         @Optional("androidOnly") String emulator,
                         @Optional("iOSOnly") String wdaLocalPort,
                         @Optional("iOSOnly") String webkitDebugProxyPort) {
        PlatformManager.setPlatformName(platformName);
        DeviceManager.setDeviceName(deviceName);
        if (Objects.isNull(DriverManager.getDriver())) {
            DriverFactory.initializeDriver(MobilePlatformName.valueOf(platformName.toUpperCase()), deviceName, udid,
                    Integer.parseInt(systemPort), emulator);
        }
        ScreenRecordingService.startRecording();
    }

    @AfterMethod
    protected void tearDown(ITestResult result) {
        ScreenRecordingService.stopRecording(result.getName());
        DriverFactory.quitDriver();
    }

    @AfterSuite(alwaysRun = true)
    protected void afterSuite() {
        AppiumServerManager.stopAppiumServer();
    }
}
