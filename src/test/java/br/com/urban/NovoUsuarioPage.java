package br.com.urban;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NovoUsuarioPage {

	private WebDriver driver;
	
	public NovoUsuarioPage(WebDriver driver) {
		this.driver = driver;
	}

	public void cadastra(String nome, String email) {
		// encontrando ambos elementos na pagina
		WebElement inputNome = driver.findElement(By.name("usuario.nome"));
		WebElement inputEmail = driver.findElement(By.name("usuario.email"));

		// digitando em cada um deles
		inputNome.sendKeys(nome);
		inputEmail.sendKeys(email);
		
		clicaEmSalvar();
	}
	
	public boolean validacaoDeNomeObrigatorio() {
		return driver.getPageSource().contains("Nome obrigatorio!");
	}
	
	private void clicaEmSalvar() {
		WebElement botaoSalvar = driver.findElement(By.id("btnSalvar"));
		botaoSalvar.click();
	}
}
