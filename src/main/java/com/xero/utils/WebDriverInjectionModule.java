package com.xero.utils;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.lang.annotation.Annotation;

import static org.openqa.selenium.remote.CapabilityType.BROWSER_NAME;

public class WebDriverInjectionModule
        extends AbstractModule
{

    private Class<? extends Annotation> webdriverScope;

    public WebDriverInjectionModule(Class<? extends Annotation> webdriverScope)
    {
        this.webdriverScope = webdriverScope;
    }

    public WebDriverInjectionModule()
    {

        this(javax.inject.Singleton.class);
    }

    @Override
    protected void configure()
    {
        bind(WebDriverFactory.class).to(WebDriverFactoryDefault.class);
        bind(WebDriver.class).toProvider(WebDriverProvider.class).in(webdriverScope);
    }

    @Provides
    DesiredCapabilities providesDesiredCapabilities(DesiredCapabilitiesBuilderDefault defaultBuilder)
    {
        return defaultBuilder.build();
    }

    @Provides
    DesiredCapabilitiesBuilderDefault providesDefaultDesiredCapilitiesBuilder()
    {
        return new DesiredCapabilitiesBuilderDefault()
                .addIfSystemPropertyExists(BROWSER_NAME);
    }
}

