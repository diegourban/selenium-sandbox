package br.com.urban;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Teste {
	
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
	public void deveAbrirGoogle() {
		driver.get("http://www.google.com.br");
		
		boolean isGoogle = "Google".equals(driver.getTitle());
		
		assertTrue(isGoogle);
	}

}
