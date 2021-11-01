package ru.stqa.litecart.tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfAllElementsLocatedBy;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class Task10 {

    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);

    }


    @Test
    public void task10Test() throws InterruptedException {
        driver.get("http://localhost:8080/litecart/en/");
        WebElement nameProduktElem = wait.until(presenceOfElementLocated(By.xpath("//div[@id='box-campaigns']//following-sibling::div[@class='name']")));
        String nameProdukt = nameProduktElem.getText();
        WebElement regularPriceElem = driver.findElement(By.xpath("//div[@id='box-campaigns']//following-sibling::s[@class='regular-price']"));
        String regularPrice = regularPriceElem.getText();
        WebElement campaignPriceElem = driver.findElement(By.xpath("//div[@id='box-campaigns']//following-sibling::strong[@class='campaign-price']"));
        String campaignPrice = campaignPriceElem.getText();
        // в) обычная цена зачёркнутая
        Assert.assertEquals(regularPriceElem.getCssValue("text-decoration-line"), "line-through");

        //в) обычная цена  серая
        String rgbFormatCam = campaignPriceElem.getCssValue("color");
        String rgbFormatReg = regularPriceElem.getCssValue("color");

        int red = Color.fromString(rgbFormatReg).getColor().getRed();
        int green = Color.fromString(rgbFormatReg).getColor().getGreen();
        int blue = Color.fromString(rgbFormatReg).getColor().getBlue();
        assertEquals(red, green, blue);

        //г) акционная цена красная
        int greenCam = Color.fromString(rgbFormatCam).getColor().getGreen();
        int blueCam = Color.fromString(rgbFormatCam).getColor().getBlue();
        Assert.assertTrue(greenCam == 0 && blueCam == 0);
        //г) акционная жирная
        Assert.assertEquals("700", campaignPriceElem.getCssValue("font-weight"));

        // д) акционная цена крупнее, чем обычная
        String sizeReg = (regularPriceElem.getCssValue("font-size"));
        String sizeCam = (campaignPriceElem.getCssValue("font-size"));
        double sizeRegDig = Double.parseDouble(sizeReg.replaceAll("[^0-9?!\\.]", ""));
        double sizeCamDig = Double.parseDouble(sizeCam.replaceAll("[^0-9?!\\.]", ""));

        Assert.assertTrue(sizeRegDig < sizeCamDig);


        nameProduktElem.click();


        WebElement nameProduktDetail = wait.until(presenceOfElementLocated(By.xpath("//div[@id='box-product']//following-sibling::h1[@class='title']")));
        WebElement regularPriceDetail = wait.until(presenceOfElementLocated((By.xpath("//div[@id='box-product']//following-sibling::s[@class='regular-price']"))));
        WebElement campaignPriceDetail = wait.until(presenceOfElementLocated((By.xpath("//div[@id='box-product']//following-sibling::strong[@class='campaign-price']"))));
// а) на главной странице и на странице товара совпадает текст названия товара
        Assert.assertEquals(nameProdukt, nameProduktDetail.getText());
        //б) на главной странице и на странице товара совпадают цены (обычная и акционная)
        Assert.assertEquals(regularPrice, regularPriceDetail.getText());
        Assert.assertEquals(campaignPrice, campaignPriceDetail.getText());
        // в) обычная цена зачёркнутая
        Assert.assertEquals(regularPriceDetail.getCssValue("text-decoration-line"), "line-through");

        // г) акционная жирная
        Assert.assertEquals("700", campaignPriceDetail.getCssValue("font-weight"));

        // д) акционная цена крупнее, чем обычная
        String sizeRegDet = (regularPriceDetail.getCssValue("font-size"));
        String sizeCamDet = (campaignPriceDetail.getCssValue("font-size"));
        double sizeRegIntDet = Double.parseDouble(sizeRegDet.replaceAll("[^0-9?!\\.]", ""));
        double sizeCamIntDet = Double.parseDouble(sizeCamDet.replaceAll("[^0-9?!\\.]", ""));
        Assert.assertTrue(sizeCamIntDet > sizeRegIntDet);


        //в) обычная цена  серая
        String rgbFormatCamDetail = campaignPriceDetail.getCssValue("color");
        String rgbFormatRegDetail = regularPriceDetail.getCssValue("color");
        int redDet = Color.fromString(rgbFormatRegDetail).getColor().getRed();
        int greenDet = Color.fromString(rgbFormatRegDetail).getColor().getGreen();
        int blueDet = Color.fromString(rgbFormatRegDetail).getColor().getBlue();
        assertEquals(redDet, greenDet, blueDet);

        //г) акционная цена красная
        int greenCamDet = Color.fromString(rgbFormatCamDetail).getColor().getGreen();
        int blueCamDet = Color.fromString(rgbFormatCamDetail).getColor().getBlue();
        Assert.assertTrue(greenCamDet == 0 && blueCamDet == 0);
    }





    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
