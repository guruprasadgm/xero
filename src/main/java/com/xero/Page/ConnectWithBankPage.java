package com.xero.Page;

import com.google.inject.Inject;
import com.xero.exception.AutomationException;
import com.xero.utils.WebDriverUtility;
import com.xero.utils.WebDriverWaitUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static com.xero.configs.TestTimeOuts.NORMAL_WAIT_SECONDS;


public class ConnectWithBankPage {

    @Inject
    private WebDriver webDriver;

    @Inject
    private WebDriverWaitUtility webDriverWaitUtility;

    @Inject
    private WebDriverUtility webDriverUtility;

    private static final By INPUT_ACCOUNT_NAME_LOCATOR= By.cssSelector(".xui-input[id*='accountname']");
    private static final By INPUT_ACCOUNT_TYPE_LOCATOR= By.cssSelector(".xui-input.x-form-text-default[id*='accounttype']");
    private static final String ACCOUNT_TYPE_VALUE_LOCATOR= "//li[text()='%s']";
    private static final By INPUT_ACCOUNT_NUMBER_LOCATOR= By.cssSelector(".xui-input[id*='accountnumber']");
    private static final By BTN_CONTINUE_LOCATOR= By.cssSelector(".x-btn.co-btn-default-small[data-automationid='continueButton']");
    private static final By BTN_GOT_FORM_LOCATOR= By.cssSelector(".xui-button.xui-actions--secondary[data-automationid='connectbank-buttonIHaveAForm']");
    private static final By BTN_UPLOAD_LATER_LOCATOR= By.cssSelector(".xui-button.xui-actions--secondary[data-automationid='uploadForm-uploadLaterButton']");
    private static final By BTN_GO_TO_DASHBOARD_LOCATOR= By.cssSelector(".xui-button.xui-button-fullwidth-layout[data-automationid='uploadFormLater-goToDashboardButton']");

    public void setAccountName(String accountName)
    {
        try{

            webDriverWaitUtility.waitUntil(ExpectedConditions.elementToBeClickable(INPUT_ACCOUNT_NAME_LOCATOR), NORMAL_WAIT_SECONDS);
            webDriver.findElement(INPUT_ACCOUNT_NAME_LOCATOR).sendKeys(accountName);
        }
        catch (
                AutomationException e) {
            throw new AutomationException("could not find the element on page " + e.getMessage());
        }
    }

    public void setAccountType(String accountType)
    {
        try{
            webDriver.findElement(INPUT_ACCOUNT_TYPE_LOCATOR).click();
            webDriver.findElement(By.xpath(String.format(ACCOUNT_TYPE_VALUE_LOCATOR,accountType))).click();
        }
        catch (
                AutomationException e) {
            throw new AutomationException("could not find the element on page " + e.getMessage());
        }
    }


    public void setAccountNumber(String accountNumber)
    {
        try{
            List<WebElement> allAccountNumberLocators = webDriver.findElements(INPUT_ACCOUNT_NUMBER_LOCATOR);
            for ( WebElement accountNumberLocator: allAccountNumberLocators) {
                if (accountNumberLocator.isDisplayed())
                {
                    accountNumberLocator.sendKeys(accountNumber);
                    break;
                }
            }
        }
        catch (
                AutomationException e) {
            throw new AutomationException("could not find the element on page " + e.getMessage());
        }
    }


    public void clickContinue()
    {
        try{
            webDriverWaitUtility.waitUntil(ExpectedConditions.elementToBeClickable(BTN_CONTINUE_LOCATOR), NORMAL_WAIT_SECONDS);
            webDriver.findElement(BTN_CONTINUE_LOCATOR).click();
        }
        catch (
                AutomationException e) {
            throw new AutomationException("could not find the element on page " + e.getMessage());
        }
    }

    public void clickIHaveGotForm()
    {
        try{
            webDriverWaitUtility.waitUntil(ExpectedConditions.elementToBeClickable(BTN_GOT_FORM_LOCATOR), NORMAL_WAIT_SECONDS);
            webDriver.findElement(BTN_GOT_FORM_LOCATOR).click();
        }
        catch (
                AutomationException e) {
            throw new AutomationException("could not find the element on page " + e.getMessage());
        }
    }


    public void clickIWillUploadLater()
    {
        try{
            webDriverWaitUtility.waitUntil(ExpectedConditions.elementToBeClickable(BTN_UPLOAD_LATER_LOCATOR), NORMAL_WAIT_SECONDS);
            webDriver.findElement(BTN_UPLOAD_LATER_LOCATOR).click();
        }
        catch (
                AutomationException e) {
            throw new AutomationException("could not find the element on page " + e.getMessage());
        }
    }

    public void clickOnDashboard()
    {
        try{
            webDriverWaitUtility.waitUntil(ExpectedConditions.elementToBeClickable(BTN_GO_TO_DASHBOARD_LOCATOR), NORMAL_WAIT_SECONDS);
            webDriver.findElement(BTN_GO_TO_DASHBOARD_LOCATOR).click();
        }
        catch (
                AutomationException e) {
            throw new AutomationException("could not find the element on page " + e.getMessage());
        }
    }

    public void navigateIHaveGotFormUploadLater(){
      clickIHaveGotForm();
      clickIWillUploadLater();
      clickOnDashboard();
    }

}
