package org.sanmyala.demo.tests;

import org.json.JSONObject;
import org.sanmyala.actions.BrowserActions;
import org.sanmyala.actions.ElementActions;
import org.sanmyala.actions.IBrowserActions;
import org.sanmyala.actions.MouseActions;
import org.sanmyala.data.provider.TestDataProvider;
import org.sanmyala.config.TestInstance;
import org.sanmyala.demo.pages.SingleFormPage;
import org.sanmyala.drivers.ChromeBrowser;
import org.testng.ITest;
import org.testng.annotations.*;

/**
 * Test case used for execution!!!
 */
public class SingleFormTest implements ITest {

    private TestInstance testInstance;
    private IBrowserActions browserActions;
    private String testApp = "http://automationpractice.com/index.php";
    private JSONObject testCase;

    @Factory(dataProvider = "dataProvider", dataProviderClass = TestDataProvider.class)
    public SingleFormTest(JSONObject testCase){
        this.testCase=testCase;
    }

    @Test
    public void singleFormScenario(){
        try{
            this.testCase = testCase;
            System.out.println(this.testCase);
            testInstance = new TestInstance(new BrowserActions(), new ElementActions(), new MouseActions(), new ChromeBrowser().getLocalDriver());
            browserActions.goToUrl(testInstance.getWebDriver(), testApp);
            SingleFormPage singleFormPage = new SingleFormPage(testInstance);
            singleFormPage.singleInputField().twoInputFields();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("Test is completed successfully!!!");
        }
    }

    public String getTestName() {
        return "Demo Test case";
    }
}
