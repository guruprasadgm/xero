package com.xero.Page;

import com.google.inject.Inject;
import com.xero.exception.AutomationException;
import com.xero.utils.WebDriverUtility;
import com.xero.utils.WebDriverWaitUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.xero.configs.TestTimeOuts.NORMAL_WAIT_SECONDS;

public class HomePage {

    @Inject
    private WebDriver webDriver;

    @Inject
    private WebDriverWaitUtility webDriverWaitUtility;

    @Inject
    private WebDriverUtility webDriverUtility;

    private static final By BTN_LOGIN = By.cssSelector(".btn.btn-tertiary-alt");

    public void clickLoginButton (){
       try{
           webDriverWaitUtility.waitUntil(ExpectedConditions.elementToBeClickable(BTN_LOGIN), NORMAL_WAIT_SECONDS);
           webDriver.findElement(BTN_LOGIN).click();
    }
        catch (
    AutomationException e) {
        throw new AutomationException("could not find the element on page " + e.getMessage());
    }
    }

}
