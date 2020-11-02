package org.sanmyala.demo.config;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.sanmyala.drivers.*;

public class Utilities {

    public static UIDriver getDriver(Browsers browser){
        switch(browser){
            case CHROME:
                return new ChromeBrowser();
            case FIREFOX:
                return new FirefoxBrowser();
            case CHROME_HEADLESS:
                return new ChromeHeadlessBrowser();
            case FIREFOX_HEADLESS:
                return new FirefoxBrowser();
            default:
                throw new RuntimeException("Please pass a valid browser type");
        }
    }

    public static DesiredCapabilities selenoidCaps(Browsers browser){
        DesiredCapabilities caps = new DesiredCapabilities();
        switch(browser){
            case CHROME:
                ChromeOptions options = new ChromeOptions();
                options.addArguments("start-maximized");
                caps.setCapability("browserName", "chrome");
                caps.setCapability("browserVersion", "86.0");
                caps.setCapability("enableVNC", true);
                caps.setCapability("enableVideo", false);
                caps.setCapability("ChromeOptions", options);
                return caps;
        }
        return null;
    }
}
