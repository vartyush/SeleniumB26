package ru.stqa.litecart.tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class Task12 {
    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);

    }


    @Test
    public void task12Test() throws InterruptedException, IOException {

        driver.get("http://localhost:8080/litecart/admin");
        wait.until(presenceOfElementLocated(By.name("username"))).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.xpath("//button[@name='login']")).click();
        wait.until(presenceOfElementLocated(By.xpath("//span[contains(text(), 'Catalog')]"))).click();

        wait.until(presenceOfElementLocated(By.linkText("Add New Product"))).click();
        wait.until(presenceOfElementLocated(By.xpath("//input[@name='name[en]']"))).sendKeys("Toothbrush");
        driver.findElement(By.xpath("//input[@name='product_groups[]']")).click();
        driver.findElement(By.xpath(" //td[contains(text(), 'Unisex')]/preceding-sibling::td/input[@name='product_groups[]']")).click();
        driver.findElement(By.xpath("//input[@name='date_valid_from']")).sendKeys("02/20/2021");
        driver.findElement(By.xpath("//input[@name='date_valid_to']")).sendKeys("02/20/2022");

        String absolute = new File("src\\main\\resources\\Toothbrush.jpg").getCanonicalPath();
        driver.findElement(By.xpath("//input[@name='new_images[]']")).sendKeys(absolute);
        driver.findElement(By.linkText("Information")).click();
        new Select(wait.until(presenceOfElementLocated(By.xpath("//select[@name='manufacturer_id']")))).selectByVisibleText("ACME Corp.");
        driver.findElement(By.xpath("//input[@name='short_description[en]']")).sendKeys("Toothbrush");
        driver.findElement(By.xpath("//div[@class=\"trumbowyg-editor\"]")).sendKeys("Toothbrush is saled");
        driver.findElement(By.linkText("Prices")).click();
        wait.until(presenceOfElementLocated(By.xpath("//input[@name='purchase_price']"))).clear();
        driver.findElement(By.xpath("//input[@name='purchase_price']")).sendKeys("50");
        new Select(driver.findElement(By.xpath("//select[@name='purchase_price_currency_code']"))).selectByVisibleText("Euros");
        driver.findElement(By.xpath("//button[@name='save']")).click();
        driver.findElement(By.linkText("Toothbrush"));
    }





    @After
    public void stop() {
        driver.quit();
        driver = null;
    }}