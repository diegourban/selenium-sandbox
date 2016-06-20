package br.com.urban;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LeiloesSystemTest {

	private WebDriver driver;
	private LeiloesPage leiloes;

	@BeforeClass
	public static void antesDaClasse() {
		System.setProperty("webdriver.chrome.driver", "resources/webdrivers/chromedriver.exe");
	}

	@Before
	public void antesDoTeste() {
		driver = new ChromeDriver();
		driver.get("http://localhost:8080/apenas-teste/limpa");
		
		leiloes = new LeiloesPage(driver);
		
		UsuariosPage usuarios = new UsuariosPage(driver);
        usuarios.visita();
        usuarios.novo().cadastra("Paulo Henrique", "paulo@henrique.com");
	}

	@After
	public void depoisDoTeste() {
		driver.close();
	}
	
	@Test
    public void deveCadastrarUmLeilao() {
        leiloes.visita();
        NovoLeilaoPage novoLeilao = leiloes.novo();
        novoLeilao.cadastra("Geladeira", 123, "Paulo Henrique", true);

        assertTrue(leiloes.existe("Geladeira", 123, "Paulo Henrique", true));
    }
	
	@Test
	public void naoDeveCadatrarLeilaoSemNome() {
		leiloes.visita();
		
		NovoLeilaoPage novoLeilao = leiloes.novo();
		novoLeilao.cadastra("", 123, "Paulo Henrique", true);
		
		assertTrue(novoLeilao.validacaoDeNomeObrigatorio());
	}
	
	@Test
	public void naoDeveCadatrarLeilaoSemValorInicial() {
		leiloes.visita();
		
		NovoLeilaoPage novoLeilao = leiloes.novo();
		novoLeilao.cadastra("Ps3", 0, "Paulo Henrique", true);
		
		assertTrue(novoLeilao.validacaoValorInicialMairQueZeroObrigatorio());
	}

}
