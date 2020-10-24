package com.xero.initialiser;

import com.google.inject.Inject;
import com.xero.utils.URLFinder;
import org.openqa.selenium.WebDriver;

public class TestInitialiser
{
    private WebDriver webDriver;

    private URLFinder urlFinder;

    @Inject
    public TestInitialiser(WebDriver webDriver, URLFinder urlFinder)
    {
        this.webDriver = webDriver;
        this.urlFinder = urlFinder;
    }

    public void init()
    {
        webDriver.get(urlFinder.getBaseURL());
    }
}