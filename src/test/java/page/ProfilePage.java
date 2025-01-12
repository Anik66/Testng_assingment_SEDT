package page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProfilePage {
    @FindBy(css="[type=button]")

    public List<WebElement> btnView;

    @FindBy(name="email")
    WebElement txtEmail;

    public ProfilePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void  updateProfile(String email)  {

        btnView.get(1).click();
        txtEmail.sendKeys(Keys.CONTROL,"a",Keys.BACK_SPACE);
        txtEmail.sendKeys(email);

        btnView.get(2).click();



    }



}
