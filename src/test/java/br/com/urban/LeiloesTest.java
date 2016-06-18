package br.com.urban;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LeiloesTest {

	private WebDriver driver;
	private UsuariosPage usuarios;

	@BeforeClass
	public static void antesDaClasse() {
		System.setProperty("webdriver.chrome.driver", "resources/webdrivers/chromedriver.exe");
	}

	@Before
	public void antesDoTeste() {
		driver = new ChromeDriver();
		usuarios = new UsuariosPage(driver);
		usuarios.visita();
	}

	@After
	public void depoisDoTeste() {
		driver.close();
	}

	@Test
	public void deveCadastrarNovoUsuario() {
		usuarios.novo().cadastra("Diego L. Urban", "email@dominio.com.br");

		assertTrue(usuarios.existeNaListagem("Diego L. Urban", "email@dominio.com.br"));
	}

	@Test
	public void naoDeveAdicionarUmUsuarioSemNome() {
		NovoUsuarioPage form = usuarios.novo();

		form.cadastra("", "ronaldo2009@terra.com.br");

		assertTrue(form.validacaoDeNomeObrigatorio());
	}

}
