import org.openqa.selenium.WebDriver;

public class LoginPage  extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }
    public boolean isOnHomePage() {
        return driver.getCurrentUrl().equals("https://www.lcwaikiki.com/tr-TR/TR");
    }
}
