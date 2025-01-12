package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginNewPassword {
    @FindBy(id="email")
    WebElement txtEmail;

    @FindBy(id="password")
    WebElement txtPassword;

    @FindBy(css = "[type=submit]")
    WebElement btnLogin;

    public LoginNewPassword(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }

    public void dologin(String email, String password) {
        txtEmail.sendKeys(email);
        txtPassword.sendKeys(password);
        btnLogin.click();
    }



}
