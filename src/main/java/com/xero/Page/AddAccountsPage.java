package com.xero.Page;

import com.google.inject.Inject;
import com.xero.exception.AutomationException;
import com.xero.utils.WebDriverUtility;
import com.xero.utils.WebDriverWaitUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static com.xero.configs.TestTimeOuts.NORMAL_WAIT_SECONDS;

public class AddAccountsPage {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddAccountsPage.class);

    @Inject
    private WebDriver webDriver;

    @Inject
    private WebDriverWaitUtility webDriverWaitUtility;

    @Inject
    private WebDriverUtility webDriverUtility;

    private static final String BANK_LIST_LOCATOR= "//li[text()='%s']";


    public void clickBankFromList(String bankText)
    {
        try {
            webDriverWaitUtility.waitUntil(ExpectedConditions.elementToBeClickable(By.xpath(String.format(BANK_LIST_LOCATOR,bankText))), NORMAL_WAIT_SECONDS);
            webDriver.findElement(By.xpath(String.format(BANK_LIST_LOCATOR,bankText))).click();
            LOGGER.info("Bank from list selected" + bankText);
        }
        catch (AutomationException e) {
            throw new AutomationException("could not find the element on page " + e.getMessage());
        }
    }
}
