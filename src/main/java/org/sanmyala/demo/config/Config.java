package org.sanmyala.demo.config;


import org.json.JSONObject;
import org.sanmyala.config.FolderManager;
import org.sanmyala.config.TestInstance;
import org.sanmyala.config.UserVar;
import org.sanmyala.drivers.ChromeBrowser;
import org.sanmyala.model.SmInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

public class Config extends SmInstance {
    Logger logger = LoggerFactory.getLogger(Config.class);

    public TestInstance testInstance;
    public JSONObject testcase;

    @BeforeClass
    public void setup(){
        testInstance = new TestInstance();
        setTestInstance(testInstance);
        testInstance.setTestCaseId(testcase.get("scenarioId") instanceof String?Long.parseLong(testcase.getString("scenarioId")):testcase.getLong("scenarioId"));
        testInstance.setTestData(testcase);
        testInstance.setTestDescription(testcase.get("scenarioId")+"_"+testcase.getString("description"));
        FolderManager.createDirectory(UserVar.getLogsLocation());
        String logFile = (testcase.get("scenarioId")+"_"+testcase.getString("description")).replaceAll("[^a-zA-Z0-9]", "_");
        this.testInstance.setLoggerPath(UserVar.getLogsLocation()+"/"+logFile);
        MDC.put("scenarioName", this.testInstance.getLoggerPath());
    }
}
