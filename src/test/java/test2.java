import junit.framework.Assert;

import org.testng.annotations.Test;

public class test2 extends GetProperties {
    private HomePage homePage;

    private CreateAccountPage createAccountPage;

    private AddToChartPage addToChartPage;

    private RemoveFromChartPage removeFromChartPage;

    private Removed removed;

    @Test
    public void fullPath() throws InterruptedException {
        homePage = new HomePage(driver).get();
        homePage.clickLogin();
        createAccountPage = new CreateAccountPage(driver).get();
        createAccountPage.createAccount();
        Assert.assertTrue("Account not created", createAccountPage.getAccountCreated().isDisplayed());
        addToChartPage = new AddToChartPage(driver).get();
        addToChartPage.getLaptopMeniu().click();
        String productNameFromList = addToChartPage.getProduct().getAttribute("title");
        addToChartPage.addToChart();
        String productNameFromChart = addToChartPage.getProductInChart().getText();
        Assert.assertTrue(productNameFromChart.contains(productNameFromList));
        removeFromChartPage = new RemoveFromChartPage(driver).get();
        removeFromChartPage.removeFromChart();
        removed = new Removed(driver).get();
        Assert.assertTrue(removed.getEmpty().isDisplayed());
    }
}
