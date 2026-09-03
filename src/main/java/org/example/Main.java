package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // 1. Configura el driver de Chrome automáticamente
        WebDriverManager.chromedriver().setup();

        // 2. Inicializa la instancia del navegador
        WebDriver driver = new ChromeDriver();

        try {
            // Maximiza la ventana del navegador
            driver.manage().window().maximize();

            // 3. Navega a una URL
            driver.get("http://localhost:3001");

            // 4. Espera explícita para asegurar que el elemento cargue en el DOM
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement emailBox = wait.until(
                    ExpectedConditions.elementToBeClickable(By.name("email"))
            );

            WebElement passBox = wait.until(
                    ExpectedConditions.elementToBeClickable(By.name("password"))
            );

            // 5. Interacción con el Frontend (Escribir y Presionar Enter)
            emailBox.sendKeys("ivan.luna@email.com", Keys.TAB);
            passBox.sendKeys("123456", Keys.ENTER);

            // 6. Esperar el resultado

            //Opcion si el div se renderiza
            // Localizador XPath que busca la clase y el texto exacto
            By alertaConTexto = By.xpath("//div[contains(@class, 'alert-danger') and text()='Credenciales de Mock inválidas']");

            // Espera hasta que el elemento sea completamente visible en la pantalla
            WebElement mensaje = wait.until(ExpectedConditions.visibilityOfElementLocated(alertaConTexto));

            /*
            //Opcion si el div se hace visible
            // Localizador por la clase de la alerta de error
            By alertaError = By.className("alert-danger");

            // Espera explícita hasta que el texto exacto aparezca en ese div
            wait.until(ExpectedConditions.textToBePresentInElementLocated(alertaError, "Credenciales de Mock inválidas"));
            */

            // Imprime el título actual en consola
            System.out.println("Título de la página: " + mensaje.getText());
            Thread.sleep(4000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 7. Cierra el navegador y termina el proceso
            driver.quit();
        }
    }
}
