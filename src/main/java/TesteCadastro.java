import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteCadastro {

    private WebDriver driver;
    private CampoTreinamentoPage campoTreinamentoPage;

    @Before
    public void inicializa(){
        driver = new FirefoxDriver();
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
        campoTreinamentoPage.setEsporte("Natação");
        campoTreinamentoPage.cadastrar();

        Assert.assertTrue(campoTreinamentoPage.obterResultadoCadastro().startsWith("Cadastrado!"));
        Assert.assertTrue(campoTreinamentoPage.obterNomeCadastro().endsWith("Matheus"));
        Assert.assertTrue(campoTreinamentoPage.obterSobrenomeCadastro().endsWith("Cristino"));
        Assert.assertEquals("Sexo: Masculino",campoTreinamentoPage.obterSexoCadastro());
        Assert.assertEquals("Comida: Pizza", campoTreinamentoPage.obterComidaCadastro());
        Assert.assertEquals("Escolaridade: mestrado", campoTreinamentoPage.obterEscolaridadeCadastro());
        Assert.assertEquals("Esportes: Natacao", campoTreinamentoPage.obterEsporteCadastro());
    }
}
