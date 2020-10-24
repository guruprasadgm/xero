package com.xero.Page;

import com.google.inject.Inject;
import com.xero.exception.AutomationException;
import com.xero.utils.WebDriverUtility;
import com.xero.utils.WebDriverWaitUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static com.xero.configs.TestTimeOuts.NORMAL_WAIT_SECONDS;

public class BankAccountsPage {

    @Inject
    private WebDriver webDriver;

    @Inject
    private WebDriverWaitUtility webDriverWaitUtility;

    @Inject
    private WebDriverUtility webDriverUtility;


   private static final By ADD_BANK_ACCOUNT_LOCATOR= By.cssSelector(".text[data-automationid='Add Bank Account-button'");


    public void clickAddBankAccount()
    {
        try {
            webDriverWaitUtility.waitUntil(ExpectedConditions.elementToBeClickable(ADD_BANK_ACCOUNT_LOCATOR), NORMAL_WAIT_SECONDS);
            webDriver.findElement(ADD_BANK_ACCOUNT_LOCATOR).click();
        }
        catch (AutomationException e) {
            throw new AutomationException("could not find the element on page " + e.getMessage());
        }
    }

}
