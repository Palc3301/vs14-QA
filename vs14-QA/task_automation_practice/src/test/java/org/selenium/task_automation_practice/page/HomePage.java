package org.selenium.task_automation_practice.page;

import org.openqa.selenium.By;
import org.selenium.task_automation_practice.factory.selenium.Interactions;
import org.selenium.task_automation_practice.util.DataFakerGeneretor;

public class HomePage extends Interactions {

    private static final By btnContactUs = By.cssSelector("#contact-link > a");
    private static final By btnSignIn = By.cssSelector("#header > div.nav > div > div > nav > div.header_user_info > a");
    private static final By logo = By.cssSelector("#header_logo > a");
    private static final By campoBusca = By.cssSelector("#search_query_top");
    private static final By btnSearch = By.cssSelector("#searchbox > button");
    private static final By womenMenu = By.cssSelector("#block_top_menu > ul > li:nth-child(1)");
    private static final By dressesMenu = By.cssSelector("#block_top_menu > ul > li:nth-child(2)");
    private static final By tshirtsMenu = By.cssSelector("#block_top_menu > ul > li:nth-child(3)");
    private static final By blogMenu = By.cssSelector("#block_top_menu > ul > li:nth-child(4)");

    private static final By btnBestSellers = By.cssSelector("#home-page-tabs > li:nth-child(2) > a");
    private static final By messageSubscriptionNewsletter = By.cssSelector("#columns > p");

  private static final By btnPopular = By.cssSelector("#home-page-tabs > li:nth-child(1) > a");
    private static final By btnQuickView = By.cssSelector
            ("#blockbestsellers > li:nth-child(2)");
    private static final By btnMore = By.cssSelector
            ("#blockbestsellers > li.ajax_block_product.col-xs-12.col-sm-4.col-md-3.last-item-of-mobile-line.hovered > div > div.right-block > div.button-container > a > span");
    private static final By btnShopNow = By.cssSelector("#homeslider > li:nth-child(2) > div > p:nth-child(3)");
    private static final By campoNewsletter = By.cssSelector("#newsletter-input");
    private static final By btnCart = By.cssSelector("#header > div:nth-child(3) > div > div > div:nth-child(3) > div > a > b");
    private static final By btnSubmitNewsletterEmail = By.cssSelector("#newsletter_block_left > div > form > div > button");

    private static final By textCartIsEmpty = By.cssSelector("#center_column > p");

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
        click(btnCart);
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

    public void hoverProductItem() {
        hoverElement(btnQuickView);
    }

    public void clickMore() {
        click(btnMore);
    }

    public void clickShopNow() {
        click(btnShopNow);
    }

    public boolean isCartEmpty() {
        return readText(textCartIsEmpty).contains("Your shopping cart is empty.");
    }

    public String enterNewsletterEmail() {
        DataFakerGeneretor dataFaker = new DataFakerGeneretor();
        String email = dataFaker.emailFaker();
        sendKeys(campoNewsletter, email);

        click(btnSubmitNewsletterEmail);

        return readText(messageSubscriptionNewsletter);
    }

    public String enterNewsletterInvalidEmail() {
        DataFakerGeneretor dataFaker = new DataFakerGeneretor();
        String email = dataFaker.emailFaker().replace("@", "");
        sendKeys(campoNewsletter, email);

        click(btnSubmitNewsletterEmail);

        return readText(messageSubscriptionNewsletter);
    }

    public String enterNewsletterEmailVazio() {
        String email = "";
        sendKeys(campoNewsletter, email);

        click(btnSubmitNewsletterEmail);

        return readText(messageSubscriptionNewsletter);
    }

    public boolean isSearchResultVisible() {
        return true;
    }

    public boolean isSearchResultVisibleF() {
        return false;
    }

}
