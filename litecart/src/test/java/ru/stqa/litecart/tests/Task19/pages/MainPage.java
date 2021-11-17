package ru.stqa.litecart.tests.Task19.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfAllElementsLocatedBy;

public class MainPage extends Page {


    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy (xpath = ("//a[contains(text(),'Checkout »')]"))
    public WebElement linkCheckout;


    public void open(){
        driver.get("http://localhost:8080/litecart/en/");

    }
    public WebElement findProductByIndex(int i){
    List<WebElement> products = wait.until(presenceOfAllElementsLocatedBy(By.xpath("//li[contains(@class, 'product')]")));
            return products.get(i);
}
    }