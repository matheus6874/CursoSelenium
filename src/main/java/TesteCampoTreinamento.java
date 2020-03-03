import org.junit.Assert;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class TesteCampoTreinamento {

    @Test
    public void testeTextField(){
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Matheus");
        Assert.assertEquals("Matheus",driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Cristino");
        Assert.assertEquals("Cristino",driver.findElement(By.id("elementosForm:sobrenome")).getAttribute("value"));
        driver.quit();
    }

    @Test
    public void deveInteragirComTextArea(){
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Sem sugestão");
        Assert.assertEquals("Sem sugestão",driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));
        driver.quit();
    }

    @Test
    public void deveInteragirComRadioButton(){
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        driver.findElement(By.id("elementosForm:sexo:0")).click();
        Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
        driver.quit();
    }

    @Test
    public void deveInteragirComCheckBox(){
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
        Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:0")).isSelected());
        driver.quit();
    }

    @Test
    public void deveInteragirComComboBox(){
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        WebElement elemento =  driver.findElement(By.id("elementosForm:escolaridade"));
        Select select = new Select(elemento);
        List<WebElement> option = select.getOptions();
        Assert.assertEquals(8,option.size());

        boolean encontrou = false;
        for (WebElement options:option){
            if (options.getText().equals("Mestrado"));
            encontrou = true;
            break;
        }
        Assert.assertTrue(encontrou);
        driver.quit();
    }

    @Test
    public void verificarValoresDisponiveisNoCombo(){
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        WebElement elemento =  driver.findElement(By.id("elementosForm:escolaridade"));
        Select select = new Select(elemento);
        select.selectByVisibleText("2o grau incompleto");
        Assert.assertEquals("2o grau incompleto",select.getFirstSelectedOption().getText());
        driver.quit();
    }

    @Test
    public void verificarValoresComboMultiplo() {
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        WebElement elemento = driver.findElement(By.id("elementosForm:esportes"));
        Select select = new Select(elemento);
        select.selectByVisibleText("Natacao");
        select.selectByVisibleText("Corrida");
        select.selectByVisibleText("O que eh esporte?");
        List<WebElement> selecionados = select.getAllSelectedOptions();
        Assert.assertEquals(3,selecionados.size());
        driver.quit();
    }

    @Test
    public void deveInteragirComBotoes() {
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        driver.findElement(By.id("buttonSimple")).click();
        Assert.assertEquals("Obrigado!",driver.findElement(By.id("buttonSimple")).getAttribute("value"));
        driver.quit();
    }

    @Test
    public void deveBuscarTextoNaTela() {
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        Assert.assertTrue(driver.findElement(By.tagName("h3")).getText().contains("Campo de Treinamento"));
        driver.quit();
    }

    @Test
    public void deveInteragirComLink() {
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        driver.findElement(By.linkText("Voltar")).click();
        Assert.assertEquals("Voltou!",driver.findElement(By.id("resultado")).getText());
        driver.quit();
    }
}
