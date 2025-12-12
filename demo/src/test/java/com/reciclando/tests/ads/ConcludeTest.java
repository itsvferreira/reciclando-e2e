package com.reciclando.tests.ads;

import com.reciclando.BaseTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.Duration;

public class ConcludeTest extends BaseTest {

   @BeforeEach
    public void setUpTest() {
        driver.get("http://localhost:5173");
        driver.manage().window().maximize();((JavascriptExecutor) driver).executeScript("window.localStorage.clear();");
        driver.get("http://localhost:5173/user-profile");
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
public void testConcludeFirstAdWithRecyclerCode() {
    Login();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    driver.get("http://localhost:5173/user-profile");
    WebElement concludeButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button._btnConclude_19ud3_263")));
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", concludeButton);
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", concludeButton);
    WebElement codeInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("recyclerCode")));codeInput.sendKeys("A9K3Q");
    WebElement confirmBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button._btnConfirm_fw04h_157")));
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", confirmBtn);
    WebElement closeBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button._btnClose_1ojtm_131")));
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", closeBtn);
}
}