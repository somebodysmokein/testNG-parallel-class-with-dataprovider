package com.browserstack.example.tests;

import org.testng.annotations.Test;

public class SecondTest extends TestBase{
    @Test
    public void test2() throws InterruptedException {
        getDriver().navigate().to("https://www.google.com/");
        getDriver().getPageSource();
        System.out.println("Test2 is running...");
        getDriver().getPageSource();
        Thread.sleep(10000);
        getDriver().getPageSource();
        System.out.println("Test2 completed...");
        getDriver().getPageSource();
    }
}
