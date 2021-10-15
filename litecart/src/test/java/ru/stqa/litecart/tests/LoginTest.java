package ru.stqa.litecart.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class LoginTest {
    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);

    }


    @Test
    public void LoginTest() {
        driver.get("http://localhost:8080/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("password")).click();
        driver.findElement(By.xpath("//button[@name='login']")).click();
        wait.until(titleIs("Dashboard | My Store"));
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
