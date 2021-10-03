import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    SearchBox searchBox ;

    public HomePage(WebDriver driver) {
        super(driver);
        searchBox = new SearchBox(driver);
    }
    public boolean isOnHomePage() {
        return driver.getCurrentUrl().equals("https://www.lcwaikiki.com/tr-TR/TR");
    }
    public boolean isOnLoginPage() {
        return driver.getCurrentUrl().equals("https://www.lcwaikiki.com/tr-TR/TR/giris");
    }
    public boolean isOnProductPage() {
        return driver.getCurrentUrl().equals("https://www.lcwaikiki.com/tr-TR/TR/arama?q=pantolon");
    }

    public SearchBox searchBox(){
        return this.searchBox;
    }

}
