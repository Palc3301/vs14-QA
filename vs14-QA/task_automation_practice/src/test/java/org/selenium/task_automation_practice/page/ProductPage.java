package org.selenium.task_automation_practice.page;

import org.openqa.selenium.By;

import org.selenium.task_automation_practice.selenium.Interactions;

public class ProductPage extends Interactions{

    private static final By btnSelectProduct = By.cssSelector
    ("#center_column > ul > li.ajax_block_product.col-xs-12.col-sm-6.col-md-4.last-in-line.first-item-of-tablet-line.last-item-of-mobile-line > div");
    private static final By btnProduct = By.cssSelector("#center_column > ul > li.ajax_block_product.col-xs-12.col-sm-6.col-md-4.last-in-line.first-item-of-tablet-line.last-item-of-mobile-line > div > div.right-block > div.button-container > a");
    private static final By btnAddBlueDress = By.cssSelector("#color_14");
    private static final By btnAddToCart = By.cssSelector("#add_to_cart > button > span");
    private static final By btnProceedToCheckout = By.cssSelector("#layer_cart > div.clearfix > div.layer_cart_cart.col-xs-12.col-md-6 > div.button-container > a");

    private static final By textCartContains = By.cssSelector("#layer_cart > div.clearfix > div.layer_cart_product.col-xs-12.col-md-6 > h2");

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

    public Boolean validateCartContains() {
        return readText(textCartContains).contains("Your shopping cart is empty.");
    }

}
