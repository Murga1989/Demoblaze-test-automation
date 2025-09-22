package com.Demoblaze.base;

import com.demoblaze.utils.ConfigReader;
import com.demoblaze.utils.ExtentReportManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.Properties;

public class BaseTest {

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<WebDriverWait> waitThreadLocal = new ThreadLocal<>();
    protected Properties config;

    public static WebDriverWait getWait() {
        return null;
    }

    @BeforeSuite
    public void beforeSuite() {
        ExtentReportManager.initializeReport();
        config = ConfigReader.getProperties();
    }

    @BeforeMethod
    @Parameters({"browser"})
    public void setUp(@Optional("chrome") String browser) {
        initializeDriver(browser);
        setupDriver();
        navigateToApplication();
    }

    @AfterMethod
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
            driverThreadLocal.remove();
            waitThreadLocal.remove();
        }
    }

    @AfterSuite
    public void afterSuite() {
        ExtentReportManager.flushReport();
    }

    private void initializeDriver(String browser) {
        WebDriver driver;

        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();

                if (Boolean.parseBoolean(config.getProperty("headless", "false"))) {
                    chromeOptions.addArguments("--headless");
                }

                chromeOptions.addArguments("--disable-dev-shm-usage");
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-extensions");
                chromeOptions.addArguments("--disable-gpu");

                driver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();

                if (Boolean.parseBoolean(config.getProperty("headless", "false"))) {
                    firefoxOptions.addArguments("--headless");
                }

                driver = new FirefoxDriver(firefoxOptions);
                break;

            default:
                throw new IllegalArgumentException("Browser not supported: " + browser);
        }

        driverThreadLocal.set(driver);
    }

    private void setupDriver() {
        WebDriver driver = getDriver();

        // Set timeouts
        driver.manage().timeouts().implicitlyWait(
                Duration.ofSeconds(Long.parseLong(config.getProperty("implicit.wait", "10")))
        );
        driver.manage().timeouts().pageLoadTimeout(
                Duration.ofSeconds(Long.parseLong(config.getProperty("page.load.timeout", "30")))
        );

        // Maximize window
        if (Boolean.parseBoolean(config.getProperty("window.maximize", "true"))) {
            driver.manage().window().maximize();
        }

        // Initialize WebDriverWait
        WebDriverWait wait = new WebDriverWait(driver,
                Duration.ofSeconds(Long.parseLong(config.getProperty("explicit.wait", "30")))
        );
        waitThreadLocal.set(wait);
    }

    private void navigateToApplication() {
        getDriver().get(config.getProperty("base.url"));
    }

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }}