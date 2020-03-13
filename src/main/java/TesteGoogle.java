import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import static core.DriverFactory.getDriver;
import static core.DriverFactory.killDriver;

public class TesteGoogle {

    @After
    public void finaliza(){
        killDriver();
    }

    @Test
    public void teste() {
//		System.setProperty("webdriver.gecko.driver", "/Users/wcaquino/Downloads/geckodriver");
//		System.setProperty("webdriver.chrome.driver", "/Users/wcaquino/Downloads/chromedriver");
//		WebDriver driver = new ChromeDriver();
//		WebDriver driver = new InternetExplorerDriver();
        getDriver().get("http://www.google.com");
        Assert.assertEquals("Google", getDriver().getTitle());
    }

}
