package org.sanmyala.demo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.sanmyala.actions.IElementActions;
import org.sanmyala.actions.IMouseActions;
import org.sanmyala.config.TestInstance;
import org.sanmyala.constants.Identifiers;
import org.testng.Assert;

import java.util.List;

public class Checkout {

    private TestInstance testInstance;
    private WebDriver driver;
    private IElementActions elementAction;
    private IMouseActions mouseAction;

    public Checkout(TestInstance testInstance){
        this.testInstance = testInstance;
        this.driver=this.testInstance.getWebDriver();
        this.elementAction = testInstance.getElementActions();
        this.mouseAction = testInstance.getMouseActions();
    }


    /**
     * function is used to select a category from landing page
     * @param category
     */
    public void selectCategory(String category){
        switch(category.toUpperCase()){
            case "WOMEN":
                mouseAction.mouseOverOnAnElement(driver, Identifiers.XPATH, "//a[@title='Women']");
                elementAction.clickElement(driver, Identifiers.XPATH, "//li[@class='sfHover']//ul//li//a[@title='T-shirts'][contains(normalize-space(),'T-shirts')]", "Element clicked successfully!!!");
                break;
            case "DRESSES":
                mouseAction.mouseOverOnAnElement(driver, Identifiers.XPATH, "//body/div[@id='page']/div[@class='header-container']/header[@id='header']/div/div[@class='container']/div[@class='row']/div[@id='block_top_menu']/ul[@class='sf-menu clearfix menu-content sf-js-enabled sf-arrows']/li[2]/a[1]");
                elementAction.clickElement(driver, Identifiers.XPATH, "//li[@class='sfHover']//ul[@class='submenu-container clearfix first-in-line-xs']//li//a[@title='Casual Dresses'][contains(normalize-space(),'Casual Dresses')]");
                break;
            case "TSHIRT":
                elementAction.clickElement(driver, Identifiers.XPATH, "//li[@class='sfHover']//ul[@class='submenu-container clearfix first-in-line-xs']//li//a[@title='Casual Dresses'][contains(normalize-space(),'Casual Dresses')]");
                break;
        }
    }

    public void selectProduct(String productName){
        //img[@title='Faded Short Sleeve T-shirts']
        mouseAction.mouseOverOnAnElement(driver, Identifiers.XPATH, "//a[@title='"+productName+"']");
        elementAction.clickElement(driver, Identifiers.XPATH, "//span[text()='More']");
    }

    public void addItemToCart(String qty){
        elementAction.setText(driver, Identifiers.ID, "quantity_wanted", qty);
        elementAction.clickElement(driver, Identifiers.XPATH, "//button[@name='Submit']//span");
    }

    public void validateCartTotal(){
        String productTotal = elementAction.waitForAnElementToBeClickable(driver, elementAction.getBy(Identifiers.XPATH, "//span[@class='ajax_block_products_total']"), elementAction.getLongWaitTime()).getText().replace("$", "");
        String shipping = elementAction.waitForAnElementToBeClickable(driver, elementAction.getBy(Identifiers.XPATH, "//span[@class='ajax_cart_shipping_cost']"), elementAction.getLongWaitTime()).getText().replace("$", "");
        String total = elementAction.waitForAnElementToBeClickable(driver, elementAction.getBy(Identifiers.XPATH, "//span[@class='ajax_block_cart_total']"), elementAction.getLongWaitTime()).getText().replace("$", "");
        Assert.assertEquals(Double.parseDouble(productTotal)+Double.parseDouble(shipping), Double.parseDouble(total));
    }

    public void addToCart(){
        elementAction.clickElement(driver, Identifiers.XPATH, "//a[@title='Proceed to checkout']//span");
    }

    public void validateItemTotal(){
        List<WebElement> cartItems = elementAction.getWebElements(driver, elementAction.getBy(Identifiers.XPATH, "//td[@data-title='Unit price']//span/span"));
        List<WebElement> cartQty = elementAction.getWebElements(driver, elementAction.getBy(Identifiers.XPATH, "//input[contains(@name,'quantity') and not(contains(@name,'hidden'))]"));

        double cartTotal = 0;
        for(int counter=0; counter<cartItems.size();counter++){
            double itemTotal =  Double.parseDouble(elementAction.waitForAnElementToBeClickable(driver, cartItems.get(counter), elementAction.getLongWaitTime()).getText().replace("$", ""));
            double cartQuantity =  Double.parseDouble(elementAction.waitForAnElementToBeClickable(driver, cartQty.get(counter), elementAction.getLongWaitTime()).getAttribute("value"));
            cartTotal = cartTotal + (itemTotal * cartQuantity);
        }
        double totalProduct = Double.parseDouble(elementAction.getElementText(driver, elementAction.getBy(Identifiers.XPATH, "//td[@id='total_product']")).replace("$", ""));
        Assert.assertEquals(cartTotal, totalProduct);
    }

    public void validateCheckoutTotal(){
        double totalProduct = Double.parseDouble(elementAction.getElementText(driver, elementAction.getBy(Identifiers.XPATH, "//td[@id='total_product']")).replace("$", ""));
        double shippingTotal = Double.parseDouble(elementAction.getElementText(driver, elementAction.getBy(Identifiers.XPATH, "//td[@id='total_shipping']")).replace("$", ""));
        double totalWithoutTax = Double.parseDouble(elementAction.getElementText(driver, elementAction.getBy(Identifiers.XPATH, "//td[@id='total_price_without_tax']")).replace("$", ""));
        Assert.assertEquals(totalProduct+shippingTotal, totalWithoutTax);

        double taxAmount = Double.parseDouble(elementAction.getElementText(driver, elementAction.getBy(Identifiers.XPATH, "//td[@id='total_tax']")).replace("$", ""));
        double totalCart = Double.parseDouble(elementAction.getElementText(driver, elementAction.getBy(Identifiers.XPATH, "//td[@id='total_price_container']")).replace("$", ""));
        Assert.assertEquals(totalWithoutTax+taxAmount, totalCart);
    }

    public void proceedToCheckout(){
        elementAction.clickElement(driver, Identifiers.XPATH, "//a[@class='button btn btn-default standard-checkout button-medium']//span");
    }

    public void signIn(String username, String password){
        elementAction.setText(driver, Identifiers.XPATH, "//input[@id='email']", username);
        elementAction.setText(driver, Identifiers.XPATH, "//input[@id='passwd']", password);
        elementAction.clickElement(driver, Identifiers.XPATH, "//button[@id='SubmitLogin']//span");
    }

    public void proceedFromAddress(){
        elementAction.clickElement(driver, Identifiers.XPATH, "//button[@name='processAddress']//span");
    }

    public void proceedFromCarrier(){
        elementAction.waitForAnElementToBePresent(driver, elementAction.getBy(Identifiers.ID, "cgv"), elementAction.getLongWaitTime()).click();
        elementAction.clickElement(driver, Identifiers.XPATH, "//button[@name='processCarrier']//span");
    }

    public void payByCheque(){
        elementAction.clickElement(driver, Identifiers.XPATH, "//a[@title='Pay by check.']");
    }

    public void confirmOrder(){
        elementAction.clickElement(driver, Identifiers.XPATH, "//button[@class='button btn btn-default button-medium']");
    }
}
