package ru.stqa.litecart.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class Task11 {
    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
       wait = new WebDriverWait(driver, 10);


      //  driver = new FirefoxDriver();

      //  wait = new WebDriverWait(driver, 15);
    }


    @Test
    public void task11Test() throws InterruptedException {
        driver.get("http://localhost:8080/litecart/en/");
        long now = System.currentTimeMillis();
        String email = "vartyush" + now + "@yandex.ru";
        wait.until(presenceOfElementLocated(new By.ByLinkText("New customers click here"))).click();
        driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("Viktoria");
        driver.findElement(By.xpath("//input[@name='tax_id']")).clear();
        driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("Rubanova");
        driver.findElement(By.xpath("//input[@name='address1']")).sendKeys("Address1");
        driver.findElement(By.xpath("//input[@name='postcode']")).sendKeys("12345");
        driver.findElement(By.xpath("//input[@name='city']")).sendKeys("Saint-Peterburg");
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys(email);
        driver.findElement(By.xpath("//span[contains(@class,'select2-container')]")).click();
        driver.findElement(By.xpath("//li[contains(text(),'United States')]")).click();
        driver.findElement(By.xpath("//input[@name='password']")).clear();
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("12345");
        driver.findElement(By.xpath("//input[@name='confirmed_password']")).sendKeys("12345");
        driver.findElement(By.xpath("//input[@name='phone']")).sendKeys("+79046493317");
        driver.findElement(By.xpath("//button[@name='create_account']")).click();
        wait.until(presenceOfElementLocated(new By.ByLinkText("Logout"))).click();
        wait.until(presenceOfElementLocated(By.xpath("//input[@name='email']"))).sendKeys(email);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("12345");
        driver.findElement(By.xpath("//button[@name='login']")).click();
        wait.until(presenceOfElementLocated(new By.ByLinkText("Logout"))).click();
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}