package com.demoblaze.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

public class HomePage extends BasePage {

    // Header elements
    @FindBy(id = "nava")
    private WebElement logo;

    @FindBy(id = "login2")
    private WebElement loginLink;

    @FindBy(id = "signin2")
    private WebElement signUpLink;

    @FindBy(id = "cartur")
    private WebElement cartLink;

    @FindBy(id = "logout2")
    private WebElement logoutLink;

    @FindBy(id = "nameofuser")
    private WebElement welcomeMessage;

    // Navigation menu
    @FindBy(xpath = "//a[text()='Home ']")
    private WebElement homeMenu;

    @FindBy(xpath = "//a[text()='Contact']")
    private WebElement contactMenu;

    @FindBy(xpath = "//a[text()='About us']")
    private WebElement aboutUsMenu;

    // Category filters
    @FindBy(xpath = "//a[text()='Phones']")
    private WebElement phonesCategory;

    @FindBy(xpath = "//a[text()='Laptops']")
    private WebElement laptopsCategory;

    @FindBy(xpath = "//a[text()='Monitors']")
    private WebElement monitorsCategory;

    // Product elements
    @FindBys(@FindBy(className = "card"))
    private List<WebElement> productCards;

    @FindBys(@FindBy(className = "hrefch"))
    private List<WebElement> productLinks;

    @FindBys(@FindBy(xpath = "//div[@class='card']//h4/a"))
    private List<WebElement> productTitles;

    @FindBys(@FindBy(xpath = "//div[@class='card']//h5"))
    private List<WebElement> productPrices;

    // Pagination
    @FindBy(id = "prev2")
    private WebElement previousButton;

    @FindBy(id = "next2")
    private WebElement nextButton;

    // Carousel
    @FindBy(className = "carousel-inner")
    private WebElement carousel;

    @FindBy(xpath = "//a[@data-slide='prev']")
    private WebElement carouselPrev;

    @FindBy(xpath = "//a[@data-slide='next']")
    private WebElement carouselNext;

    // Methods
    public boolean isLogoDisplayed() {
        return isElementDisplayed(logo);
    }

    public void clickLogin() {
        click(loginLink);
    }

    public void clickSignUp() {
        click(signUpLink);
    }

    public void clickCart() {
        click(cartLink);
    }

    public void clickLogout() {
        click(logoutLink);
    }

    public String getWelcomeMessage() {
        return getText(welcomeMessage);
    }

    public boolean isUserLoggedIn() {
        return isElementDisplayed(logoutLink) && isElementDisplayed(welcomeMessage);
    }

    public void clickHome() {
        click(homeMenu);
    }

    public void clickContact() {
        click(contactMenu);
    }

    public void clickAboutUs() {
        click(aboutUsMenu);
    }

    public void filterByPhones() {
        click(phonesCategory);
        waitForPageLoad();
    }

    public void filterByLaptops() {
        click(laptopsCategory);
        waitForPageLoad();
    }

    public void filterByMonitors() {
        click(monitorsCategory);
        waitForPageLoad();
    }

    public int getProductCount() {
        return productCards.size();
    }

    public void clickProductByIndex(int index) {
        if (index >= 0 && index < productLinks.size()) {
            click(productLinks.get(index));
        }
    }

    public void clickProductByName(String productName) {
        for (WebElement productTitle : productTitles) {
            if (getText(productTitle).equals(productName)) {
                click(productTitle);
                break;
            }
        }
    }

    public List<String> getProductTitles() {
        return productTitles.stream()
                .map(this::getText)
                .toList();
    }

    public List<String> getProductPrices() {
        return productPrices.stream()
                .map(this::getText)
                .toList();
    }

    public boolean isProductDisplayed(String productName) {
        return productTitles.stream()
                .anyMatch(element -> getText(element).equals(productName));
    }

    public void clickNext() {
        if (isElementDisplayed(nextButton) && isElementEnabled(nextButton)) {
            click(nextButton);
            waitForPageLoad();
        }
    }

    public void clickPrevious() {
        if (isElementDisplayed(previousButton) && isElementEnabled(previousButton)) {
            click(previousButton);
            waitForPageLoad();
        }
    }

    public boolean isNextButtonEnabled() {
        return isElementEnabled(nextButton);
    }

    public boolean isPreviousButtonEnabled() {
        return isElementEnabled(previousButton);
    }

    public void navigateCarouselNext() {
        click(carouselNext);
    }

    public void navigateCarouselPrev() {
        click(carouselPrev);
    }

    public boolean isCarouselDisplayed() {
        return isElementDisplayed(carousel);
    }
}