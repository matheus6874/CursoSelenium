import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Dsl {

    private WebDriver driver;

    public Dsl(WebDriver driver) {
        this.driver = driver;
    }

    public void escreverNoCampo(String idCampo, String texto){
        driver.findElement(By.id(idCampo)).sendKeys(texto);
    }

    public String obsterValorCampo(String idCampo){
        return driver.findElement(By.id(idCampo)).getAttribute("value");
    }

    public void clicar(String idCampo){
        driver.findElement(By.id(idCampo)).click();
    }

    public Boolean verificaSeEstaSelecionado(String idCampo){
        return driver.findElement(By.id("idCampo")).isSelected();
    }

    public void selecionarCombo(String idCampo,String texto){
        WebElement element = driver.findElement(By.id(idCampo));
        Select combo = new Select(element);
//		combo.selectByIndex(2);
//		combo.selectByValue("superior");
        combo.selectByVisibleText(texto);
    }


    public String obterValorCombo(String idCampo){
        WebElement element = driver.findElement(By.id(idCampo));
        Select combo = new Select(element);
        return combo.getFirstSelectedOption().getText();
    }

    public void clicarBotao(String idCampo){
        WebElement botao = driver.findElement(By.id(idCampo));
        botao.click();
    }

    public String obterValorBotao(String idCampo){
        WebElement botao = driver.findElement(By.id(idCampo));
        return  botao.getAttribute("value");
    }

    public void clicarLink(String linkText){
        driver.findElement(By.linkText(linkText)).click();
    }

    public String obeterTexto(String idCampo){
        return driver.findElement(By.id(idCampo)).getText();
    }

    public String obeterTexto(By by){
        return driver.findElement((by)).getText();
    }


}
