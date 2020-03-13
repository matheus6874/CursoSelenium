import core.DSL;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import static core.DriverFactory.getDriver;
import static core.DriverFactory.killDriver;

@RunWith(Parameterized.class)
public class TesteRegrasCadastro {

    private DSL dsl;
    private CampoTreinamentoPage campoTreinamentoPage;

    @Parameterized.Parameter(value = 0)
    public String nome;
    @Parameterized.Parameter(value = 1)
    public String sobrenome;
    @Parameterized.Parameter(value = 2)
    public String sexo;
    @Parameterized.Parameter(value = 3)
    public List<String> comidas;
    @Parameterized.Parameter(value = 4)
    public String[] esportes;
    @Parameterized.Parameter(value = 5)
    public String msg;

    @Before
    public void inicializa(){
        getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        dsl = new DSL();
        campoTreinamentoPage = new CampoTreinamentoPage();
    }

    @After
    public void finaliza(){
        killDriver();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getCollection(){
        return Arrays.asList(new Object[][]{
                {"","","",Arrays.asList(),new String []{},"Nome eh obrigatorio"},
                {"Matheus","","",Arrays.asList(),new String []{},"Sobrenome eh obrigatorio"},
                {"Matheus","Cristino","",Arrays.asList(),new String []{},"Sexo eh obrigatorio"},
                {"Matheus","Cristino","Masculino",Arrays.asList("Carne","Vegetariano"),new String []{},"Tem certeza que voce eh vegetariano?"},
                {"Matheus","Cristino","Feminino",Arrays.asList("Carne"),new String []{"Karate","O que eh esporte?"},"Voce faz esporte ou nao?"}
        });
    }

    @Test
    public void deveValidarRegra(){
            campoTreinamentoPage.setNome(nome);
            campoTreinamentoPage.setSobrenome(sobrenome);
            if (sexo.equals("Masculino"))
                campoTreinamentoPage.setSexoMasculino();
            else if (sexo.equals("Feminino"))
                campoTreinamentoPage.setSexoFeminino();
            if (comidas.contains("Carne")) campoTreinamentoPage.setComidaCarne();
            if (comidas.contains("Frango")) campoTreinamentoPage.setComidaFrango();
            if (comidas.contains("Pizza")) campoTreinamentoPage.setComidaPizza();
            if (comidas.contains("Vegetariano")) campoTreinamentoPage.setComidaVegetariano();
            campoTreinamentoPage.setEsporte(esportes);
            campoTreinamentoPage.cadastrar();
            Assert.assertEquals(msg, dsl.alertaObterTextoEAceita());
    }
}
