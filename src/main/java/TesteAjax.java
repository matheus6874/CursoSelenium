import core.DSL;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static core.DriverFactory.getDriver;
import static core.DriverFactory.killDriver;

public class TesteAjax {
    private DSL dsl;

    @Before
    public void inicializa(){
        getDriver().get("https://www.primefaces.org/showcase/ui/ajax/basic.xhtml");
        dsl = new DSL();
    }

    @After
    public void finaliza(){
        killDriver();
    }

    @Test
    public void textAjax(){
        dsl.escrever("j_idt720:name","Teste");
        dsl.clicarBotao("j_idt720:j_idt723");
        WebDriverWait wait = new WebDriverWait(getDriver(),30);
        wait.until(ExpectedConditions.textToBe(By.id("j_idt720:display"),"Teste"));
        Assert.assertEquals("Teste",dsl.obterTexto("j_idt720:display"));
    }

}
