package org.sanmyala.demo.tests;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.json.JSONObject;
import org.sanmyala.data.provider.TestDataProvider;
import org.sanmyala.demo.config.Config;
import org.sanmyala.demo.config.Utilities;
import org.sanmyala.demo.pages.Checkout;
import org.sanmyala.drivers.Browsers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.testng.ITest;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import static net.logstash.logback.argument.StructuredArguments.kv;

public class AutomationPractice extends Config implements ITest {

    private Logger logger = LoggerFactory.getLogger(AutomationPractice.class);

    @Factory(dataProvider = "dataProvider", dataProviderClass = TestDataProvider.class)
    public AutomationPractice(JSONObject testcase){
        this.testcase = testcase;
        System.out.println(this.testInstance);
    }

    @Test
    public void shopApparels(){
        try{
            logger.info("Test Started for us", kv("testcase", testcase.getString("description")));
            Checkout checkoutPage = new Checkout(testInstance);
            checkoutPage.selectCategory(testcase.getString("category"));
            checkoutPage.selectProduct(testcase.getString("product"));
            checkoutPage.addItemToCart(testcase.getString("qty"));
            checkoutPage.validateCartTotal();
            checkoutPage.addToCart();
            checkoutPage.validateItemTotal();
            checkoutPage.validateCheckoutTotal();
            checkoutPage.proceedToCheckout();
            checkoutPage.signIn(testcase.getString("username"), testcase.getString("password"));
            checkoutPage.proceedFromAddress();
            checkoutPage.proceedFromCarrier();
            checkoutPage.payByCheque();
            checkoutPage.confirmOrder();
        }catch(Throwable e){
            testInstance.setFailureMessage(e.getMessage());
            testInstance.setStacktrace(ExceptionUtils.getStackTrace(e));
            throw e;
        }finally{
            System.out.println(testInstance.getValidations());
            MDC.clear();
        }
    }

    @Override
    public String getTestName() {
        return this.testcase.getString("scenarioId")+"_"+this.testcase.getString("description");
    }
}
