package com.xero.test;

import com.google.inject.Inject;
import com.google.inject.util.Modules;
import com.xero.guice.EndToEndInjectionModule;
import com.xero.guice.SampleWebDriverInjectionModule;
import com.xero.rules.DependencyInjectionRule;
import com.xero.rules.ScreenshotUponFailureRule;
import com.xero.rules.TestFinalisingRule;
import com.xero.utils.WebDriverInjectionModule;
import org.junit.Rule;
import org.junit.rules.RuleChain;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public abstract class TestBase
{
    @Inject
    private WebDriver webDriver;

    @Inject
    private DesiredCapabilities cap;

    @Rule
    public RuleChain chain = RuleChain
            .outerRule(new DependencyInjectionRule(this, Modules.override(new WebDriverInjectionModule()).with(new SampleWebDriverInjectionModule()), new EndToEndInjectionModule()))
            .around(new TestFinalisingRule(this::getWebDriver))
            .around(new ScreenshotUponFailureRule(this::getWebDriver));


    private WebDriver getWebDriver()
    {
        return webDriver;
    }
}