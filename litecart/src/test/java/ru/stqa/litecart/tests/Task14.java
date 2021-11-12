package ru.stqa.litecart.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfAllElementsLocatedBy;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class Task14 {

    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);

    }

    @Test
    public void task10Test() throws InterruptedException {
        driver.get("http://localhost:8080/litecart/admin/?app=countries&doc=countries");
        wait.until(presenceOfElementLocated(By.name("username"))).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.xpath("//button[@name='login']")).click();

        wait.until(presenceOfElementLocated(By.xpath("(//i[@class='fa fa-pencil'])[1]"))).click();
        List<WebElement> externalLinks = wait.until(presenceOfAllElementsLocatedBy(By.xpath("//a[@target='_blank']")));
        for (WebElement link : externalLinks) {

            String originalWindow = driver.getWindowHandle();
            Set<String> existingWindows = driver.getWindowHandles();
            link.click();
            String newWindow = wait.until(anyWindowOtherThan(existingWindows));
            driver.switchTo().window(newWindow);
            driver.close();
            driver.switchTo().window(originalWindow);


        }
    }

    public ExpectedCondition<String> anyWindowOtherThan(Set<String> oldWindows) {
        return new ExpectedCondition<String>() {

            public String apply(WebDriver driver) {
                Set<String> handles = driver.getWindowHandles();
                handles.removeAll(oldWindows);
                return handles.size() > 0 ? handles.iterator().next() : null;
            }
        };
    }


    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}