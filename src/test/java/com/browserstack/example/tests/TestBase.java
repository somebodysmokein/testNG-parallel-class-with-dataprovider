package com.browserstack.example.tests;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import java.io.FileReader;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TestBase {

    public static final String PATH_TO_TEST_CAPS_JSON = "src/test/resources/conf/capabilities/test_caps.json";
    // ThreadLocal gives the ability to store data individually for the current thread
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final String DOCKER_SELENIUM_HUB_URL = "http://localhost:4444/wd/hub";
    private static final String BROWSERSTACK_HUB_URL = "https://"+System.getenv("BROWSERSTACK_USERNAME")
            +":"+System.getenv("BROWSERSTACK_ACCESS_KEY")+"@hub.browserstack.com/wd/hub";
    private static final long TIMESTAMP = new Date().getTime();
    protected WebDriverWait wait;
    private String environment,url="http://google.com";


    public WebDriver getDriver() {
        return driver.get();
    }

    public boolean isRemoteExecution() {
        return environment.equalsIgnoreCase("remote");
    }

    @BeforeMethod
    public void setUp() throws Exception {


            DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "chrome");

        HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();

        // Setting the OS or device version capabilities
        browserstackOptions.put("os", "Windows");
        browserstackOptions.put("osVersion", "10");

        // Setting a build name for the test
        browserstackOptions.put("buildName", "Dependent Test");

        // Setting a session name for the test
        browserstackOptions.put("sessionName", "Dependent test");
        // Setting the Selenium version
        browserstackOptions.put("seleniumVersion", "4.0.0");
        browserstackOptions.put("seleniumCdp", true);
        browserstackOptions.put("networkLogs", "true");
        capabilities.setCapability("bstack:options", browserstackOptions);

            driver.set(new RemoteWebDriver(new URL(BROWSERSTACK_HUB_URL), capabilities));
            getDriver().get(url);
        getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wait = new WebDriverWait(getDriver(), 30);


    }

    @AfterMethod
    public void tearDown() throws Exception {
        if (getDriver() != null) {
            getDriver().quit();
        }
    }

    @DataProvider(parallel=true)
    public Object[][] dataMethod() {
        return new Object[][] { { "Browserstack" }, { "Google" } };
    }
}
