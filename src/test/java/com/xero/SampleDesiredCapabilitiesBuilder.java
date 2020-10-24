package com.xero;

import com.xero.utils.DesiredCapabilitiesBuilderDefault;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.remote.CapabilityType;

import java.util.HashMap;
import java.util.Map;

public class SampleDesiredCapabilitiesBuilder
        extends DesiredCapabilitiesBuilderDefault
{
    private Map<String, Object> capabilities = new HashMap<>();

    @Override
    public Map<String, Object> addOtherCapabilities()
    {
        addProxy();
        return capabilities;
    }

    private void addProxy()
    {
        String browserProxy = System.getProperty("browserProxy");
        if (StringUtils.isNotBlank(browserProxy))
        {
            capabilities.put(CapabilityType.PROXY, new Proxy().setHttpProxy(browserProxy).setSslProxy(browserProxy));
        }
    }

}
