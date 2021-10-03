import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class DemoTest extends BaseTest {
    HomePage homePage;
    LoginPage loginPage;
    ProductsPage productsPage;

    @Test
    @Order(1)
    public void openMainUrl() {
        homePage = new HomePage(driver);
        Assertions.assertTrue(homePage.isOnHomePage(),
                "Not on home page!");
    }

    @Test
    @Order(2)
    public void loginPage() {
        homePage = new HomePage(driver);
        WebElement loginIcon = homePage.find(By.id("header-user-section"));
        loginIcon.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assertions.assertTrue(homePage.isOnLoginPage(),
                "Not on login page!");
    }

    @Test
    @Order(3)
    public void signIn() {
        loginPage = new LoginPage(driver);

        WebElement loginEmail = loginPage.find(By.id("LoginEmail"));
        loginEmail.sendKeys("farukiisik@gmail.com");

        WebElement loginPassword = loginPage.find(By.id("Password"));
        loginPassword.sendKeys("F.isik06!");

        WebElement loginlink = loginPage.find(By.id("loginLink"));
        loginlink.click();
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        Assertions.assertTrue(loginPage.isOnHomePage(),
                "Not on home page!");
    }

    @Test
    @Order(4)
    public void searchProduct() {
        homePage = new HomePage(driver);
        homePage.searchBox().search("pantolon");
        Assertions.assertTrue(homePage.isOnProductPage(),
                "Not on product page!");
    }

    @Test
    @Order(5)
    public void scrollPage() {
        homePage = new HomePage(driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,350)", "");
        WebElement loadMoreButton = homePage.find(By.className("lazy-load-button"));
        loadMoreButton.click();
    }

    @Test
    @Order(6)
    public void selectProduct() {
        productsPage = new ProductsPage(driver);
       /* BEDEN VEYA YAS SECIMI SEPETE EKLEMEYE ENGEL OLDUĞU ICIN  Random KUTUPHANESI KULLANILAMADI.
            Random rand = new Random();
            productsPage.selectProduct(rand.nextInt(productsPage.getAllProducts().size()));
        */
        productsPage.selectProduct(6);
        WebElement productPrice = driver.findElement(By.xpath("//*[@id=\"rightInfoBar\"]/div[1]/div/div[2]/div/div/div/span[2]"));
        productsPage.setProductPrice(productPrice.getText());
    }

    @Test
    @Order(7)
    public void addProductToCart() {
        WebElement element = driver.findElement(By.id("pd_add_to_cart"));
        element.click();
        WebElement cardLocator = driver.findElement(By.xpath("//*[@id=\"spanCart\"]"));
        Assertions.assertEquals(1, Integer.parseInt(cardLocator.getText()));
    }


    @Test
    @Order(8)
    public void checkPrice() {
        WebElement cardLocator = driver.findElement(By.id("spanCart"));
        cardLocator.click();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        WebElement productPrice = driver.findElement(By.className("rd-cart-item-price"));
        Assertions.assertEquals(productPrice.getText(), productsPage.getProductPrice());
    }


    @Test
    @Order(9)
    public void productCountIncrease() {
        WebElement countButton = driver.findElement(By.className("plus"));
        countButton.click();

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        WebElement countText = driver.findElement(By.className("item-quantity-input"));

        Assertions.assertEquals(2,
                Integer.parseInt(countText.getText()));

    }

    @Test
    @Order(10)
    public void deleteProduct() {
        WebElement deleteButton = driver.findElement(By.className("cart-square-link"));
        deleteButton.click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        WebElement deleteModalButton = driver.findElement(By.className("inverted-modal-button"));
        deleteModalButton.click();

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        WebElement message = driver.findElement(By.className("cart-empty-title"));

        Assertions.assertEquals("Sepetinizde ürün bulunmamaktadır.", message.getText());
    }

}
