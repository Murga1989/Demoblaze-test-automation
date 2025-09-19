package com.demoblaze.tests;

import com.demoblaze.base.BaseTest;
import com.demoblaze.pages.HomePage;
import com.demoblaze.pages.LoginPage;
import com.demoblaze.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(description = "Verify successful login with valid credentials")
    public void testValidLogin() {
        HomePage homePage = new HomePage();
        LoginPage loginPage = new LoginPage();

        // Click login link
        homePage.clickLogin();

        // Verify login modal is displayed
        Assert.assertTrue(loginPage.isLoginModalDisplayed(), "Login modal should be displayed");

        // Perform login
        loginPage.login(config.getProperty("test.username"), config.getProperty("test.password"));

        // Wait for modal to close
        loginPage.waitForModalToClose();

        // Verify user is logged in
        Assert.assertTrue(homePage.isUserLoggedIn(), "User should be logged in");

        // Verify welcome message contains username
        String welcomeMessage = homePage.getWelcomeMessage();
        Assert.assertTrue(welcomeMessage.contains(config.getProperty("test.username")),
                "Welcome message should contain username");
    }

    @Test(description = "Verify login fails with invalid credentials")
    public void testInvalidLogin() {
        HomePage homePage = new HomePage();
        LoginPage loginPage = new LoginPage();

        // Click login link
        homePage.clickLogin();

        // Verify login modal is displayed
        Assert.assertTrue(loginPage.isLoginModalDisplayed(), "Login modal should be displayed");

        // Attempt login with invalid credentials
        loginPage.login("invaliduser", "invalidpass");

        // Wait for alert and verify error message
        String alertText = loginPage.getAlertText();
        Assert.assertEquals(alertText, "User does not exist.", "Should show user not exists error");

        // Accept alert
        loginPage.acceptAlert();

        // Verify user is not logged in
        Assert.assertFalse(homePage.isUserLoggedIn(), "User should not be logged in");
    }

    @Test(description = "Verify login modal elements are displayed")
    public void testLoginModalElements() {
        HomePage homePage = new HomePage();
        LoginPage loginPage = new LoginPage();

        // Click login link
        homePage.clickLogin();

        // Verify modal elements
        Assert.assertTrue(loginPage.isLoginModalDisplayed(), "Login modal should be displayed");
        Assert.assertTrue(loginPage.isUsernameFieldDisplayed(), "Username field should be displayed");
        Assert.assertTrue(loginPage.isPasswordFieldDisplayed(), "Password field should be displayed");
        Assert.assertTrue(loginPage.isLoginButtonDisplayed(), "Login button should be displayed");
        Assert.assertTrue(loginPage.isLoginButtonEnabled(), "Login button should be enabled");

        // Verify modal title
        Assert.assertEquals(loginPage.getLoginModalTitle(), "Log in", "Modal title should be 'Log in'");
    }

    @Test(description = "Verify empty credentials login")
    public void testEmptyCredentialsLogin() {
        HomePage homePage = new HomePage();
        LoginPage loginPage = new LoginPage();

        // Click login link
        homePage.clickLogin();

        // Click login without entering credentials
        loginPage.clickLoginButton();

        // Wait for alert and verify error message
        String alertText = loginPage.getAlertText();
        Assert.assertEquals(alertText, "Please fill out Username and Password.",
                "Should show empty credentials error");

        // Accept alert
        loginPage.acceptAlert();
    }
}
