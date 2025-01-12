package testrunner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.LoginNewPassword;
import setup.AddDataSet;
import setup.Setup;

import java.time.Duration;

public class Logintestrunner extends Setup {
  @Test(dataProvider = "FileWithCSVData", dataProviderClass = AddDataSet.class)
  public void loginNewpassword(String itemName, String amount, String remarks) throws InterruptedException {
    // Perform Login
    LoginNewPassword loginnewpassword = new LoginNewPassword(driver);
    loginnewpassword.dologin("anikkumardas966@gmail.com", "4321");

    // Verify header text
    String headerActual = driver.findElement(By.tagName("h2")).getText();
    String headerExpected = "User Daily Costs";
    Assert.assertTrue(headerActual.contains(headerExpected), "Header does not match!");
    System.out.println("Header verified: " + headerActual);

    // Add cost
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    WebElement addCostButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("add-cost-button")));
    addCostButton.click();

    // Fill form using CSV data
    WebElement inputField = driver.findElement(By.id("itemName"));
    inputField.sendKeys(itemName);

    WebElement amountField = driver.findElement(By.id("amount"));
    amountField.sendKeys(amount);

    WebElement remarksField = driver.findElement(By.id("remarks"));
    remarksField.sendKeys(remarks);

    // Submit form
    WebElement submitButton = driver.findElement(By.className("submit-button"));
    submitButton.click();

    // Handle alert
    wait.until(ExpectedConditions.alertIsPresent());
    driver.switchTo().alert().accept();

    System.out.println("Form submitted with data: " + itemName + ", " + amount + ", " + remarks);
  }
}
