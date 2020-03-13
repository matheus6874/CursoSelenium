import core.DSL;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static core.DriverFactory.getDriver;
import static core.DriverFactory.killDriver;

public class TesteFramesEJanelas {

    private DSL dsl;

    @Before
    public void inicializa(){
        getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        dsl = new DSL();
    }

    @After
    public void finaliza(){
        killDriver();
    }

    @Test
    public void deveInteragirComFrames(){
        dsl.entrarFrame("frame1");
        dsl.clicarBotao("frameButton");
        String msg = dsl.alertaObterTextoEAceita();
        Assert.assertEquals("Frame OK!", msg);

        dsl.sairFrame();
        dsl.escrever("elementosForm:nome", msg);
    }

    @Test
    public void deveInteragirComJanelas(){
        dsl.clicarBotao("buttonPopUpEasy");
        dsl.trocarJanela("Popup");
        dsl.escrever(By.tagName("textarea"), "Deu certo?");
        getDriver().close();
        dsl.trocarJanela("");
        dsl.escrever(By.tagName("textarea"), "e agora?");
    }

    @Test
    public void deveInteragirComJanelasSemTitulo(){
        dsl.clicarBotao("buttonPopUpHard");
        System.out.println(getDriver().getWindowHandle());
        System.out.println(getDriver().getWindowHandles());
        dsl.trocarJanela((String) getDriver().getWindowHandles().toArray()[1]);
        dsl.escrever(By.tagName("textarea"), "Deu certo?");
        dsl.trocarJanela((String) getDriver().getWindowHandles().toArray()[0]);
        dsl.escrever(By.tagName("textarea"), "e agora?");
    }

    @Test
    public void deveInteragirComFrameEscondido(){
        WebElement frame = getDriver().findElement(By.id("frame2"));
        //dsl.executarJs("window.scroll(0),argumentos[0]",frame.getLocation().y); scroll para baixo
        dsl.entrarFrame("frame2");
        dsl.clicarBotao("frameButton");
        String msg = dsl.alertaObterTextoEAceita();
        Assert.assertEquals("Frame OK!", msg);
    }

}
