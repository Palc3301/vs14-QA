package org.selenium.task_automation_practice.page;

import org.openqa.selenium.By;

import org.selenium.task_automation_practice.factory.selenium.Interactions;
import org.selenium.task_automation_practice.util.DataFakerGeneretor;

public class ProductPaymentPage extends Interactions{

    private DataFakerGeneretor dataFakerGeneretor = new DataFakerGeneretor();


    private static final By btnSelectProduct = By.cssSelector
    ("#center_column > ul > li.ajax_block_product.col-xs-12.col-sm-6.col-md-4.last-in-line.first-item-of-tablet-line.last-item-of-mobile-line > div");
    private static final By btnProduct = By.cssSelector("#center_column > ul > li.ajax_block_product.col-xs-12.col-sm-6.col-md-4.last-in-line.first-item-of-tablet-line.last-item-of-mobile-line > div > div.right-block > div.button-container > a");
    private static final By btnAddBlueDress = By.cssSelector("#color_14");
    private static final By btnAddToCart = By.cssSelector("#add_to_cart > button > span");
    private static final By btnProceedToCheckout = By.cssSelector("#layer_cart > div.clearfix > div.layer_cart_cart.col-xs-12.col-md-6 > div.button-container > a");
    private static final By btnCheckoutToPayment = By.cssSelector("#center_column > p.cart_navigation.clearfix > a.button.btn.btn-default.standard-checkout.button-medium");

    private static final By btnCheckoutPaymentLorem = By.cssSelector("#center_column > form > p > button");

    private static final By btnCheckoutAddressToPayment = By.cssSelector("#form > p > button > span");
    private static final By textCartContains = By.cssSelector("#layer_cart > div.clearfix > div.layer_cart_product.col-xs-12.col-md-6 > h2");
    private static final By areaTextToShipping = By.cssSelector("#ordermsg > textarea");

    private static final By btnTermsOfService = By.cssSelector("#form > div > p.checkbox > label");
    private static final By btnShippingCheckout = By.cssSelector("#form > p > button");
    private static final By btnPaymentByBankWire = By.cssSelector("#HOOK_PAYMENT > div:nth-child(1) > div > p > a");

    private static final By btnConfirmOrder = By.cssSelector("#cart_navigation > button > span");
    private static final By btnCloseTermsOfService = By.cssSelector("#order > div.fancybox-overlay.fancybox-overlay-fixed > div > div > a");

    public void clickProduct() {
        click(btnSelectProduct);
    }

    public void clickInProduct() {
        click(btnProduct);
    }

    public void clickAddBlueDress() {
        click(btnAddBlueDress);
    }

    public void clickAddToCart() {
        click(btnAddToCart);
    }

    public void clickProceedToCheckout() {
        click(btnProceedToCheckout);
    }

    public void clickCheckoutToPayment() {
        click(btnCheckoutToPayment);
    }

    public void clickCheckoutPaymentLorem() {
        click(btnCheckoutPaymentLorem);
    }

    public void clickCheckoutAddressToPayment() {
        click(btnCheckoutAddressToPayment);
    }

    public void clickTermsOfService() {
        click(btnTermsOfService);
    }

    public void clickShippingCheckout() {
        click(btnShippingCheckout);
    }

    public void clickPaymentByBankWire() {
        click(btnPaymentByBankWire);
    }

    public void clickConfirmOrder() {
        click(btnConfirmOrder);
    }

    public void fillTextAreaShipping() {
        click(areaTextToShipping);
        sendKeys(areaTextToShipping, dataFakerGeneretor.textAreaFaker());
    }

    public void closeTermsOfService() {
        click(btnCloseTermsOfService);
    }

}
