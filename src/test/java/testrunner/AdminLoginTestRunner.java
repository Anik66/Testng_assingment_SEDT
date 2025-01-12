package testrunner;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.LoginNewPassword;
import setup.Setup;

import java.util.List;

public class AdminLoginTestRunner extends Setup {

    @Test
    public void adminLogin() throws InterruptedException {
        LoginNewPassword loginnewpassword = new LoginNewPassword(driver);
        loginnewpassword.dologin(System.getProperty("email"), System.getProperty("password"));
        Thread.sleep(2000);
        WebElement searchBox = driver.findElement(By.className("search-box"));
        searchBox.sendKeys("anikkumardas966+9603@gmail.com");

        List<WebElement> tableCells = driver.findElements(By.tagName("td"));
        String cellText = tableCells.get(2).getText();
        Assert.assertTrue(cellText.contains("anikkumardas966+9603@gmail.com"));
        Thread.sleep(2000);
        WebElement button = driver.findElement(By.cssSelector("button[type='button'][aria-label='account of current user']"));
        button.click();

        List<WebElement> menuItems = driver.findElements(By.cssSelector("[role='menuitem']"));
        menuItems.get(1).click();










    }

}
