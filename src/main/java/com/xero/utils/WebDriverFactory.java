package com.xero.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public interface WebDriverFactory
{
    WebDriver create(DesiredCapabilities capabilities);
}
