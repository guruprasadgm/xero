package com.xero.Page;

import com.google.inject.Inject;
import com.xero.exception.AutomationException;
import com.xero.utils.WebDriverUtility;
import com.xero.utils.WebDriverWaitUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.xero.configs.TestTimeOuts.NORMAL_WAIT_SECONDS;

public class MenuNavigation {

    @Inject
    private WebDriver webDriver;

    @Inject
    private WebDriverWaitUtility webDriverWaitUtility;

    @Inject
    private WebDriverUtility webDriverUtility;


    private static final String MENU_LOCATOR = ".xrh-focusable.xrh-tab--body[data-event-action='Clicked NAVIGATION: %s']";
    private static final String SUBMENU_LOCATOR = ".xrh-verticalmenuitem--body[data-event-action='Clicked NAVIGATION_SUBMENU: %s']";


    public void clickMenu (String menu){
        try {
            webDriverWaitUtility.waitUntil(ExpectedConditions.elementToBeClickable(By.cssSelector(String.format(MENU_LOCATOR, menu))), NORMAL_WAIT_SECONDS);
            webDriver.findElement(By.cssSelector(String.format(MENU_LOCATOR, menu))).click();
        }
        catch (AutomationException e) {
            throw new AutomationException("could not find the element on page " + e.getMessage());
        }
    }

    public void clickSubMenu (String subMenu){
        webDriverWaitUtility.waitUntil(ExpectedConditions.elementToBeClickable(By.cssSelector(String.format(SUBMENU_LOCATOR, subMenu))), NORMAL_WAIT_SECONDS);
        webDriver.findElement(By.cssSelector(String.format(SUBMENU_LOCATOR, subMenu))).click();
    }

    public void navigateToMenuItem(String menuItem,String SubMenuItem){
        clickMenu(menuItem);
        clickSubMenu(SubMenuItem);

    }
}
