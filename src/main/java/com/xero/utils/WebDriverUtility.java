package com.xero.utils;

import com.google.common.base.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class WebDriverUtility {

    private WebDriver driver;

    protected static final Logger LOG = LoggerFactory.getLogger(WebDriverUtility.class);
    protected static final By LOADING_SPINNER_LOCATOR = By.cssSelector("div#loading");

    public static final int NORMAL_WAIT_TIME = 8;
    public static final int LONGER_WAIT_TIME = 30;
    public static final int VERY_LONG_WAIT_TIME = 180;

    @Inject
    public WebDriverUtility(WebDriver driver)
    {
        this.driver = driver;
    }


    public void waitUntilDOMLoadingIsFinished(int maxWaitSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, maxWaitSeconds, 100);
        wait.until(new ExpectedCondition<Boolean>()
        {
            @Override
            public Boolean apply(WebDriver driver)
            {
                JavascriptExecutor jse = (JavascriptExecutor) driver;
                return jse.executeScript("return document.readyState").toString().equals("complete");
            }
        });
        waitUntilElementNotOpaque(LOADING_SPINNER_LOCATOR, NORMAL_WAIT_TIME);
    }

    public void waitUntilElementNotOpaque(By locator, int maxWaitSecondsIn)
    {
        waitUntilElementNotOpaque(locator, 0, maxWaitSecondsIn);
    }

    public void waitUntilElementNotOpaque(By locator, int initialWaitTimeBeforeCheckingInMilliseconds, int maxWaitTimeInSeconds)
    {
        waitCustom(initialWaitTimeBeforeCheckingInMilliseconds);

        ExpectedCondition expectedCondition = new ExpectedCondition<Boolean>()
        {
            @Override
            public Boolean apply(WebDriver driver)
            {
                String opacity = driver.findElement(locator).getCssValue("opacity");
                return "".equals(opacity) || "0".equals(opacity);
            }
        };

        waitUntilIgnoreException((Function) expectedCondition, maxWaitTimeInSeconds);
    }

    public <T> T waitUntilIgnoreException(Function<? super WebDriver, T> expectedCondition, int maxTimeOutSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, maxTimeOutSeconds, 100);
        try
        {
            return wait.until(expectedCondition);
        }
        catch (WebDriverException e)
        {
            LOG.debug(e.getMessage());
            return null;
        }
    }

    public void waitCustom(int maxWaitMilliseconds)
    {
        try
        {
            Thread.sleep(maxWaitMilliseconds);
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
    }

    public void moveToElement(By locator){
        WebElement menuItem = driver.findElement(locator);
        Actions builder = new Actions(driver);
        builder.moveToElement(menuItem).perform();
    }


    public void moveToElementClick(WebElement webElement){
        Actions builder = new Actions(driver);
        builder.moveToElement(webElement).click().perform();
    }

    public void waitUntilDOMLoadingIsFinished(int maxWaitSeconds, By loadingSpinnerLocator)
    {
        waitUntilDOMLoadingIsFinished(maxWaitSeconds);
        waitUntilElementNotOpaque(loadingSpinnerLocator, NORMAL_WAIT_TIME);
    }

    public void switchToIframe(int iframeId)
    {
        driver.switchTo().defaultContent();
        driver.switchTo().frame(iframeId);
    }

    public void selectDropDownByVisibleText(WebElement webElement, String dropDownText){
        Select dropDown = new Select(webElement);
        dropDown.selectByVisibleText(dropDownText);
    }

    public void selectDropDownByIndex(WebElement webElement, int index){
        Select dropDown = new Select(webElement);
        dropDown.selectByIndex(index);
    }

    public void highlight(By targetLocator)
    {
        List<WebElement> elementsToHighlight = driver.findElements(targetLocator);
        highlight(elementsToHighlight);
    }


    public void highlight(WebElement element)
    {
        highlight(Arrays.asList(element));
    }


    public void highlight(List<WebElement> targetElements)
    {
        for (WebElement elementToHighlight : targetElements)
        {
            ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", elementToHighlight);
        }
    }

    public static String generateRandomNumberString()
    {
        Random random = new Random();
        int number  = 100000 + random.nextInt(900000);
        return Integer.toString(number);
    }
    public static String generateRandomNumberStringBasedOnRequiredDigits(int charLength)
    {
        return String.valueOf(charLength < 1 ? 0 : new Random()
                .nextInt((9 * (int) Math.pow(10, charLength - 1)) - 1)
                + (int) Math.pow(10, charLength - 1));
    }

}
