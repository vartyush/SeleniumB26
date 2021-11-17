package ru.stqa.litecart.tests.Task19.tests;

import org.junit.Test;
import ru.stqa.litecart.tests.Task19.tests.TestBase;

public class AddRemoveProductTest extends TestBase {


    @Test
    public void task19Test() throws InterruptedException {
        app.mainPage.open();
        for (int i = 1; i <= 3; i++) {
            app.mainPage.findProductByIndex(i).click();
           int countBefore= app.detailedPage.getCountBefore();
            app.detailedPage.addToCart();
            app.detailedPage.assertCountAfterWithBefore(countBefore);
            app.detailedPage.goToHome();
        }
        app.mainPage.linkCheckout.click();
        app.cartPage.removeAllProducts();

    }
}

