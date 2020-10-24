package com.xero.utils;

import com.xero.exception.AutomationException;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

public class LocalWebDriverProvider
{
    private DesiredCapabilities capabilities;

    public LocalWebDriverProvider(DesiredCapabilities capabilities)
    {
        this.capabilities = capabilities;
    }

    public WebDriver createLocalWebDriver()
    {
        String browserName = System.getProperty("browserName");

        if (StringUtils.isBlank(browserName))
        {
            throw new AutomationException("browserName capability is not set. Please set 'browserName' system property.");
        }

        WebDriver driver;

        switch (browserName)
        {
            case (BrowserType.CHROME):
                driver = new ChromeDriver(capabilities);
                break;
            case (BrowserType.FIREFOX):
                driver = new FirefoxDriver(capabilities);
                break;
            case (BrowserType.SAFARI):
                driver = new SafariDriver(capabilities);
                break;
            case (BrowserType.EDGE):
                driver = new EdgeDriver(capabilities);
                break;
            case (BrowserType.IE):
                driver = new InternetExplorerDriver(capabilities);
                break;
            default:
                throw new AutomationException("browserName '" + browserName + "' is not supported.");
        }

        return driver;
    }
}
