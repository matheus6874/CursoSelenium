import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteGoogle {
    private WebDriver driver = new FirefoxDriver();;

    @Before
    public void inicializa(){
        driver.manage().window().maximize();
    }

    @After
    public void fechaNavegador(){
        driver.quit();
    }

    @Test
    public void teste(){
        driver.get("https://www.google.com");
        Assert.assertEquals("Google",driver.getTitle());
    }

}
