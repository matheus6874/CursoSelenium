import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteCadastro {

    private WebDriver driver;
    private CampoTreinamentoPage campoTreinamentoPage;

    @Before
    public void inicializa(){
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1200, 765));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        campoTreinamentoPage = new CampoTreinamentoPage(driver);
    }

    @After
    public void finaliza(){
        driver.quit();
    }

    @Test
    public void deveRealizarCadastroComSucesso(){
        campoTreinamentoPage.setNome("Matheus");
        campoTreinamentoPage.setSobrenome("Cristino");
        campoTreinamentoPage.setSexoMasculino();
        campoTreinamentoPage.setComidaPizza();
        campoTreinamentoPage.setEscolaridade("Mestrado");
        campoTreinamentoPage.setEsporte("Natacao");
        campoTreinamentoPage.cadastrar();

        Assert.assertEquals("Cadastrado!",campoTreinamentoPage.obterResultadoCadastro());
        Assert.assertEquals("Matheus",campoTreinamentoPage.obterNomeCadastro());
        Assert.assertEquals("Cristino",campoTreinamentoPage.obterSobrenomeCadastro());
        Assert.assertEquals("Masculino",campoTreinamentoPage.obterSexoCadastro());
        Assert.assertEquals("Pizza", campoTreinamentoPage.obterComidaCadastro());
        Assert.assertEquals("mestrado", campoTreinamentoPage.obterEscolaridadeCadastro());
        Assert.assertEquals("Natacao", campoTreinamentoPage.obterEsporteCadastro());
    }
}
