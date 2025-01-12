package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class RegistrationPage {
    @FindBy(id="firstName")
    WebElement txtFirstName;

    @FindBy(id="lastName")
    WebElement txtLastName;


    @FindBy(id="email")
    WebElement txtEmail;

    @FindBy(id="password")
    WebElement txtPassword;

    @FindBy(id="phoneNumber")
    WebElement txtphoneNumber;

    @FindBy(id="address")
    WebElement txtAddress;

    @FindBy(css = "[type=radio]")
    List<WebElement > radioBtn;

    @FindBy(css = "[type=checkbox]")
    WebElement checkTerms;

    @FindBy(id="register")
    WebElement btnRegister;

    public RegistrationPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }

    public void doRegistration(String firstName,String lastName, String email, String password, String phoneNumber,String address) {
        txtFirstName.sendKeys(firstName);

        txtEmail.sendKeys(email);
        txtLastName.sendKeys(lastName);
        txtPassword.sendKeys(password);
        txtphoneNumber.sendKeys(phoneNumber);
        txtAddress.sendKeys(address);
        txtAddress.click();

        btnRegister.click();
        radioBtn.get(0).click();
        checkTerms.click();
        btnRegister.click();


    }

}

