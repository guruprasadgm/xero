package com.xero.guice;

import com.google.inject.AbstractModule;
import com.xero.configs.TestAppURLFinder;
import com.xero.utils.URLFinder;

public class EndToEndInjectionModule
        extends AbstractModule
{
    @Override
    protected void configure()
    {
        bind(URLFinder.class).to(TestAppURLFinder.class);
    }
}

