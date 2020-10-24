package com.xero.rules;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

public class DependencyInjectionRule
        implements TestRule
{
    private Module[] modules;

    private Object target;

    public DependencyInjectionRule(Object target, Module... modules)
    {
        this.target = target;
        this.modules = modules;
    }

    @Override
    public Statement apply(Statement base, Description description)
    {
        return new Statement()
        {
            @Override public void evaluate()
                    throws Throwable
            {
                Injector injector = Guice.createInjector(modules);
                injector.injectMembers(target);
                base.evaluate();
            }
        };
    }
}