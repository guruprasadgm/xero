package com.xero.utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Provider;
import java.util.concurrent.TimeUnit;

public class WebDriverProvider
        implements Provider<WebDriver>
{
    private static final Logger LOGGER = LoggerFactory.getLogger(WebDriverProvider.class);

    private WebDriverFactory driverFactory;

    private DesiredCapabilities desiredCapabilities;

    @Inject
    public WebDriverProvider(WebDriverFactory driverFactory, DesiredCapabilities desiredCapabilities)
    {
        this.driverFactory = driverFactory;
        this.desiredCapabilities = desiredCapabilities;
    }

    @Override
    public WebDriver get()
    {
        WebDriver webDriver = driverFactory.create(desiredCapabilities);
        if (webDriver instanceof AppiumDriver)
        {
            return webDriver;
        }

        long implicitTimeoutInSeconds = Long.parseLong(System.getProperty("implicit.wait.timeout", 10L + ""));
        LOGGER.info("WebDriver implicit time wait is set to {}.", implicitTimeoutInSeconds);
        webDriver.manage().timeouts().implicitlyWait(implicitTimeoutInSeconds, TimeUnit.SECONDS);

        long pageLoadTimeoutInSeconds = Long.parseLong(System.getProperty("pageload.timeout", 90L + ""));
        LOGGER.info("WebDriver page load timeout is set to {}.", pageLoadTimeoutInSeconds);
        webDriver.manage().timeouts().pageLoadTimeout(pageLoadTimeoutInSeconds, TimeUnit.SECONDS);

        return webDriver;
    }
}
