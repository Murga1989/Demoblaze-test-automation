package com.demoblaze.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignUpPage extends BasePage {

    // Sign up modal elements
    @FindBy(id = "signInModal")
    private WebElement signUpModal;

    @FindBy(xpath = "//div[@id='signInModal']//h4[@class='modal-title']")
    private WebElement signUpModalTitle;

    @FindBy(id = "sign-username")
    private WebElement usernameInput;

    @FindBy(id = "sign-password")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[text()='Sign up']")
    private WebElement signUpButton;

    @FindBy(xpath = "//div[@id='signInModal']//button[text()='Close']")
    private WebElement closeButton;

    @FindBy(xpath = "//div[@id='signInModal']//button[@class='close']")
    private WebElement closeXButton;

    // Methods
    public boolean isSignUpModalDisplayed() {
        return isElementDisplayed(signUpModal);
    }

    public String getSignUpModalTitle() {
        return getText(signUpModalTitle);
    }

    public void enterUsername(String username) {
        sendKeys(usernameInput, username);
    }

    public void enterPassword(String password) {
        sendKeys(passwordInput, password);
    }

    public void clickSignUpButton() {
        click(signUpButton);
    }

    public void clickClose() {
        click(closeButton);
    }

    public void clickCloseX() {
        click(closeXButton);
    }

    public void signUp(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickSignUpButton();
    }

    public boolean isUsernameFieldDisplayed() {
        return isElementDisplayed(usernameInput);
    }

    public boolean isPasswordFieldDisplayed() {
        return isElementDisplayed(passwordInput);
    }

    public boolean isSignUpButtonDisplayed() {
        return isElementDisplayed(signUpButton);
    }

    public boolean isSignUpButtonEnabled() {
        return isElementEnabled(signUpButton);
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
        waitForElementToBeInvisible(signUpModal);
    }
}