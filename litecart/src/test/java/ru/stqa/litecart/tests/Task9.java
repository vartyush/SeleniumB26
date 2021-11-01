package ru.stqa.litecart.tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfAllElementsLocatedBy;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class Task9 {

    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);

    }


    @Test
    public void collectContriesTest() {
        driver.get("http://localhost:8080/litecart/admin/?app=countries&doc=countries");
        wait.until(presenceOfElementLocated(By.name("username"))).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.xpath("//button[@name='login']")).click();
        List<WebElement> countries = wait.until(presenceOfAllElementsLocatedBy(By.xpath("//form[contains(@name, \"countries\")]//td[5]")));
        ArrayList<String> arrayCountries = new ArrayList<>();


        for (WebElement counter : countries) {
            arrayCountries.add(counter.getText());
        }           /* Sort statement*/
        List<String> collectList = arrayCountries.stream().sorted().collect(Collectors.toList());

        Assert.assertEquals(collectList, arrayCountries);
    }

    @Test
    public void collectZonesTest() {
        driver.get("http://localhost:8080/litecart/admin/?app=countries&doc=countries");
        wait.until(presenceOfElementLocated(By.name("username"))).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.xpath("//button[@name='login']")).click();

        List<WebElement> zones = wait.until(presenceOfAllElementsLocatedBy(By.xpath("//form[contains(@name, \"countries\")]//td[6]")));
        for (int i = 1; i <= zones.size(); i++) {
            WebElement zone = driver.findElement(By.xpath("(//form[contains(@name, 'countries')]//td[6])" + "[" + i + "]"));
            if (Integer.parseInt(zone.getText()) > 0) {
                zone.findElement(By.xpath(".//preceding-sibling::td/a")).click();

                List<WebElement> zoneList = wait.until(presenceOfAllElementsLocatedBy(By.xpath(("//td/input[contains(@name,'zones') and contains(@name, 'name')]"))));
                ArrayList<String> arrayZones = new ArrayList<>();


                for (WebElement zoneElement : zoneList) {
                    arrayZones.add(zoneElement.getAttribute("value"));
                }           /* Sort statement*/
                List<String> collectZones = arrayZones.stream().sorted().collect(Collectors.toList());

                Assert.assertEquals(collectZones, arrayZones);
                driver.findElement(By.xpath("//span[contains(text(), 'Countries')]")).click();

            }
        }
    }

    @Test
    public void collectGeoZonesTest() {
        driver.get("http://localhost:8080/litecart/admin/?app=geo_zones&doc=geo_zones");
        wait.until(presenceOfElementLocated(By.name("username"))).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.xpath("//button[@name='login']")).click();

        List<WebElement> geoZones = wait.until(presenceOfAllElementsLocatedBy(By.xpath("//form[@name=\"geo_zones_form\"]//td[3]")));
        for (int i = 1; i <= geoZones.size(); i++) {
            driver.findElement(By.xpath("(//form[@name=\"geo_zones_form\"]//td[3]/a)" + "[" + i + "]")).click();

            List<WebElement> geoZoneList = wait.until(presenceOfAllElementsLocatedBy(By.xpath("//select[contains(@name, 'zone_code')]/option[@selected='selected']")));

            ArrayList<String> arrayGeoZones = new ArrayList<>();


            for (WebElement geoZoneElem : geoZoneList) {
                System.out.println(geoZoneElem.getText());
                arrayGeoZones.add(geoZoneElem.getText());
            }           /* Sort statement*/
            List<String> collectGeoZones = arrayGeoZones.stream().sorted().collect(Collectors.toList());

            Assert.assertEquals(collectGeoZones, arrayGeoZones);
            driver.findElement(By.xpath("//span[contains(text(), 'Geo Zones')]")).click();

        }
    }




    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

    //form[contains(@name, "countries")]//td[5]
}
