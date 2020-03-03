import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteAlert {

    @Test
    public void deveInteratirComAlert() {
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        driver.findElement(By.id("alert")).click();
        Alert alert =  driver.switchTo().alert();
        String texto = alert.getText();

        Assert.assertEquals("Alert Simples",alert.getText());
        alert.accept();
        driver.findElement(By.id("elementosForm:nome")).sendKeys(texto);
        driver.quit();
    }

    @Test
    public void deveInteratirComAlertConfirm() {
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        driver.findElement(By.id("confirm")).click();
        Alert alert =  driver.switchTo().alert();
        String texto = alert.getText();
        Assert.assertEquals("Confirm Simples",alert.getText());
        alert.accept();
        Assert.assertEquals("Confirmado",alert.getText());
        alert.accept();
        driver.quit();
    }

    @Test
    public void deveInteratirComAlertNegar() {
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        driver.findElement(By.id("confirm")).click();
        Alert alert =  driver.switchTo().alert();
        String texto = alert.getText();
        Assert.assertEquals("Confirm Simples",alert.getText());
        alert.dismiss();
        Assert.assertEquals("Negado",alert.getText());
        alert.accept();
        driver.quit();
    }

    @Test
    public void deveInteratirComOPrompt() {
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        driver.findElement(By.id("prompt")).click();
        Alert alert =  driver.switchTo().alert();
        String texto = alert.getText();
        Assert.assertEquals("Digite um numero",alert.getText());
        alert.sendKeys("1");
        alert.accept();
        Assert.assertEquals("Era 1?",alert.getText());
        alert.accept();
        Assert.assertEquals(":D",alert.getText());
        alert.accept();
        driver.quit();
    }

    @Test
    public void realizarCadastro() {
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

        driver.findElement(By.id("elementosForm:nome")).sendKeys("Matheus");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Cristino");
        driver.findElement(By.id("elementosForm:sexo:0")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
        Select select = new Select(driver.findElement(By.id("elementosForm:escolaridade")));
        select.selectByIndex(4);
        Select selectEsporte = new Select(driver.findElement(By.id("elementosForm:esportes")));
        selectEsporte.selectByVisibleText("Futebol");
        driver.findElement(By.id("elementosForm:cadastrar")).click();

        Assert.assertEquals("Cadastrado!",driver.findElement(By.id("resultado")).findElement(By.tagName("span")).getText());
        Assert.assertEquals("Matheus",driver.findElement(By.id("descNome")).findElement(By.tagName("span")).getText());
        Assert.assertEquals("Cristino",driver.findElement(By.id("descSobrenome")).findElement(By.tagName("span")).getText());
        Assert.assertEquals("Masculino",driver.findElement(By.id("descSexo")).findElement(By.tagName("span")).getText());
        Assert.assertEquals("Pizza",driver.findElement(By.id("descComida")).findElement(By.tagName("span")).getText());
        Assert.assertEquals("superior",driver.findElement(By.id("descEscolaridade")).findElement(By.tagName("span")).getText());
        Assert.assertEquals("Futebol",driver.findElement(By.id("descEsportes")).findElement(By.tagName("span")).getText());
        driver.quit();
    }
}
