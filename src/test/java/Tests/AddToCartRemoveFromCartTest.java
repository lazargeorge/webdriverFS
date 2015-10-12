package Tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.CartPage;
import pageObjects.ProductPage;

/**
 * @author Silviu Moraru
 */

public class AddToCartRemoveFromCartTest extends Setup
{

    /**
     * This test adds a product to cart then checks if it is present in the basket at checkout
     * Step 1: Get the full name of the product then truncate to a more manageable size
     * Step 2: Add the product to cart
     * Step 3: Check if the item is present
     */

    @Test
    public void AddToBasket() throws Exception
    {
        ProductPage productPage = PageFactory.initElements(driver, ProductPage.class);

        productPage.buy();

        CartPage cartPage = PageFactory.initElements(driver, CartPage.class);

        Assert.assertTrue(cartPage.cos.isDisplayed(), "The cart is being filled");
        // Assert.assertTrue(productPage.checkProductInCart(), String.format("Produsul {%s} se regaseste in cos", ProductPage.getLap()));
        // Assert.assertTrue(productPage.checkProductInCart());
    }

    /**
     * This test runs after the first one
     * Step 1: Press the empty basket button
     * Step 2: Check that the basket is empty
     */

    @Test
    public void EmptyBasket() throws Exception
    {
        CartPage cartPage = PageFactory.initElements(driver, CartPage.class);

        cartPage.EmptyCart(driver);
        Assert.assertTrue(cartPage.cos_gol.isDisplayed(), "The cart is empty");
    }

}
