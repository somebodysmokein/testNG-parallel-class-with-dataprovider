package com.browserstack.example.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class FirstTest extends TestBase{

    @Test(dataProvider = "dataMethod")
    public void test1(String data) throws InterruptedException {
        getDriver().navigate().to("https://www.google.com/");
        System.out.println("Test1 is running...");
        Thread.sleep(10000);
        WebElement searchField = getDriver().findElement(By.name("q"));
        searchField.sendKeys(data);
        WebElement searchButton = getDriver().findElement(By.name("btnK"));
        System.out.println("Test1 completed...");

    }
}
