package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResetPage {




    @FindBy(css = "[type='email']")
    WebElement textemail;



    public ResetPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void sendEmail(String email) {
        textemail.sendKeys(email);






    }


}

