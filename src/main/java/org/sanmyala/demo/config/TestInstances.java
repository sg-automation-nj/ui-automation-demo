package org.sanmyala.demo.config;

import org.openqa.selenium.WebDriver;
import org.sanmyala.actions.*;

public class TestInstances {

    private IBrowserActions browserActions;
    private IElementActions elementActions;
    private IMouseActions mouseActions;
    private WebDriver webDriver;
    private String loggerPath;


    public TestInstances(){
        this.browserActions=new BrowserActions();
        this.elementActions = new ElementActions();
        this.mouseActions = new MouseActions();
    }

    public TestInstances(IBrowserActions browserActions, IElementActions elementActions, IMouseActions mouseActions, WebDriver driver){
        this.browserActions = browserActions;
        this.elementActions = elementActions;
        this.mouseActions = mouseActions;
        this.webDriver = driver;
    }

    public IBrowserActions getBrowserActions() {
        return browserActions;
    }

    public IElementActions getElementActions() {
        return elementActions;
    }

    public IMouseActions getMouseActions() {
        return mouseActions;
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public void setWebDriver(WebDriver driver){
        this.webDriver = driver;
    }

    public void setLoggerPath(String loggerPath){
        this.loggerPath = loggerPath;
    }

    public String getLoggerPath(){
        return this.loggerPath;
    }
}
