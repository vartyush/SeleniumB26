package ru.stqa.training.selenium;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;


public class IExplorerTest {

    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void start() {
        DesiredCapabilities caps = new DesiredCapabilities();
      //  caps.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
        caps.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
         driver = new InternetExplorerDriver(caps);
        wait = new WebDriverWait(driver, 10);
    }


    @Test
    public void myFirstTest() {
        driver.get("http://www.google.com");
       // driver.findElement(By.name("btnG")).click();
        driver.findElement(By.name("q")).sendKeys("webdriver");
        driver.findElement(By.name("btnK")).click();
        wait.until(titleIs("webdriver - Поиск в Google"));
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
