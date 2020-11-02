package org.sanmyala.demo.config;

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
}
