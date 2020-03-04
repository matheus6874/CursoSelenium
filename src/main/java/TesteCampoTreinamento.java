import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCampoTreinamento {

    private WebDriver driver;
    private Dsl dsl;

    @Before
    public void inicializa(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        dsl = new Dsl(driver);
    }

    @After
    public void finaliza(){
        driver.quit();
    }

    @Test
    public void testeTextField(){
        dsl.escreverNoCampo("elementosForm:nome","Teste de escrita");
        Assert.assertEquals("Teste de escrita",dsl.obsterValorCampo("elementosForm:nome"));
    }

    @Test
    public void deveIntegarirComTextArea(){
        dsl.escreverNoCampo("elementosForm:sugestoes","teste\n\naasldjdlks\nUltima linha");
        Assert.assertEquals("teste\n\naasldjdlks\nUltima linha",dsl.obsterValorCampo("elementosForm:sugestoes"));
    }

    @Test
    public void deveIntegarirComRadioButton(){
        dsl.clicar("elementosForm:sexo:0");
        Assert.assertTrue(dsl.verificaSeEstaSelecionado("elementosForm:sexo:0"));
    }

    @Test
    public void deveIntegarirComCheckbox(){
        dsl.clicar("elementosForm:comidaFavorita:2");
        Assert.assertTrue(dsl.verificaSeEstaSelecionado("elementosForm:comidaFavorita:2"));
    }

    @Test
    public void deveIntegarirComCombo(){
        dsl.selecionarCombo("elementosForm:escolaridade","2o grau completo");
        Assert.assertEquals("2o grau completo", dsl.obterValorCombo("elementosForm:escolaridade"));
    }

    @Test
    public void deveVerificarValoresCombo(){
        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
        Select combo = new Select(element);
        List<WebElement> options = combo.getOptions();
        Assert.assertEquals(8, options.size());

        boolean encontrou = false;
        for(WebElement option: options) {
            if(option.getText().equals("Mestrado")){
                encontrou = true;
                break;
            }
        }
        Assert.assertTrue(encontrou);
    }

    @Test
    public void deveVerificarValoresComboMultiplo(){
        dsl.selecionarCombo("elementosForm:esportes","Natacao");
        dsl.selecionarCombo("elementosForm:esportes","Corrida");
        dsl.selecionarCombo("elementosForm:esportes","O que eh esporte?");

        WebElement element = driver.findElement(By.id("elementosForm:esportes"));
        Select combo = new Select(element);
        combo.selectByVisibleText("Natacao");
        combo.selectByVisibleText("Corrida");
        combo.selectByVisibleText("O que eh esporte?");

        List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
        Assert.assertEquals(3, allSelectedOptions.size());

        combo.deselectByVisibleText("Corrida");
        allSelectedOptions = combo.getAllSelectedOptions();
        Assert.assertEquals(2, allSelectedOptions.size());
    }

    @Test
    public void deveinteragirComBotoes(){
        dsl.clicarBotao("buttonSimple");
        WebElement botao = driver.findElement(By.id("buttonSimple"));
        Assert.assertEquals("Obrigado!", botao.findElement(By.id("resultado")));
    }

    @Test
    public void deveinteragirComLinks(){
        dsl.clicarLink("Voltar");
        Assert.assertEquals("Voltou!",dsl.obeterTexto("resultado"));
    }

    @Test
    public void deveBuscarTextosNaPagina(){
//		Assert.assertTrue(driver.findElement(By.tagName("body"))
//				.getText().contains("Campo de Treinamento"));
        Assert.assertEquals("Campo de Treinamento",dsl.obeterTexto(By.tagName("h3")));

        Assert.assertEquals("Cuidado onde clica, muitas armadilhas...",dsl.obeterTexto(By.className("facilAchar")));
    }

}


