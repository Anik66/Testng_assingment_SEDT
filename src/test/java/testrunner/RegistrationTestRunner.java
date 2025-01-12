package testrunner;

import Utils.Utils;
import org.apache.commons.configuration.ConfigurationException;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import setup.ReadGmail;
import com.github.javafaker.Faker;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.RegistrationPage;
import setup.Setup;
import setup.UserModel;

import java.io.IOException;

public class RegistrationTestRunner extends Setup {

    @Test(priority = 1, description = "user can register successfully")
    public void userRegister() throws InterruptedException, IOException, ConfigurationException, ParseException {

        RegistrationPage registrationPage = new RegistrationPage(driver);
        Faker faker = new Faker();
        driver.findElement(By.partialLinkText("Register")).click();
        String firstName =faker.name().firstName();
        String lastname =faker.name().lastName();
        String email = "anikkumardas966+" + Utils.generateRandomId(1000, 9999) + "@gmail.com";
        String password = "1234";
        String phoneNumber = "01760" + Utils.generateRandomId(1000000, 9999999);
        String address = faker.address().fullAddress();
        registrationPage.doRegistration(firstName,lastname, email, password, phoneNumber,address);
        Thread.sleep(3000);


        String successMessage = driver.findElement(By.className("Toastify__toast")).getText();
        Assert.assertTrue(successMessage.contains("registered successfully!"));


        UserModel userModel = new UserModel();
        userModel.setFirstname(firstName);
        userModel.setLastname(lastname);
        userModel.setEmail(email);
        userModel.setPassword(password);
        userModel.setPhoneNumber(phoneNumber);
        userModel.setAddress(address);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("firstname", firstName);
        jsonObject.put("lastname", lastname);
        jsonObject.put("email", email);
        jsonObject.put("password", password);
        jsonObject.put("phoneNumber", phoneNumber);
        jsonObject.put("address", address);


        Utils.saveData(jsonObject);

        Thread.sleep(5000);

//        // Retrieve the email body from Gmail (after registration)
//        ReadGmail readGmail = new ReadGmail();
//        String mailBody = readGmail.readMail();  // Get the email body
//        System.out.println(mailBody);
//
//        // Perform assertion on the email body
//        String expectedMessage = " Welcome to our platform! We&#39;re excited to have you onboard. Best regards, Road to Career";
//        Assert.assertTrue(mailBody.contains(expectedMessage));


    }
}
