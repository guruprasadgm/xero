package com.xero.Page;

import com.google.inject.Inject;
import com.xero.exception.AutomationException;
import com.xero.utils.WebDriverUtility;
import com.xero.utils.WebDriverWaitUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class DashboardPage {

    private static final Logger LOGGER = LoggerFactory.getLogger(DashboardPage.class);

    @Inject
    private WebDriver webDriver;

    @Inject
    private WebDriverWaitUtility webDriverWaitUtility;

    @Inject
    private WebDriverUtility webDriverUtility;

    private static final By BANK_WIDGET_CONTAINER_LOCATOR = By.cssSelector(".xdash-shared__widget___TnErD[data-automationid='bankWidget']");
    private static final By BANK_ACCOUNT_NAME_LOCATOR= By.cssSelector(".xui-panel--heading.xui-text-panelheading");
    private static final By BANK_ACCOUNT_NUMBER_LOCATOR= By.cssSelector(".xui-text-secondary");
    private static final By BANK_LOGO_LOCATOR = By.cssSelector(".xui-margin-right.xdash-WidgetHeader__widget-header--logo___3wUao");

    public void validateAccountCreation (String accountName,String AccountNumber,String bankName){
    try{
        boolean flagNoWidgetFound=false;
        List<WebElement> allWidgets = webDriver.findElements(BANK_WIDGET_CONTAINER_LOCATOR);
        for (WebElement widget: allWidgets) {
            if (widget.findElement(BANK_ACCOUNT_NAME_LOCATOR).getText().equals(accountName)) {
                assertThat(widget.findElement(BANK_ACCOUNT_NUMBER_LOCATOR).getText()).isEqualTo(AccountNumber);
                assertThat(widget.findElement(BANK_LOGO_LOCATOR).getAttribute("src")).contains(bankName);
                LOGGER.info("validation successful for bankName: " + bankName + ", accountName: " + AccountNumber + ", accountNumber: "  + AccountNumber);
                if (flagNoWidgetFound) {
                    flagNoWidgetFound = true;
                    assertThat(false).withFailMessage("test failed as accountName: "+ accountName+ " is not unique on the Dashboard page").isTrue();
                    break;
                }
                else
                {
                    flagNoWidgetFound=true;
                }
            }
        }
        if (!flagNoWidgetFound){
            assertThat(false).withFailMessage("test failed as accountName: "+ accountName+ " was not found on the Dashboard page").isTrue();
         }
    }
        catch (
    AutomationException e) {
        throw new AutomationException("could not find the element on page " + e.getMessage());
    }
    }


}
