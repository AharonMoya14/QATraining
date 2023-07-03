/*
 All Rights Reserved
 This software is property information of AKUREY S.A.
 Contact us at contact@akurey.com
 @summary The code checks an user has been added correctly, and if some input is missing, the div has to change border color
*/
package com.example;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.github.javafaker.Faker;



public class WebTablesTest {
    private static WebDriver driver;
    private static void TestCase1(){
        driver.findElement(By.cssSelector("#addNewRecordButton")).click();
        Faker faker = new Faker();
        String randomFirstName = faker.name().firstName();
        String randomLastName = faker.name().lastName();
        String randomEmail = faker.internet().emailAddress();
        int randomAge = faker.number().numberBetween(18, 100);
        int randomSalary = faker.number().numberBetween(1000, 300000);
        String randomDepartment = faker.commerce().department();

        WebElement firstNameInput = driver.findElement(By.cssSelector("#firstName")); 
        WebElement lastNameInput = driver.findElement(By.cssSelector("#lastName"));
        WebElement emailInput = driver.findElement(By.cssSelector("#userEmail")); 
        WebElement ageInput = driver.findElement(By.cssSelector("#age")); 
        WebElement salaryInput = driver.findElement(By.cssSelector("#salary"));
        WebElement departmentInput = driver.findElement(By.cssSelector("#department")); 

        firstNameInput.sendKeys(randomFirstName);
        lastNameInput.sendKeys(randomLastName);
        emailInput.sendKeys(randomEmail);
        ageInput.sendKeys(String.valueOf(randomAge));
        salaryInput.sendKeys(String.valueOf(randomSalary));
        departmentInput.sendKeys(randomDepartment);

        driver.findElement(By.cssSelector("#submit")).click();
        List<WebElement> rowElements = driver.findElements(By.cssSelector(".rt-tbody .rt-tr"));
        for (WebElement rowElement : rowElements) {
            if (rowElement.getText().contains(randomEmail)) {
                scrollToElement(driver, rowElement);
                List<WebElement> cells = rowElement.findElements(By.cssSelector(".rt-td"));
                for (WebElement cell : cells) {
                    if (cell.getText().contains(randomFirstName)) {
                        scrollToElement(driver, cell);
                        Assertions.assertEquals(true, cell.isDisplayed(), "First Name is not found");
                    }
                    if (cell.getText().contains(randomLastName)) {
                        scrollToElement(driver, cell);
                        Assertions.assertEquals(true, cell.isDisplayed(), "Last Name is not found");
                    }
                    if (cell.getText().contains(randomEmail)) {
                        scrollToElement(driver, cell);
                        Assertions.assertEquals(true, cell.isDisplayed(), "Email is not found");
                    }
                    if (cell.getText().contains(Integer.toString(randomAge))) {
                        scrollToElement(driver, cell);
                        Assertions.assertEquals(true, cell.isDisplayed(), "Age is not found");
                    }
                    if (cell.getText().contains(Integer.toString(randomSalary))) {
                        scrollToElement(driver, cell);
                        Assertions.assertEquals(true, cell.isDisplayed(), "Salary is not found");
                    }
                    if (cell.getText().contains(randomDepartment)) {
                        scrollToElement(driver, cell);
                        Assertions.assertEquals(true, cell.isDisplayed(), "Department is not found");
                    }
                }
                }
            }
        }
        
    private static void TestCase2(){
        driver.findElement(By.cssSelector("#addNewRecordButton")).click();
        Faker faker = new Faker();
        String randomFirstName = faker.name().firstName();
        String randomLastName = faker.name().lastName();
        int randomAge = faker.number().numberBetween(18, 100);
        int randomSalary = faker.number().numberBetween(1000, 300000);
        String randomDepartment = faker.commerce().department();

        WebElement firstNameInput = driver.findElement(By.cssSelector("#firstName")); 
        WebElement lastNameInput = driver.findElement(By.cssSelector("#lastName"));
        WebElement ageInput = driver.findElement(By.cssSelector("#age")); 
        WebElement salaryInput = driver.findElement(By.cssSelector("#salary"));
        WebElement departmentInput = driver.findElement(By.cssSelector("#department")); 

        firstNameInput.sendKeys(randomFirstName);
        lastNameInput.sendKeys(randomLastName);
        ageInput.sendKeys(String.valueOf(randomAge));
        salaryInput.sendKeys(String.valueOf(randomSalary));
        departmentInput.sendKeys(randomDepartment);

        driver.findElement(By.cssSelector("#submit")).click();
        WebElement userEmailElement = driver.findElement(By.id("userEmail"));
        String borderColor = userEmailElement.getCssValue("border-color");
        String[] rgbComponents = borderColor.replace("rgb(", "").replace(")", "").split(", ");
        int redComponent = Integer.parseInt(rgbComponents[0].trim());
        Assertions.assertTrue(redComponent > 200, "There is not an error message");
    }
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://demoqa.com");
        List<WebElement> cards = driver.findElements(By.cssSelector(".card.mt-4.top-card"));
        for (WebElement card : cards) {
            if (card.getText().contains("Elements")) {
                scrollToElement(driver, card);
                card.click();
                break;
            }
        }
        List<WebElement> elements = driver.findElements(By.cssSelector(".btn.btn-light"));
        for (WebElement element : elements) {
            if (element.getText().contains("Web Tables")) {
                scrollToElement(driver, element);
                element.click();
                break;
            }
        }
        TestCase1();
        TestCase2();
    }

    private static void scrollToElement(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
}
