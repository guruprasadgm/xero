package com.xero.rules;

import com.xero.exception.AutomationException;
import java.util.function.Supplier;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;

public class TestFinalisingRule
        extends TestWatcher
{
    private Supplier<WebDriver> webDriverProvider;

    public TestFinalisingRule(Supplier<WebDriver> webDriverProvider)
    {
        if (webDriverProvider == null)
        {
            throw new AutomationException("webDriverProvider cannot be null.");
        }
        this.webDriverProvider = webDriverProvider;
    }

    @Override
    protected void finished(Description description)
    {
        webDriverProvider.get().quit();
        super.finished(description);
    }
}
