import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCadastro {

    private WebDriver driver;
    private Dsl dsl;

    @Before
    public void inicializa(){
        driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1200, 765));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        dsl = new Dsl(driver);
    }

    @After
    public void finaliza(){
        driver.quit();
    }

    @Test
    public void deveRealizarCadastroComSucesso(){
        dsl.escreverNoCampo("elementosForm:nome","Wagner");
        dsl.escreverNoCampo("elementosForm:sobrenome","Costa");
        dsl.clicar("elementosForm:sexo:0");
        dsl.clicar("elementosForm:comidaFavorita:2");
        dsl.selecionarCombo("elementosForm:escolaridade","Mestrado");
        dsl.selecionarCombo("elementosForm:esportes","Natacao");
        dsl.clicarBotao("elementosForm:cadastrar");


        Assert.assertTrue(dsl.obeterTexto("resultado").startsWith("Cadastrado"));
        Assert.assertTrue(dsl.obeterTexto("descNome").startsWith("Wagner"));
        Assert.assertEquals("Sobrenome: Costa", dsl.obeterTexto("descSobrenome"));
        Assert.assertEquals("Sexo: Masculino", dsl.obeterTexto("descSexo"));
        Assert.assertEquals("Comida: Pizza", dsl.obeterTexto("descComida"));
        Assert.assertEquals("Escolaridade: mestrad", dsl.obeterTexto("descEscolaridade"));
        Assert.assertEquals("Esportes: Natacao", dsl.obeterTexto("descEsportes"));
    }

    @Test
    public void deveValidarNomeObrigatorio(){
        dsl.clicarBotao("elementosForm:cadastrar");
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Nome eh obrigatorio", alert.getText());
    }

    @Test
    public void deveValidarSobrenomeObrigatorio(){
        dsl.escreverNoCampo("elementosForm:nome","Nome qualquer");
        dsl.clicarBotao("elementosForm:cadastrar");
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Sobrenome eh obrigatorio", alert.getText());
    }

    @Test
    public void deveValidarSexoObrigatorio(){
        dsl.escreverNoCampo("elementosForm:nome","Nome qualquer");
        dsl.escreverNoCampo("elementosForm:sobrenome","Sobrenome qualquer");
        dsl.clicarBotao("elementosForm:cadastrar");
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Sexo eh obrigatorio", alert.getText());
    }

    @Test
    public void deveValidarComidaVegetariana(){
        dsl.escreverNoCampo("elementosForm:nome","Nome qualquer");
        dsl.escreverNoCampo("elementosForm:sobrenome","Sobrenome qualquer");
        dsl.clicar("elementosForm:sexo:1");
        dsl.clicar("elementosForm:comidaFavorita:0");
        dsl.clicar("elementosForm:comidaFavorita:3");
        dsl.clicarBotao("elementosForm:cadastrar");
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Tem certeza que voce eh vegetariano?", alert.getText());
    }

    @Test
    public void deveValidarEsportistaIndeciso(){
        dsl.escreverNoCampo("elementosForm:nome","Nome qualquer");
        dsl.escreverNoCampo("elementosForm:sobrenome","Sobrenome qualquer");
        dsl.clicar("elementosForm:sexo:1");
        dsl.clicar("elementosForm:comidaFavorita:0");
        dsl.selecionarCombo("elementosForm:esportes","Karate");
        dsl.selecionarCombo("elementosForm:esportes","O que eh esporte?");
        dsl.clicarBotao("elementosForm:cadastrar");
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Voce faz esporte ou nao?", alert.getText());
    }
}
