package uiComponents.Screens;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/*This class holds page objects*/
public class Locators extends Base {

    /*Amazon WebPage locators*/
    public static final By searchField = By.id("twotabsearchtextbox");
    public static final By searchButton = By.xpath("//form[@name='site-search']/div[2]");
    public static final By addToCartButton = By.id("add-to-cart-button");
    public static final By cart = By.id("nav-cart");
    public static final By deleteButton = By.cssSelector("span.a-declarative");
    public static final By cartCount = By.id("nav-cart-count");
    public static final By checkoutButton = By.id("sc-buy-box-ptc-button");
    public static final By deliveryButton = By.xpath("//div[@id='address-book-entry-0']/div[2]/span/a");
    public static final By myAccount = By.id("nav-link-yourAccount");
    public static final By signOut = By.id("nav-item-signout");
    public static final By signInOptionOnHader = By.xpath("//a[@id='nav-link-yourAccount']/span");
    public static final By amazonLogo = By.cssSelector("a.a-link-nav-icon");
    public static final By changeProductLink =By.xpath("//div[@class='order-header']/div/a");
    public static final By cartTitle = By.xpath("//div[@id='sc-active-cart']/div/div/h2");

    /*Alert messages locators for invalid/blank field tests*/
    public static final By loginEmail = By.id("ap_email");
    public static final By continueButton = By.id("continue");
    public static final By loginPassword = By.id("ap_password");
    public static final By loginButton = By.id("signInSubmit");
    public static final By missingEmailAlert =By.xpath("//div[@id='auth-email-missing-alert']/div/div");
    public static final By missingPasswordAlert =By.xpath("//div[@id='auth-password-missing-alert']/div/div");
    public static final By incorrectEmailPasswordAlert=By.xpath("//span[@class='a-list-item']");
    public static final By skipAndContinueLink=By.id("ap-account-fixup-phone-skip-link");


    /*This are the getter methods to get WebElement*/

    public static WebElement getSearchField(){
        return driver.findElement(searchField);
    }
    public static WebElement getSearchButton(){
        return driver.findElement(searchButton);
    }
    public static WebElement getAddToCartButton(){
        return driver.findElement(addToCartButton);
    }
    public static WebElement getCart(){
        return driver.findElement(cart);
    }
    public static WebElement getDeleteButton(){
        return driver.findElement(deleteButton);
    }
    public static WebElement getLoginEmail(){
        return driver.findElement(loginEmail);
    }
    public static WebElement getContinueButton(){
        return driver.findElement(continueButton);
    }
    public static WebElement getLoginPassword(){
        return driver.findElement(loginPassword);
    }
    public static WebElement getLoginButton(){
        return driver.findElement(loginButton);
    }
    public static WebElement getcheckoutButton(){
        return driver.findElement(checkoutButton);
    }
    public static WebElement getDeliveryButton(){
        return driver.findElement(deliveryButton);
    }
    public static WebElement getMyAccount(){
        return driver.findElement(myAccount);
    }
    public static WebElement getSignOut(){
        return driver.findElement(signOut);
    }
    public static WebElement getAmazonLogo(){
        return driver.findElement(amazonLogo);
    }
    public static WebElement getSignInOptionOnHader(){
        return driver.findElement(signInOptionOnHader);
    }
    public static WebElement getCartCount(){
        return driver.findElement(cartCount);
    }
    public static WebElement getCartTitle() {
        return driver.findElement(cartTitle);
    }
    public static WebElement getMissingEmailAlert(){
        return driver.findElement(missingEmailAlert);
    }
    public static WebElement getMissingPasswordAlert(){
        return driver.findElement(missingPasswordAlert);
    }
    public static WebElement getIncorrectEmailPasswordAlert(){
        return driver.findElement(incorrectEmailPasswordAlert);
    }
    public static WebElement getskipAndContinueLink(){
        return driver.findElement(skipAndContinueLink);
    }
    public static WebElement selectProduct(String productName){
        System.out.println("Selecting "+productName+" Product from the search results");
        return driver.findElement(By.xpath("//*[contains(text(),'"+productName+"')]"));
    }
    public static WebElement getChangeProductLink(){
        return driver.findElement(changeProductLink);
    }
    public static WebElement getDeleteProduct(){
        return driver.findElement(By.xpath("//*[contains(text(),'Delete')]"));
    }




}
