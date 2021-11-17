package ru.stqa.litecart.tests.Task19.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class DetailedPage extends Page {
    public DetailedPage(WebDriver driver) {
        super(driver);
    }

    public void addToCart() {
        if (isElementPresent(driver, By.xpath("//select[@name='options[Size]']"))) {
            new Select(wait.until(presenceOfElementLocated(By.xpath("//select[@name='options[Size]']")))).selectByVisibleText("Small");
            wait.until(presenceOfElementLocated(By.xpath("//button[contains(text(),'Add To Cart')]"))).click();
        } else wait.until(presenceOfElementLocated(By.xpath("//button[contains(text(),'Add To Cart')]"))).click();
    }

    public int getCountBefore() {
        WebElement count = driver.findElement(By.xpath("//span[@class='quantity']"));
        int countBefore = Integer.parseInt(count.getText());
        return countBefore;
    }

    boolean isElementPresent(WebDriver driver, By locator) {
        return driver.findElements(locator).size() > 0;
    }

    public void assertCountAfterWithBefore(int countBefore) {
        WebElement countAfterElem = wait.until(presenceOfElementLocated(By.xpath("//span[@class='quantity' and text()=" + (countBefore + 1) + "]")));
        Assert.assertEquals(Integer.parseInt(countAfterElem.getText()), (countBefore + 1));

    }
    public void goToHome(){
        driver.findElement(By.linkText("Home"));

    }
}