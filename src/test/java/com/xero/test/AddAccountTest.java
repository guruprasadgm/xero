package com.xero.test;

import com.xero.Page.AddAccountsPage;
import com.xero.Page.BankAccountsPage;
import com.xero.Page.ConnectWithBankPage;
import com.xero.Page.HomePage;
import com.xero.Page.LoginPage;
import com.xero.Page.DashboardPage;
import com.xero.Page.MenuNavigation;
import com.xero.configs.Constants;

import com.xero.initialiser.TestInitialiser;
import org.junit.Test;
import javax.inject.Inject;
import static com.xero.utils.WebDriverUtility.generateRandomNumberStringBasedOnRequiredDigits;

public class AddAccountTest extends TestBase{

    @Inject
    private TestInitialiser testInitialiser;

    @Inject
    private HomePage homePage;

    @Inject
    private LoginPage loginPage;

    @Inject
    private MenuNavigation menuNavigation;

    @Inject
    private BankAccountsPage bankAccountsPage;

    @Inject
    private AddAccountsPage addAccountsPage;

    @Inject
    private ConnectWithBankPage connectWithBankPage;

    @Inject
    private DashboardPage dashboardPage;


    @Test
    public void AddANZBankAccountTest() {
        testInitialiser.init();
        String accountNumber= generateRandomNumberStringBasedOnRequiredDigits(10);
        String accountName= "TestAccount" + generateRandomNumberStringBasedOnRequiredDigits(4);

        homePage.clickLoginButton();

        loginPage.login("xeroassesment@gmail.com", "xerotest123");

        menuNavigation.navigateToMenuItem(Constants.MENU_ACCOUNTING, Constants.SUBMENU_BANK_ACCOUNTS);
        bankAccountsPage.clickAddBankAccount();
        addAccountsPage.clickBankFromList(Constants.BANKNAME_ANZ_NZ);
        connectWithBankPage.setAccountName(accountName);
        connectWithBankPage.setAccountType(Constants.ACCOUNTTYPE_EVERYDAY);
        connectWithBankPage.setAccountNumber(accountNumber);
        connectWithBankPage.clickContinue();
        connectWithBankPage.navigateIHaveGotFormUploadLater();

        dashboardPage.validateAccountCreation(accountName, accountNumber, Constants.BANKNAME_ANZ);
    }


}
