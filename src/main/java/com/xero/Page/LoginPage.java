package com.xero.Page;

import com.google.inject.Inject;
import com.xero.exception.AutomationException;
import com.xero.utils.WebDriverUtility;
import com.xero.utils.WebDriverWaitUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.xero.configs.TestTimeOuts.NORMAL_WAIT_SECONDS;

public class LoginPage {

    @Inject
    private WebDriver webDriver;

    @Inject
    private WebDriverWaitUtility webDriverWaitUtility;

    @Inject
    private WebDriverUtility webDriverUtility;


    private static final By BTN_USERNAME_LOCATOR = By.id("xl-form-email");
    private static final By BTN_PASSWORD_LOCATOR = By.id("xl-form-password");
    private static final By BTN_SUBMIT_LOCATOR = By.id("xl-form-submit");


    public void setUsername(String username){
        try {
        webDriverUtility.waitUntilDOMLoadingIsFinished(NORMAL_WAIT_SECONDS);
        webDriverWaitUtility.waitUntil(ExpectedConditions.presenceOfElementLocated(BTN_USERNAME_LOCATOR), NORMAL_WAIT_SECONDS);
        webDriver.findElement(BTN_USERNAME_LOCATOR).sendKeys(username);
        }
        catch (AutomationException e) {
            throw new AutomationException("could not find the element on page " + e.getMessage());
        }
    }

    public void setPassword(String password){
        webDriver.findElement(BTN_PASSWORD_LOCATOR).sendKeys(password);
    }

    public void clickSubmit(){
        webDriver.findElement(BTN_SUBMIT_LOCATOR).click();
    }

    public void login(String username,String password){
        setUsername(username);
        setPassword(password);
        clickSubmit();
    }



}
