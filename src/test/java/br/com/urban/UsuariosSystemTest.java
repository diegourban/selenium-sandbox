package br.com.urban;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class UsuariosSystemTest {

	private WebDriver driver;
	private UsuariosPage usuarios;

	@BeforeClass
	public static void antesDaClasse() {
		System.setProperty("webdriver.chrome.driver", "resources/webdrivers/chromedriver.exe");
	}

	@Before
	public void antesDoTeste() {
		driver = new ChromeDriver();
		driver.get("http://localhost:8080/apenas-teste/limpa");
		
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

	@Test
	public void deveRemoverUsuarioCadastrado() {
		final String nome = "Para Remover";
		final String email = "para@remover.com.br";

		usuarios.novo().cadastra(nome, email);

		assertTrue(usuarios.existeNaListagem(nome, email));

		usuarios.removeUsuarioNaPosicao(1);

		assertFalse(usuarios.existeNaListagem(nome, email));
	}

	@Test
	public void deveAlterarUmUsuario() {
		usuarios.novo().cadastra("Ronaldo Luiz de Albuquerque", "ronaldo2009@terra.com.br");
		usuarios.altera(1).para("Jos� da Silva", "jose@silva.com");

		assertFalse(usuarios.existeNaListagem("Ronaldo Luiz de Albuquerque", "ronaldo2009@terra.com.br"));
		assertTrue(usuarios.existeNaListagem("Jos� da Silva", "jose@silva.com"));
	}

}
