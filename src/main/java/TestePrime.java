import core.DSL;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestePrime {

    private WebDriver driver;
    private DSL dsl;

    @Before
    public void inicializa(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        dsl = new DSL(driver);
    }

    @After
    public void finaliza(){
        //driver.quit();
    }

    @Test
    public void deveInteragirComRadioPrime(){
        driver.get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml");
        dsl.clicarRadio(By.xpath("//input[@id='j_idt86:console:0']/../..//span"));
        Assert.assertTrue(dsl.isRadioMarcado("j_idt86:console:0"));
        dsl.clicarRadio(By.xpath("//label[.='PS4']/..//span"));
        Assert.assertTrue(dsl.isRadioMarcado("j_idt86:console:1"));
    }

    @Test
    public void deveInteragirComSelectPrime(){
        driver.get("https://www.primefaces.org/showcase/ui/input/oneMenu.xhtml");
        dsl.selecionarComboPrime("j_idt86:console", "Xbox One");
        Assert.assertEquals("Xbox One", dsl.obterTexto("j_idt86:console_label"));
    }
}


