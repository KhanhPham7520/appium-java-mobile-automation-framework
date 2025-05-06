package com.khanhppn.utils;

import com.khanhppn.constants.FrameworkConstants;
import com.khanhppn.enums.ConfigProperties;
import com.khanhppn.utils.configs.PropertyUtils;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AppiumServerManager {

    private static AppiumDriverLocalService service;

    static boolean checkIfServerIsRunning(int port) {
        boolean isServerRunning = false;
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.close();
        } catch (IOException e) {
            isServerRunning = true;
        }
        return isServerRunning;
    }

    public static void startAppiumServer() {
        if (PropertyUtils.getPropertyValue(ConfigProperties.START_APPIUM_SERVER).equalsIgnoreCase("yes")) {
            if (!AppiumServerManager.checkIfServerIsRunning(FrameworkConstants.APPIUM_SERVER_PORT)) {
                //Build the Appium service
                AppiumServiceBuilder builder = new AppiumServiceBuilder();
                builder.usingDriverExecutable(new File("/opt/homebrew/bin/node"))
                        .withAppiumJS(new File("/opt/homebrew/lib/node_modules/appium/build/lib/main.js"))
                        .withIPAddress(FrameworkConstants.APPIUM_SERVER_HOST)
                        .usingPort(FrameworkConstants.APPIUM_SERVER_PORT)
                        .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                        .withArgument(GeneralServerFlag.ALLOW_INSECURE, "chromedriver_autodownload")
                        .withLogFile(new File(FrameworkConstants.getAppiumServerLogsPath()));
                //Start the server with the builder
                service = AppiumDriverLocalService.buildService(builder);
                service.start();
                service.clearOutPutStreams();
            }
        }
    }

    public static void stopAppiumServer() {
        if (PropertyUtils.getPropertyValue(ConfigProperties.START_APPIUM_SERVER).equalsIgnoreCase("yes")) {
            service.stop();
            Runtime runtime = Runtime.getRuntime();
            String os = System.getProperty("os.name").toLowerCase();
            try {
                if (os.contains("win")) {
                    runtime.exec("taskkill /F /IM node.exe");
                    runtime.exec("taskkill /F /IM cmd.exe");
                } else if (os.contains("mac") || os.contains("nix") || os.contains("nux")) {
                    runtime.exec("pkill -f node");
                    runtime.exec("pkill -f appium");
                } else {
                    System.out.println("Unsupported OS: " + os);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
