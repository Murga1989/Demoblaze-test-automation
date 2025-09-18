package com.demoblaze.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    // Login modal elements
    @FindBy(id = "logInModal")
    private WebElement loginModal;

    @FindBy(xpath = "//div[@id='logInModal']//h4[@class='modal-title']")
    private WebElement loginModalTitle;

    @FindBy(id = "loginusername")
    private WebElement usernameInput;

    @FindBy(id = "loginpassword")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[text()='Log in']")
    private WebElement loginButton;

    @FindBy(xpath = "//div[@id='logInModal']//button[text()='Close']")
    private WebElement closeButton;

    @FindBy(xpath = "//div[@id='logInModal']//button[@class='close']")
    private WebElement closeXButton;

    // Methods
    public boolean isLoginModalDisplayed() {
        return isElementDisplayed(loginModal);
    }

    public String getLoginModalTitle() {
        return getText(loginModalTitle);
    }

    public void enterUsername(String username) {
        sendKeys(usernameInput, username);
    }

    public void enterPassword(String password) {
        sendKeys(passwordInput, password);
    }

    public void clickLoginButton() {
        click(loginButton);
    }

    public void clickClose() {
        click(closeButton);
    }

    public void clickCloseX() {
        click(closeXButton);
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    public boolean isUsernameFieldDisplayed() {
        return isElementDisplayed(usernameInput);
    }

    public boolean isPasswordFieldDisplayed() {
        return isElementDisplayed(passwordInput);
    }

    public boolean isLoginButtonDisplayed() {
        return isElementDisplayed(loginButton);
    }

    public boolean isLoginButtonEnabled() {
        return isElementEnabled(loginButton);
    }

    public void clearUsername() {
        usernameInput.clear();
    }

    public void clearPassword() {
        passwordInput.clear();
    }

    public String getUsernameValue() {
        return getAttribute(usernameInput, "value");
    }

    public String getPasswordValue() {
        return getAttribute(passwordInput, "value");
    }

    public void waitForModalToClose() {
        waitForElementToBeInvisible(loginModal);
    }
}