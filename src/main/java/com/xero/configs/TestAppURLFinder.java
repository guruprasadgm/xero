package com.xero.configs;

import com.xero.utils.URLFinder;
import org.apache.commons.lang3.StringUtils;

public class TestAppURLFinder
        implements URLFinder
{
    @Override
    public String getBaseURL()
    {
        String applicationUnderTest = System.getProperty("targetAppURL");

        if (StringUtils.isNotBlank(applicationUnderTest))
        {
            return applicationUnderTest;
        }

        return "https://www.xero.com/nz/";
    }
}
