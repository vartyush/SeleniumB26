package ru.stqa.litecart.tests.Task19.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class CartPage extends Page {

    public CartPage(WebDriver driver) {
        super(driver);
    }

public void removeAllProducts(){
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
}

