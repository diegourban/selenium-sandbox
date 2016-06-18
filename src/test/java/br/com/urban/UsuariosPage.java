package br.com.urban;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UsuariosPage {
	
	private WebDriver driver;
	
	public UsuariosPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void visita() {
		driver.get("http://localhost:8080/usuarios");
	}
	
	public NovoUsuarioPage novo() {
		driver.findElement(By.linkText("Novo Usu�rio")).click();
		return new NovoUsuarioPage(driver);
	}

	public boolean existeNaListagem(String nome, String email) {
		boolean achouNome = driver.getPageSource().contains(nome);
		boolean achouEmail = driver.getPageSource().contains(email);
		
		return achouNome && achouEmail;
	}
	
	public void removeUsuarioNaPosicao(int posicao) {
		driver.findElements(By.tagName("button")).get(posicao-1).click();
		
		// pega o alert que est� aberto
		Alert alert = driver.switchTo().alert();
		// confirma
		alert.accept();
	}

}
