package testrunner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.LoginNewPassword;
import page.ProfilePage;
import setup.Setup;

import java.util.List;

public class UpdatedGmailTestRunner extends Setup {
    @Test(priority = 1)
    public void updatedGmail() throws InterruptedException {
        LoginNewPassword loginnewpassword = new LoginNewPassword(driver);
        loginnewpassword.dologin("anikd143@gmail.com", "1234");
        WebElement button = driver.findElement(By.cssSelector("button[type='button'][aria-label='account of current user']"));
        button.click();

        List<WebElement> menuItems = driver.findElements(By.cssSelector("[role='menuitem']"));
        menuItems.get(0).click();
        ProfilePage profilepage = new ProfilePage(driver);

        profilepage.updateProfile("anikd143@gmail.com");
        Thread.sleep(2000);

        driver.switchTo().alert().accept();

        WebElement button1 = driver.findElement(By.cssSelector("button[type='button'][aria-label='account of current user']"));
        button1.click();

        List<WebElement> menuItems1 = driver.findElements(By.cssSelector("[role='menuitem']"));
        menuItems1.get(1).click();

    }
    @Test(priority = 2)
    public void LoginWithUpdatedGmail() throws InterruptedException {
        LoginNewPassword loginnewpassword = new LoginNewPassword(driver);
        loginnewpassword.dologin("anikd143@gmail.com", "1234");
        String headerActual = driver.findElement(By.tagName("h2")).getText();
        String headerExpected = "User Daily Costs";
        Assert.assertTrue(headerActual.contains(headerExpected), "Header does not match!");
        System.out.println("Header verified: " + headerActual);

        //logout
        WebElement button = driver.findElement(By.cssSelector("button[type='button'][aria-label='account of current user']"));
        button.click();

        List<WebElement> menuItems = driver.findElements(By.cssSelector("[role='menuitem']"));
        menuItems.get(1).click();

    }
    @Test(priority = 3)
    public void LoginWithPreviusGmail() throws InterruptedException {
        LoginNewPassword loginnewpassword = new LoginNewPassword(driver);
        loginnewpassword.dologin("anikd1439@gmail.com", "1234");
        String ActualMessage =  driver.findElement(By.tagName("p")).getText();
        String ExpectedMessage = "Invalid email or password";
        Assert.assertTrue(ActualMessage.contains(ExpectedMessage));
        driver.navigate().refresh();

    }


}
