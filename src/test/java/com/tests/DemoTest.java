package com.tests;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class DemoTest {

    @Test
    @Parameters({"emulator", "platformName", "udid", "deviceName", "systemPort"})
    public void sampleTest(String emulator, String platformName, String udid, String deviceName, String systemPort) {

        System.out.println("Emulator: " + emulator);
        System.out.println("Platform: " + platformName);
        System.out.println("UDID: " + udid);
        System.out.println("Device: " + deviceName);
        System.out.println("Port: " + systemPort);
    }
}
