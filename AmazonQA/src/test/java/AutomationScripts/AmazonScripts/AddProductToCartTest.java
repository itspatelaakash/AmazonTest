package AutomationScripts.AmazonScripts;

import base.Base;
import logging.Verify;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import uiComponents.Screens.Locators;
import utilities.AppConstants;

import java.util.Iterator;
import java.util.Set;

public class AddProductToCartTest extends Base {
    int cartCount = 0;

    @Test
    public void amazonAddToCartFlowTest() throws Exception {

        Verify.verifyTrue(Locators.getSignInOptionOnHader().isDisplayed(),
                "Verifying user is on Home Page");

        //Searching and selecting product from property file & Verifying product is listed in search results
        searchProduct(prop.getProperty("category1"), prop.getProperty("product1"));
        Verify.verifyTrue(Locators.selectProduct(prop.getProperty("product1")).isDisplayed(),
                "Verifying searched product is listed in search results");
        Locators.selectProduct(prop.getProperty("product1")).click();
        //Switching window
        switchWindow();
        Verify.verifyTrue(Locators.getAddToCartButton().isDisplayed(),
                "Verifying user is successfully navigated to child window");
        addProductToCart();
        Verify.verifyEquals(Locators.getCartCount().getText(), String.valueOf(cartCount),
                "Verifying item is added to the cart and count is updated");

        //Adding 2nd product to cart and cart count is updated
        searchProduct(prop.getProperty("category2"), prop.getProperty("product2"));
        Locators.selectProduct(prop.getProperty("product2")).click();
        switchWindow();
        addProductToCart();
        Verify.verifyEquals(Locators.getCartCount().getText(), String.valueOf(cartCount),
                "Verifying second item is added to the cart and count is updated");

        //Navigating to the cart and Login
        Locators.getCart().click();
        Verify.verifyEquals(Locators.getCartTitle().getText(), AppConstants.cartTitle,
                "Verifying user is navigated to cart and items are listed");
        Locators.getcheckoutButton().click();
        System.out.println("Testing Login to the account");
        login();

        //Proceeding for payment and changing products
        waitForElement(Locators.getDeliveryButton());
        Locators.getDeliveryButton().click();
        Locators.getChangeProductLink().click();
        System.out.println("Delete product from cart");
        deleteProductFromCart();
        Verify.verifyEquals(Locators.getCartCount().getText(), String.valueOf(cartCount),
                "Verifying cart is empty on removing all the items");

        //Adding 3rd product to cart after login and delete activity
        searchProduct(prop.getProperty("category3"), prop.getProperty("product3"));
        Locators.selectProduct(prop.getProperty("product3")).click();
        switchWindow();
        addProductToCart();
        Verify.verifyEquals(Locators.getCartCount().getText(), String.valueOf(cartCount),
                "Verifying item is added to the cart and count is updated");
        Locators.getCart().click();
        Locators.getDeleteButton().click();

        //Moving mouse to MyAccount tile for logout operation
        moveToElement(Locators.getMyAccount());
        Locators.getSignOut().click();
        Verify.verifyTrue(Locators.getLoginEmail().isDisplayed(),
                "Verifying user is logged 0ut");
        Locators.getAmazonLogo().click();
        Verify.verifyTrue(Locators.getSignInOptionOnHader().getText().contains("Sign in"),
                "Verifying Sign in option is displayed on logging out from account");

    }

    /**---Following methods are generic methods created for test----**/

    /*This method switches browser windows between parent & Child*/
    public void switchWindow() throws InterruptedException {
        //Waiting for nvall the open windows to load
        Thread.sleep(5000);
        String parentWindow = driver.getWindowHandle();
        // Returns no. of windows opened by WebDriver in Set
        Set<String> set = driver.getWindowHandles();
        // Iterating window id's using Iterator
        Iterator<String> itr = set.iterator();
        while (itr.hasNext()) {
            String childWindow = itr.next();
            // Comparing whether parent windows is equal to child window. If not then switch to child.
            if (!parentWindow.equals(childWindow)) {
                System.out.println("Switching driver window to Child");
                driver.switchTo().window(childWindow);
                Thread.sleep(2000);
            }
        }
    }

