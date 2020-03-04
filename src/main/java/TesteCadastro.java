import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteCadastro {

    private WebDriver driver;
    private DSL dsl;
    private CampoTreinamentoPage campoTreinamentoPage;


    @Before
    public void inicializa(){
        driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1200, 765));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        dsl = new DSL(driver);
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

    @Test
    public void deveValidarNomeObrigatorio(){
        campoTreinamentoPage.cadastrar();
        Assert.assertEquals("Nome eh obrigatorio", dsl.alertaObterTextoEAceita());
    }

    @Test
    public void deveValidarSobrenomeObrigatorio(){
        campoTreinamentoPage.setNome("Matheus");
        campoTreinamentoPage.cadastrar();
        Assert.assertEquals("Sobrenome eh obrigatorio", dsl.alertaObterTextoEAceita());
    }

    @Test
    public void deveValidarSexoObrigatorio(){
        campoTreinamentoPage.setNome("Matheus");
        campoTreinamentoPage.setSobrenome("Cristino");
        campoTreinamentoPage.cadastrar();
        Assert.assertEquals("Sexo eh obrigatorio", dsl.alertaObterTextoEAceita());
    }

    @Test
    public void deveValidarComidaVegetariana(){
        campoTreinamentoPage.setNome("Matheus");
        campoTreinamentoPage.setSobrenome("Cristino");
        campoTreinamentoPage.setSexoMasculino();
        campoTreinamentoPage.setComidaCarne();
        campoTreinamentoPage.setComidaVegetariano();
        campoTreinamentoPage.cadastrar();
        Assert.assertEquals("Tem certeza que voce eh vegetariano?", dsl.alertaObterTextoEAceita());
    }

    @Test
    public void deveValidarEsportistaIndeciso(){
        campoTreinamentoPage.setNome("Matheus");
        campoTreinamentoPage.setSobrenome("Cristino");
        campoTreinamentoPage.setSexoMasculino();
        campoTreinamentoPage.setComidaCarne();
        campoTreinamentoPage.setEsporte("Karate","O que eh esporte?");
        campoTreinamentoPage.cadastrar();
        Assert.assertEquals("Voce faz esporte ou nao?", dsl.alertaObterTextoEAceita());
    }
}
