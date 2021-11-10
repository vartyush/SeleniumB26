package ru.stqa.litecart.tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class Task13 {
    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);

    }


    @Test
    public void task13Test() {
        driver.get("http://localhost:8080/litecart/en/");

        List<WebElement> products = wait.until(presenceOfAllElementsLocatedBy(By.xpath("//li[contains(@class, 'product')]")));
        products.get(0).click();
        WebElement count = driver.findElement(By.xpath("//span[@class='quantity']"));
        int countBefore = Integer.parseInt(count.getText());

        if (isElementPresent(driver, By.xpath("//select[@name='options[Size]']"))) {
            new Select(wait.until(presenceOfElementLocated(By.xpath("//select[@name='options[Size]']")))).selectByVisibleText("Small");
            wait.until(presenceOfElementLocated(By.xpath("//button[contains(text(),'Add To Cart')]"))).click();
        } else wait.until(presenceOfElementLocated(By.xpath("//button[contains(text(),'Add To Cart')]"))).click();


        WebElement countAfterElem = wait.until(presenceOfElementLocated(By.xpath("//span[@class='quantity']")));
//        Assert.assertEquals(Integer.parseInt(countAfterElem.getText()), (countBefore + 1));

        driver.findElement(By.linkText("Home"));

       products.get(1).click();

        int countBefore1 = Integer.parseInt(count.getText());

        if (isElementPresent(driver, By.xpath("//select[@name='options[Size]']"))) {
            new Select(wait.until(presenceOfElementLocated(By.xpath("//select[@name='options[Size]']")))).selectByVisibleText("Small");
            wait.until(presenceOfElementLocated(By.xpath("//button[contains(text(),'Add To Cart')]"))).click();
        } else wait.until(presenceOfElementLocated(By.xpath("//button[contains(text(),'Add To Cart')]"))).click();

         wait.until(presenceOfElementLocated(By.xpath("//span[@class='quantity']")));

        driver.findElement(By.linkText("Home"));

        products.get(1).click();

        int countBefore2 = Integer.parseInt(count.getText());

        if (isElementPresent(driver, By.xpath("//select[@name='options[Size]']"))) {
            new Select(wait.until(presenceOfElementLocated(By.xpath("//select[@name='options[Size]']")))).selectByVisibleText("Small");
            wait.until(presenceOfElementLocated(By.xpath("//button[contains(text(),'Add To Cart')]"))).click();
        } else wait.until(presenceOfElementLocated(By.xpath("//button[contains(text(),'Add To Cart')]"))).click();

        wait.until(presenceOfElementLocated(By.xpath("//span[@class='quantity']")));
    }

    boolean isElementPresent(WebDriver driver, By locator) {
        return driver.findElements(locator).size() > 0;
    }


    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
