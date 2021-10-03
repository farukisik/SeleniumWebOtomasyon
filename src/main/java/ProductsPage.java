import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductsPage  extends BasePage {

    By productNameLocator =   By.className("picture-box");
    String productPrice ="";
    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public void selectProduct(int i) {
        List<WebElement> products = getAllProducts();

        getAllProducts().get(i).click();
    }

    public List<WebElement> getAllProducts(){
        return findAll(productNameLocator);
    }

    public void setProductPrice(String price){
        this.productPrice = price;
    }

    public String getProductPrice(){
        return this.productPrice;
    }
}