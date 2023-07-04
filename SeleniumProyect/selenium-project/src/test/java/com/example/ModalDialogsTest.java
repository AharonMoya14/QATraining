/*
 All Rights Reserved
 This software is property information of AKUREY S.A.
 Contact us at contact@akurey.com
 @summary The code checks if Small and Large modals appears when user press the correct button
*/
package com.example;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;



public class ModalDialogsTest {
    private static WebDriver driver;
    private static void SmallModal(){
        driver.findElement(By.cssSelector("#showSmallModal")).click();
        List<WebElement> modals = driver.findElements(By.cssSelector(".modal-content"));
        for (WebElement modal : modals) {
            if (modal.getText().contains("Small Modal")) {
                Assertions.assertEquals(true, modal.isDisplayed(), "The Small Modal is not shown");
                driver.findElement(By.cssSelector("#closeSmallModal")).click();
                break;
            }
        }
    }
    private static void LargeModal(){
        driver.findElement(By.cssSelector("#showLargeModal")).click();
        List<WebElement> modals = driver.findElements(By.cssSelector(".modal-content"));
        for (WebElement modal : modals) {
            if (modal.getText().contains("Large Modal")) {
                Assertions.assertEquals(true, modal.isDisplayed(), "The Large Modal is not shown");
                driver.findElement(By.cssSelector("#closeLargeModal")).click();
                break;
            }
        }
        driver.findElement(By.cssSelector("#closeLargeModal")).click();
    }
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://demoqa.com");
        List<WebElement> cards = driver.findElements(By.cssSelector(".card.mt-4.top-card"));
        for (WebElement card : cards) {
            if (card.getText().contains("Alerts, Frame & Windows")) {
                scrollToElement(driver, card);
                card.click();
                break;
            }
        }
        List<WebElement> elements = driver.findElements(By.cssSelector(".btn.btn-light"));
        for (WebElement element : elements) {
            if (element.getText().contains("Modal Dialogs")) {
                scrollToElement(driver, element);
                element.click();
                break;
            }
        }
        SmallModal();
        
        LargeModal();

    
    }

  
  
    private static void scrollToElement(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
}
