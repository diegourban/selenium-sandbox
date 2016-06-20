package br.com.urban;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class NovoLeilaoPage {

	private WebDriver driver;

	public NovoLeilaoPage(WebDriver driver) {
		this.driver = driver;
	}

	public void cadastra(String nome, double valor, String usuario, boolean usado) {
		// encontrando ambos elementos na pagina
		WebElement inputNome = driver.findElement(By.name("leilao.nome"));
		WebElement inputValor = driver.findElement(By.name("leilao.valorInicial"));

		// digitando em cada um deles
		inputNome.sendKeys(nome);
		inputValor.sendKeys(String.valueOf(valor));

		WebElement combo = driver.findElement(By.name("leilao.usuario.id"));
		Select cbUsuario = new Select(combo);
		cbUsuario.selectByVisibleText(usuario);

		if (usado) {
			WebElement ckUsado = driver.findElement(By.name("leilao.usado"));
			ckUsado.click();
		}

		inputNome.submit();
	}
	
	public boolean validacaoDeNomeObrigatorio() {
		return driver.getPageSource().contains("Nome obrigatorio!");
	}
	
	public boolean validacaoValorInicialMairQueZeroObrigatorio() {
		return driver.getPageSource().contains("Valor inicial deve ser maior que zero!");
	}

}
