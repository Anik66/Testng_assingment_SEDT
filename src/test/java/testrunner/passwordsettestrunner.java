package testrunner;

import org.apache.commons.configuration.ConfigurationException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import setup.ReadGmailPassword;
import setup.Setup;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class passwordsettestrunner extends Setup {
    @Test
   public void passwordfixed() throws ConfigurationException, IOException, InterruptedException {

        Thread.sleep(5000);
       ReadGmailPassword readgmailpassword = new ReadGmailPassword();
       String mailBody = readgmailpassword.readMail();

       if (mailBody == null) {
           Assert.fail("Mail body could not be fetched. Check the Gmail API setup.");
       }

       String resetLink = extractResetLink(mailBody);
       if (resetLink == null) {
           Assert.fail("Reset link could not be extracted from the mail body.");
       }


       System.out.println("Reset Link: " + resetLink);
       driver.get(resetLink);


       List<WebElement> passFields = driver.findElements(By.cssSelector("[type=password]"));
       passFields.get(0).sendKeys("4321");
       passFields.get(1).sendKeys("4321");
       driver.findElement(By.cssSelector("[type=submit]")).click();
   }

    private String extractResetLink(String mailBody) {
        String keyword = "reset your password:";
        if (mailBody.contains(keyword)) {
            int startIndex = mailBody.indexOf(keyword) + keyword.length();
            String link = mailBody.substring(startIndex).trim();
            int endIndex = link.indexOf(" ");
            if (endIndex != -1) {
                link = link.substring(0, endIndex).trim();
            }
            return link;
        }
        return null;
    }

}
