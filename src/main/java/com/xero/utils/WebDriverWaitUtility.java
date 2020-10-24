package com.xero.utils;

import com.google.common.base.Function;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.inject.Inject;

public class WebDriverWaitUtility {
    private WebDriver driver;

    @Inject
    public WebDriverWaitUtility(WebDriver driver)
    {
        this.driver = driver;
    }

    public <T> T waitUntil(Function<? super WebDriver, T> expectedCondition, int maxTimeOutSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, maxTimeOutSeconds, 100);
        return wait.until(expectedCondition);
    }
}