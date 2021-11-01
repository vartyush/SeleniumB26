package ru.stqa.litecart.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class Task7 {
    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);

    }


    @Test
    public void task7Test() throws InterruptedException {
        driver.get("http://localhost:8080/litecart/admin");
        wait.until(presenceOfElementLocated(By.name("username"))).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.xpath("//button[@name='login']")).click();
       List <WebElement> app= wait.until(presenceOfAllElementsLocatedBy(By.xpath("//li[contains(@id,'app')]")));
        int size = app.size();
        for (int i=1; i<=size; i++) {
           driver.findElement(By.xpath("(//li[contains(@id,'app')])"+"["+i+"]")).click();
            driver.findElement(By.tagName("head"));
            int subSize = driver.findElements(By.xpath("//li[contains(@id,'doc')]")).size();
            if (subSize > 0) {
                for (int j=1; j<=subSize; j++) {
                    driver.findElement(By.xpath("(//li[contains(@id,'doc')])"+"["+j+"]")).click();
                    driver.findElement(By.tagName("head"));

                }
            }

        }
    }
    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}

