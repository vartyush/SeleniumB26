package ru.stqa.litecart.tests.Task19.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page {


        protected WebDriver driver;
        protected WebDriverWait wait;

        public Page(WebDriver driver) {
            this.driver = driver;
            wait = new WebDriverWait(driver, 10);
        }
    }

