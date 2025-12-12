package com.reciclando.tests.ads;
import com.reciclando.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.Duration;


import com.reciclando.JsUtils;


public class AdsTests extends BaseTest {
    @BeforeEach
    public void setUpTest() {
        driver.get("http://localhost:5173");
        driver.manage().window().maximize();
        driver.manage().window().maximize();
        ((JavascriptExecutor) driver).executeScript("window.localStorage.clear();");
        driver.navigate().refresh();
}
    
    private void Login() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.cssSelector("button.btn-login")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("email")));
        driver.findElement(By.name("email")).sendKeys("luiza.sanchez@email.com");
        driver.findElement(By.name("password")).sendKeys("123456");
        driver.findElement(By.cssSelector("button._submitButton_17iqy_75")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(), 'Criar Anúncio')]")));
        assertTrue(driver.getPageSource().contains("Criar Anúncio"));
    }

    @Test
    public void testCreateAd() {
       Login();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.cssSelector("button._btnCreateAd_celu5_175")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("title")));
        String titulo = "Doação de Garrafas PET";
        driver.findElement(By.id("title")).sendKeys(titulo);
        driver.findElement(By.id("description")).sendKeys("Estou doando garrafas PET limpas e em bom estado.");
        String caminhoArquivo = "C:\\Users\\veron\\OneDrive\\Documentos\\projeto\\testee2e\\reciclando-e2e\\demo\\src\\test\\java\\com\\reciclando\\Imagem\\images (1).jpg";
        driver.findElement(By.id("image")).sendKeys(caminhoArquivo);
        JsUtils jsUtils = new JsUtils(driver);
        WebElement labelPlastico = driver.findElement(By.cssSelector("label[for='PLASTIC']"));
        jsUtils.scrollToElement(labelPlastico);
        jsUtils.jsClick(labelPlastico);
        WebElement submitButton = driver.findElement(By.cssSelector("button.btn.btn-success"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitButton);
        wait.until(ExpectedConditions.urlContains("/user-profile"));
        assertTrue(driver.getCurrentUrl().contains("/user-profile"));
    }

    
    
 }
