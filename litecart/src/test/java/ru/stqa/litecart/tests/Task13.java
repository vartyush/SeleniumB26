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
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class Task13 {
    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);

    }


    @Test
    public void task13Test() throws InterruptedException {
        driver.get("http://localhost:8080/litecart/en/");

        for (int i = 1; i <= 3; i++) {
            List<WebElement> products = wait.until(presenceOfAllElementsLocatedBy(By.xpath("//li[contains(@class, 'product')]")));
            products.get(i).click();
            WebElement count = driver.findElement(By.xpath("//span[@class='quantity']"));
            int countBefore = Integer.parseInt(count.getText());

            if (isElementPresent(driver, By.xpath("//select[@name='options[Size]']"))) {
                new Select(wait.until(presenceOfElementLocated(By.xpath("//select[@name='options[Size]']")))).selectByVisibleText("Small");
                wait.until(presenceOfElementLocated(By.xpath("//button[contains(text(),'Add To Cart')]"))).click();
            } else wait.until(presenceOfElementLocated(By.xpath("//button[contains(text(),'Add To Cart')]"))).click();


            WebElement countAfterElem = wait.until(presenceOfElementLocated(By.xpath("//span[@class='quantity' and text()=" + (countBefore + 1) + "]")));
            Assert.assertEquals(Integer.parseInt(countAfterElem.getText()), (countBefore + 1));

            driver.findElement(By.linkText("Home"));
        }

        wait.until(presenceOfElementLocated(By.linkText("Checkout »"))).click();

        List<WebElement> listForRemoveProducts = wait.until(presenceOfAllElementsLocatedBy(By.xpath("//a[contains(@class,'inact')]")));
        for (int i = 1; i <= listForRemoveProducts.size(); i++) {
            if (isElementPresent(driver, By.xpath("//a[contains(@class,'act')]"))) {
                driver.findElement(By.xpath("//a[contains(@class,'act')]")).click();
                wait.until(presenceOfElementLocated(By.xpath("//div[@class='billing-address']")));
                wait.until(elementToBeClickable(By.xpath("(//button[@name='remove_cart_item'])[1]"))).click();
                wait.until(stalenessOf(driver.findElement(By.xpath("//div[@class='billing-address']"))));
                driver.findElement(By.xpath("//div[@class='billing-address']"));
            } else {
                wait.until(presenceOfElementLocated(By.xpath("//div[@class='billing-address']")));
                wait.until(elementToBeClickable(By.xpath("(//button[@name='remove_cart_item'])[1]"))).click();
                wait.until(stalenessOf(driver.findElement(By.xpath("//div[@class='billing-address']"))));

            }
        }
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
