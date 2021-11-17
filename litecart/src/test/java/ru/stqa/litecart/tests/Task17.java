package ru.stqa.litecart.tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfAllElementsLocatedBy;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class Task17 {

    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);

    }


    @Test
    public void task17Test() {
        driver.get("http://localhost:8080/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        wait.until(presenceOfElementLocated(By.name("username"))).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.xpath("//button[@name='login']")).click();

        List<WebElement> listProducts = wait.until(presenceOfAllElementsLocatedBy(By.xpath("//a[@title='Edit']")));

        for (int i = 1; i <= listProducts.size(); i++) {
            wait.until(presenceOfElementLocated(By.xpath("//a[@title='Edit']"))).click();
            for (LogEntry l : driver.manage().logs().get("browser").getAll()) {
                System.out.println(l);
            }
            driver.findElement(By.xpath("//button[@value='Cancel']")).click();
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}