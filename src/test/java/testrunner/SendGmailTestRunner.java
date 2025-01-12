package testrunner;


import com.github.javafaker.Faker;
import org.apache.commons.configuration.ConfigurationException;
import org.openqa.selenium.By;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.ResetPage;
import setup.Setup;

import java.io.IOException;


public class SendGmailTestRunner extends Setup {
      @Test(priority = 1, description = "Reset password using wrong Gmail")
    public void ResetEmail() throws InterruptedException {
        ResetPage resetpage=new ResetPage(driver);
          Thread.sleep(1000);

        driver.findElement(By.partialLinkText("Reset it here")).click();
          Faker faker=new Faker();
        resetpage.sendEmail(faker.name().firstName().toLowerCase()+"@gmail.com");
        driver.findElement(By.cssSelector("[type=submit]")).click();

        String headerMessage = driver.findElement(By.tagName("p")).getText();
        String ActualMessage = "Your email is not registered";
        Assert.assertTrue(headerMessage.contains(ActualMessage));
          driver.navigate().refresh();
          Thread.sleep(2000);




    }

    @Test(priority = 2, description = "Reset password using wrong and half Gmail")
    public void ResetEmail2() throws InterruptedException {


        driver.findElement(By.xpath("//input[@id=':r0:']")).sendKeys("anik@");
        driver.findElement(By.cssSelector("[type=submit]")).click();
        String ExpectedMessage = driver.findElement(By.xpath("//input[@id=':r0:']")).getAttribute("validationMessage");
        String ActualMessage = "Please enter a part following '@'. 'anik@' is incomplete";
        Assert.assertTrue(ExpectedMessage.contains(ActualMessage));
        driver.navigate().refresh();
        Thread.sleep(2000);



    }

    @Test(priority = 3, description = "Reset password using right Gmail")
    public void RecoverPassword() throws ConfigurationException, IOException, InterruptedException {

        ResetPage resetpage = new ResetPage(driver);



        resetpage.sendEmail("anikkumardas966@gmail.com");

        driver.findElement(By.cssSelector("[type=submit]")).click();

        Thread.sleep(10000);


        String headerMessage = driver.findElement(By.tagName("p")).getText();
        String expectedMessage = "Password reset link sent to your email";
        Assert.assertTrue(headerMessage.contains(expectedMessage));




    }






}
