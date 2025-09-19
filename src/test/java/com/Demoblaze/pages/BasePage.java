package com.demoblaze.pages;

import com.demoblaze.base.BaseTest;
import com.demoblaze.utils.ScreenshotUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public abstract class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage() {
        this.driver = BaseTest.getDriver();
        this.wait = BaseTest.getWait();
        PageFactory.initElements(driver, this);
    }

    // Wait methods
    protected WebElement waitForElementToBeVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected WebElement waitForElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected boolean waitForElementToBeInvisible(WebElement element) {
        return wait.until(ExpectedConditions.invisibilityOf(element));
    }

    protected void waitForPageLoad() {
        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));
    }

    protected void waitForAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
    }

    // Click methods
    protected void click(WebElement element) {
        waitForElementToBeClickable(element).click();
    }

    protected void clickWithJavaScript(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    // Text input methods
    protected void sendKeys(WebElement element, String text) {
        waitForElementToBeVisible(element);
        element.clear();
        element.sendKeys(text);
    }

    protected void sendKeysWithJavaScript(WebElement element, String text) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value='" + text + "';", element);
    }

    // Get text methods
    protected String getText(WebElement element) {
        return waitForElementToBeVisible(element).getText();
    }

    protected String getAttribute(WebElement element, String attribute) {
        return waitForElementToBeVisible(element).getAttribute(attribute);
    }

    // Dropdown methods
    protected void selectByVisibleText(WebElement dropdown, String text) {
        Select select = new Select(waitForElementToBeVisible(dropdown));
        select.selectByVisibleText(text);
    }

    protected void selectByValue(WebElement dropdown, String value) {
        Select select = new Select(waitForElementToBeVisible(dropdown));
        select.selectByValue(value);
    }

    // Validation methods
    protected boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
    }

    protected boolean isElementEnabled(WebElement element) {
        try {
            return waitForElementToBeVisible(element).isEnabled();
        } catch (TimeoutException e) {
            return false;
        }
    }

    protected boolean isElementSelected(WebElement element) {
        try {
            return waitForElementToBeVisible(element).isSelected();
        } catch (TimeoutException e) {
            return false;
        }
    }

    // Alert methods
    protected void acceptAlert() {
        waitForAlert();
        driver.switchTo().alert().accept();
    }

    protected void dismissAlert() {
        waitForAlert();
        driver.switchTo().alert().dismiss();
    }

    protected String getAlertText() {
        waitForAlert();
        return driver.switchTo().alert().getText();
    }

    // Scroll methods
    protected void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    protected void scrollToTop() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0);");
    }

    protected void scrollToBottom() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    // Navigation methods
    protected void refreshPage() {
        driver.navigate().refresh();
        waitForPageLoad();
    }

    protected void navigateBack() {
        driver.navigate().back();
        waitForPageLoad();
    }

    protected void navigateForward() {
        driver.navigate().forward();
        waitForPageLoad();
    }

    // Window methods
    protected void switchToWindow(String windowHandle) {
        driver.switchTo().window(windowHandle);
    }

    protected String getCurrentWindowHandle() {
        return driver.getWindowHandle();
    }

    // Screenshot method
    protected String takeScreenshot(String testName) {
        return ScreenshotUtils.takeScreenshot(testName);
    }

    // Wait with custom timeout
    protected WebElement waitForElement(WebElement element, int timeoutInSeconds) {
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return customWait.until(ExpectedConditions.visibilityOf(element));
    }
}
