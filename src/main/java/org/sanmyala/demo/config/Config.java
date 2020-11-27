package org.sanmyala.demo.config;


import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.sanmyala.actions.IBrowserActions;
import org.sanmyala.config.FolderManager;
import org.sanmyala.config.TestInstance;
import org.sanmyala.config.UserVar;
import org.sanmyala.data.TestUtils;
import org.sanmyala.drivers.Browsers;
import org.sanmyala.drivers.ChromeBrowser;
import org.sanmyala.drivers.UIDriver;
import org.sanmyala.model.SmInstance;
import org.sanmyala.reporting.ReportHelper;
import org.sanmyala.reporting.build.BuildVar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

public class Config extends SmInstance {
    Logger logger = LoggerFactory.getLogger(Config.class);

    public TestInstance testInstance;
    public JSONObject testcase;

    @BeforeClass
    public void setup(ITestContext context){
        logger.info("Test variables are initiated");
        testInstance = new TestInstance();
        setTestInstance(testInstance);
        testInstance.setTestCaseId(testcase.get("scenarioId") instanceof String?Long.parseLong(testcase.getString("scenarioId")):testcase.getLong("scenarioId"));
        testInstance.setTestData(testcase);
        testInstance.setTestDescription(testcase.get("scenarioId")+"_"+testcase.getString("description"));
        testInstance.setAppUrl(TestUtils.getAppProps(context.getName(), UserVar.getTestEnv()).getString("url"));
        String loggerDirectory = UserVar.getLogsLocation()+"/"+ BuildVar.getBuildId();
        FolderManager.createDirectory(loggerDirectory);
        String logFile = (testcase.get("scenarioId")+"_"+testcase.getString("description")).replaceAll("[^a-zA-Z0-9]", "_");
        this.testInstance.setLoggerPath(loggerDirectory+"/"+logFile);
        MDC.put("scenarioName", this.testInstance.getLoggerPath());

        setDriver();
    }


    /**
     * This function is used to initiate a webdriver and open an application.
     */
    public void setDriver(){
        logger.info("Initiating Web Browser");
        //this.testInstance.setWebDriver(Utilities.getDriver(Browsers.valueOf("CHROME")).getRemoteWebDriver(Utilities.selenoidCaps(Browsers.valueOf("CHROME"))));
        this.testInstance.setWebDriver(Utilities.getDriver(Browsers.valueOf("CHROME")).getLocalDriver());
        this.testInstance.getWebDriver().manage().window().maximize();
        this.testInstance.getWebDriver().get(this.testInstance.getAppUrl());
    }
}
