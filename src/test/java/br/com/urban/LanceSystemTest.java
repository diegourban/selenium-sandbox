package br.com.urban;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LanceSystemTest {

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

		UsuariosPage usuarios = new UsuariosPage(driver);
		usuarios.visita();
		usuarios.novo().cadastra("Paulo Henrique", "paulo@henrique.com");
		usuarios.novo().cadastra("José Alberto", "jose@alberto.com");

		leiloes = new LeiloesPage(driver);
		leiloes.visita();
		leiloes.novo().cadastra("Geladeira", 100, "Paulo Henrique", false);
	}

	@After
	public void depoisDoTeste() {
		driver.close();
	}

	@Test
	public void deveFazerUmLance() {
		DetalhesDoLeilaoPage lances = leiloes.detalhes(1);

		lances.lance("José Alberto", 150);

		assertTrue(lances.existeLance("José Alberto", 150));
	}

}
