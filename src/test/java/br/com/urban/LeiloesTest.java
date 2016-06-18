package br.com.urban;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LeiloesTest {

	private WebDriver driver;

	@BeforeClass
	public static void antesDaClasse() {
		System.setProperty("webdriver.chrome.driver", "resources/webdrivers/chromedriver.exe");
	}

	@Before
	public void antesDoTeste() {
		driver = new ChromeDriver();
	}

	@After
	public void depoisDoTeste() {
		driver.close();
	}

	@Test
	public void deveCadastrarNovoUsuario() {
		driver.get("http://localhost:8080/usuarios/new");

		// encontrando ambos elementos na pagina
		WebElement nome = driver.findElement(By.name("usuario.nome"));
		WebElement email = driver.findElement(By.name("usuario.email"));

		// digitando em cada um deles
		nome.sendKeys("Ronaldo Luiz de Albuquerque");
		email.sendKeys("ronaldo2009@terra.com.br");

		WebElement botaoSalvar = driver.findElement(By.id("btnSalvar"));
		botaoSalvar.click();

		boolean achouNome = driver.getPageSource().contains("Ronaldo Luiz de Albuquerque");
		boolean achouEmail = driver.getPageSource().contains("ronaldo2009@terra.com.br");

		assertTrue(achouNome);
		assertTrue(achouEmail);
	}

}