    /*This method waits until element is visible on page*/
    public void waitForElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /*This method is for logging user in account and verifying login fields*/
    public void login() {
        //reading login id and password from properties file & Testing Login fields.
        Verify.verifyTrue(Locators.getLoginEmail().isDisplayed(),
                "Verifying email field is displayed");
        Locators.getContinueButton().click();
        Verify.verifyEquals(Locators.getMissingEmailAlert().getText(), AppConstants.enterEmailError,
                "Verifying error message on keeping email field blank");
        Locators.getLoginEmail().sendKeys(prop.getProperty("loginId") + "123");
        Locators.getContinueButton().click();
        Verify.verifyEquals(Locators.getIncorrectEmailPasswordAlert().getText(), AppConstants.accountNotFoundError,
                "Verifying error message on entering invalid email id in field ");
        Locators.getLoginEmail().clear();
        Locators.getLoginEmail().sendKeys(prop.getProperty("loginId"));
        Locators.getContinueButton().click();
        Verify.verifyTrue(Locators.getLoginPassword().isDisplayed(),
                "Verify user is able to login with valid id");
        //Testing Password field
        Locators.getLoginButton().click();
        Verify.verifyEquals(Locators.getMissingPasswordAlert().getText(), AppConstants.enterPasswordError,
                "Verifying error message on keeping password field blank");
        Locators.getLoginPassword().sendKeys(prop.getProperty("loginPassword") + "123");
        Locators.getLoginButton().click();
        int size = driver.findElements(Locators.loginButton).size();
        if (size<=0) {
            System.out.println("OTP is asked for Login");
            Locators.getContinueButton().click();
            Locators.getLoginPassword().sendKeys(prop.getProperty("loginPassword"));
            Locators.getLoginButton().click();
            if(driver.findElements(Locators.skipAndContinueLink).size()>0){
                Locators.getskipAndContinueLink().click();
            }

        }else {
            System.out.println("OTP is not asked for Login");
            Verify.verifyEquals(Locators.getIncorrectEmailPasswordAlert().getText(), AppConstants.invalidPasswordError,
                    "Verifying error message on entering invalid password in field");
            Locators.getLoginPassword().sendKeys(prop.getProperty("loginPassword"));
            Locators.getLoginButton().click();
            if(driver.findElements(Locators.skipAndContinueLink).size()>0){
                Locators.getskipAndContinueLink().click();
            }
        }
        System.out.println("User is logged in to Amazon account successfully");
    }

    /*This method is for searching product on Amazon*/
    public void searchProduct(String category, String productName) {
        //Searching and selecting product from property file
        Locators.getSearchField().clear();
        Locators.getSearchField().sendKeys(category);
        System.out.println("Searching in " + category + " category");
        Locators.getSearchButton().click();
        waitForElement(Locators.selectProduct(productName));
    }

    /*This method is for adding searched product in cart*/
    public void addProductToCart() throws Exception {
        int j = this.cartCount;
        Thread.sleep(3000);
        waitForElement(Locators.getAddToCartButton());
        System.out.println("Adding product to cart");
        Locators.getAddToCartButton().click();
        j++;
        this.cartCount = j;

    }

    /*This method deletes all the products added in cart*/
    public void deleteProductFromCart() throws Exception {
        int j = this.cartCount;
        while (Locators.getDeleteProduct().isDisplayed()) {
            System.out.println("Deleting " + j + " No. product from cart");
            Locators.getDeleteProduct().click();
            j--;
            Thread.sleep(5000);
        }
        this.cartCount = j;
    }

    /*Moving to element using Actions class*/
    public void moveToElement(WebElement element) {
        Actions actions = new Actions(driver);
        System.out.println("Moving mouse to the element: " + element);
        actions.moveToElement(element).build().perform();
    }
}