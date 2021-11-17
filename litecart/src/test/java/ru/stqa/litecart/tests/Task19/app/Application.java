package ru.stqa.litecart.tests.Task19.app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.litecart.tests.Task19.pages.CartPage;
import ru.stqa.litecart.tests.Task19.pages.DetailedPage;
import ru.stqa.litecart.tests.Task19.pages.MainPage;

public class Application {
    public WebDriver driver;
    public WebDriverWait wait;

    public MainPage mainPage;
    public DetailedPage detailedPage;
    public CartPage cartPage;

    public Application() {
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        detailedPage = new DetailedPage(driver);
        cartPage = new CartPage(driver);
        wait = new WebDriverWait(driver, 10);

    }


    }
