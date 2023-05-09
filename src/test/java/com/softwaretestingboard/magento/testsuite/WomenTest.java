package com.softwaretestingboard.magento.testsuite;

import com.softwaretestingboard.magento.customlisteners.CustomListeners;
import com.softwaretestingboard.magento.pages.HomePage;
import com.softwaretestingboard.magento.testbase.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

@Listeners(CustomListeners.class)

public class WomenTest extends BaseTest {
    HomePage homePage = new HomePage();

    @BeforeMethod(alwaysRun = true)
    public void inIt() {
        homePage = new HomePage();
    }

    @Test(groups = {"sanity", "regression"})
    //1. verifyTheSortByProductNameFilter
    public void verifyTheSortByProductNameFilter() throws InterruptedException {
        Thread.sleep(1000);
        homePage.mouseHoverToWomenMenu();
        homePage.mouseHoverToTopMenu();
        homePage.clickOnJacket();
        List<WebElement> jacketsElementsList = driver.findElements(By.xpath("//strong[@class='product name product-item-name']//a"));
        ArrayList<String> jacketsNameListBefore = new ArrayList<>();
        for (WebElement e : jacketsElementsList) {
            jacketsNameListBefore.add(e.getText());
        }
        System.out.println(jacketsNameListBefore);
        jacketsNameListBefore.sort(String.CASE_INSENSITIVE_ORDER);
        //* Select Sort By filter “Product Name”
        homePage.verifySortByProductName();
        //* Verify the products name display in alphabetical order
        Assert.assertEquals(homePage.afterSortingByProductName(), homePage.defaultProductList());

    }

    @Test(groups = {"smoke", "regression"})
    public void verifyTheSortByPriceFilter() throws InterruptedException {
        Thread.sleep(1000);
        homePage.mouseHoverToWomenMenu();
        //* Mouse Hover on Tops
        homePage.mouseHoverToTopMenu();
        //* Click on Jackets
        homePage.clickOnJacket();
        Thread.sleep(1000);
        List<WebElement> jacketsElementsList = driver.findElements(By.xpath("//span[@class='price-wrapper ']//span"));
        ArrayList<Double> jacketsPriceListBefore = new ArrayList<>();
        for (WebElement e : jacketsElementsList) {
            jacketsPriceListBefore.add(Double.valueOf(e.getText().replace("$", "")));
        }
        homePage.verifySortByPrice();
        Thread.sleep(1000);
        Assert.assertEquals(homePage.defaultProductsPriceList(), homePage.afterSortingByProductsPriceList());
    }
}
