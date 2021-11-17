package ru.stqa.litecart.tests.Task19.tests;

import org.junit.After;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.litecart.tests.Task19.app.Application;

public class TestBase {
    public WebDriverWait wait;
    public Application app=new Application();



    @After
    public void stop() {
        app.driver.quit();
        app.driver = null;
    }


  }