import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static core.DriverFactory.killDriver;
import static core.DriverFactory.getDriver;

public class TesteCadastro {

    private CampoTreinamentoPage campoTreinamentoPage;

    @Before
    public void inicializa(){
        getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        campoTreinamentoPage = new CampoTreinamentoPage();
    }

    @After
    public void finaliza(){
        killDriver();
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
