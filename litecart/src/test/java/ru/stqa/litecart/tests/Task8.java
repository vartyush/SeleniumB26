package ru.stqa.litecart.tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfAllElementsLocatedBy;

public class Task8 {

    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);

    }


    @Test
    public void task8Test() throws InterruptedException {
        driver.get("http://localhost:8080/litecart");
        List<WebElement> produkts = wait.until(presenceOfAllElementsLocatedBy(By.xpath("//li[contains(@class,\"product\")]")));
        for (WebElement duck : produkts) {
            Assert.assertTrue(duck.findElements(By.xpath("//div[contains(@class,\"sticker\")]")).size()==1);
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
