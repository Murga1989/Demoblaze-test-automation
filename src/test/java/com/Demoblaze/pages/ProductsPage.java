package com.Demoblaze.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

class ProductPage extends com.demoblaze.pages.BasePage {

    // Product details elements
    @FindBy(xpath = "//h2[@class='name']")
    private WebElement productName;

    @FindBy(xpath = "//h3[@class='price-container']")
    private WebElement productPrice;

    @FindBy(id = "more-information")
    private WebElement productDescription;

    @FindBy(xpath = "//div[@class='item active']//img")
    private WebElement productImage;

    @FindBy(xpath = "//a[text()='Add to cart']")
    private WebElement addToCartButton;

    // Breadcrumb navigation
    @FindBy(xpath = "//ol[@class='breadcrumb']")
    private WebElement breadcrumb;

    // Product carousel/images
    @FindBy(id = "myTabContent")
    private WebElement productImageContainer;

    // Methods
    public String getProductName() {
        return getText(productName);
    }

    public String getProductPrice() {
        return getText(productPrice);
    }}