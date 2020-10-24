package com.xero.rules;

import com.xero.exception.AutomationException;
import org.apache.commons.io.FileUtils;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Supplier;

public class ScreenshotUponFailureRule
        extends TestWatcher
{
    private Supplier<WebDriver> webDriverProvider;

    public ScreenshotUponFailureRule(Supplier<WebDriver> webDriverProvider)
    {
        if (webDriverProvider == null)
        {
            throw new AutomationException("webDriverProvider cannot be null.");
        }
        this.webDriverProvider = webDriverProvider;
    }

    @Override
    protected void failed(Throwable e, Description description)
    {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd-HH.mm.ss").format(new Date());
        captureScreenshot(description.getMethodName() + "_" + timeStamp);
        super.failed(e, description);
    }

    private void captureScreenshot(String screenshotName)
    {
        try
        {
            File screenshot = ((TakesScreenshot) webDriverProvider.get()).getScreenshotAs(OutputType.FILE);
            String filename = "screenshots/" + screenshotName + ".png";
            FileUtils.copyFile(screenshot, new File(filename));
        }
        catch (Exception e)
        {
            throw new AutomationException(e);
        }
    }
}
