package com.xero.utils;

import com.xero.exception.AutomationException;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import static org.openqa.selenium.remote.CapabilityType.ACCEPT_INSECURE_CERTS;
import static org.openqa.selenium.remote.CapabilityType.BROWSER_NAME;
import static org.openqa.selenium.remote.CapabilityType.VERSION;

public class DesiredCapabilitiesBuilderDefault
{
    private Map<String, Object> capabilities = new HashMap<>();

    public DesiredCapabilities build()
    {
        capabilities.putAll(addOtherCapabilities());
        return new DesiredCapabilities(capabilities);
    }

    protected Map<String, Object> addOtherCapabilities()
    {
        this.addIfSystemPropertyExists(BROWSER_NAME)
                .setAcceptInsecureCerts();
        return Collections.emptyMap();
    }

    public DesiredCapabilitiesBuilderDefault addIfSystemPropertyExists(String systemPropertyKey)
    {
        if (StringUtils.isNotBlank(System.getProperty(systemPropertyKey)))
        {
            capabilities.put(systemPropertyKey, System.getProperty(systemPropertyKey));
        }
        return this;
    }

    public DesiredCapabilitiesBuilderDefault addBrowserOptionsCapabilities(String... browserArguments)
    {
        if (capabilities.get(BROWSER_NAME).equals("chrome"))
        {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments(browserArguments);
            capabilities.put(ChromeOptions.CAPABILITY, chromeOptions);
        }
        else if (capabilities.get(BROWSER_NAME).equals("firefox"))
        {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments(browserArguments);
            capabilities.put(FirefoxOptions.FIREFOX_OPTIONS, firefoxOptions);
        }

        return this;
    }


    public DesiredCapabilitiesBuilderDefault setBrowserVersion()
    {
        String browserVersion = System.getProperty("browserVersion");
        if (StringUtils.isNotBlank(capabilities.get(BROWSER_NAME).toString()) && StringUtils.isNotBlank(browserVersion))
        {
            if (browserVersion.matches("[0-9]*\\.?[0-9]+"))
            {
                capabilities.put(VERSION, browserVersion);
            }
            else
            {
                throw new AutomationException(String.format("browserVersion %s is not a valid number.", browserVersion));
            }
        }
        return this;
    }


    public DesiredCapabilitiesBuilderDefault setAcceptInsecureCerts()
    {
        if (StringUtils.isNotBlank(capabilities.get(BROWSER_NAME).toString()))
        {
            if (Boolean.getBoolean("acceptInsecureCerts"))
            {
                capabilities.put(ACCEPT_INSECURE_CERTS, true);
            }
        }
        return this;
    }

}
