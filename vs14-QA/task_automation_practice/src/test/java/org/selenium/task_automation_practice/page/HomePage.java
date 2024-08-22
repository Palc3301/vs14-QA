package org.selenium.task_automation_practice.page;

import org.openqa.selenium.By;
import org.selenium.task_automation_practice.selenium.Interactions;

public class HomePage extends Interactions {

    private static final By btnContactUs = By.cssSelector("#contact-link > a");
    private static final By btnSignIn = By.cssSelector("#header > div.nav > div > div > nav > div.header_user_info > a");
    private static final By logo = By.cssSelector("#header_logo > a");
    private static final By campoBusca = By.cssSelector("#search_query_top");
    private static final By btnSearch = By.cssSelector("#searchbox > button");
    private static final By cart = By.cssSelector("#header > div:nth-child(3) > div > div > div:nth-child(3) > div > a");
    private static final By womenMenu = By.cssSelector("#block_top_menu > ul > li:nth-child(1)");
    private static final By dressesMenu = By.cssSelector("#block_top_menu > ul > li:nth-child(2)");
    private static final By tshirtsMenu = By.cssSelector("#block_top_menu > ul > li:nth-child(3)");
    private static final By blogMenu = By.cssSelector("#block_top_menu > ul > li:nth-child(4)");

    private static final By btnBestSellers = By.cssSelector("#home-page-tabs > li:nth-child(2) > a");

    private static final By btnPopular = By.cssSelector("#home-page-tabs > li:nth-child(1) > a");
    private static final By btnQuickView = By.cssSelector
            ("#blockbestsellers > li.ajax_block_product.col-xs-12.col-sm-4.col-md-3.first-in-line.first-item-of-tablet-line.first-item-of-mobile-line.hovered > div > div.left-block > div > a.quick-view > span ");
    private static final By btnMore = By.cssSelector
            ("#blockbestsellers > li.ajax_block_product.col-xs-12.col-sm-4.col-md-3.last-item-of-mobile-line.hovered > div > div.right-block > div.button-container > a > span");
    private static final By btnShopNow = By.cssSelector("#homeslider > li:nth-child(2) > div > p:nth-child(3)");
    private static final By campoNewsletter = By.cssSelector("#newsletter-input");
    private static final By btnEnviarNewsletter = By.cssSelector("#newsletter_block_left > div > form > div > button");


    public void clickContactUs() {
        click(btnContactUs);
    }

    public void clickSignIn() {
        click(btnSignIn);
    }

    public void clickLogo() {
        click(logo);
    }

    public void enterSearchQuery(String query) {
        sendKeys(campoBusca, query);
    }

    public void clickSearchButton() {
        click(btnSearch);
    }

    public void clickCart() {
        click(cart);
    }

    public void clickWomenMenu() {
        click(womenMenu);
    }

    public void clickDressesMenu() {
        click(dressesMenu);
    }

    public void clickTShirtsMenu() {
        click(tshirtsMenu);
    }

    public void clickBlogMenu() {
        click(blogMenu);
    }

    public void clickBestSellers() {
        click(btnBestSellers);
    }

    public void clickPopular() {
        click(btnPopular);
    }

    public void clickQuickView() {
        click(btnQuickView);
    }

    public void clickMore() {
        click(btnMore);
    }

    public void clickShopNow() {
        click(btnShopNow);
    }

    public void enterNewsletterEmail(String email) {
        sendKeys(campoNewsletter, email);
    }

    public void clickSendNewsletter() {
        click(btnEnviarNewsletter);
    }

    public boolean isSearchResultVisible() {
        return true;
    }

    public boolean isSearchResultVisibleF() {
        return false;
    }

}
