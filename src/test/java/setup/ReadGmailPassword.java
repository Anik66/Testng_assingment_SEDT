package setup;

import Utils.Utils;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.configuration.ConfigurationException;



import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ReadGmailPassword {

    Properties prop;
    @Test(priority = 1)
    public void getEnvVar() throws IOException {
        prop = new Properties();
        FileInputStream fs = new FileInputStream("./src/test/resources/config.properties");
        prop.load(fs);
    }

    public ReadGmailPassword() throws IOException {
        getEnvVar();
    }

    @Test(priority = 1)

    public void getGmailList() throws IOException, ConfigurationException {
        RestAssured.baseURI = "https://gmail.googleapis.com";

        Response response = given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + prop.getProperty("gmail_token"))
                .when()
                .get("/gmail/v1/users/me/messages");

        if (response.statusCode() == 200) {
            JsonPath jsonPath = response.jsonPath();
            String mailId = jsonPath.get("messages[0].id");
            Utils.setEnvVar("mail_id_for_password", mailId);
            System.out.println("Mail ID fetched: " + mailId);
        } else {
            System.err.println("Failed to fetch the Gmail list. Status code: " + response.statusCode());
        }
    }


    public String readMail() throws IOException, ConfigurationException {
        RestAssured.baseURI = "https://gmail.googleapis.com";

        String mailId = prop.getProperty("mail_id_for_password");
        if (mailId == null || mailId.isEmpty()) {
            System.err.println("Mail ID is not set. Run getGmailList() first.");
            return null;
        }

        Response response = given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + prop.getProperty("gmail_token"))
                .when()
                .get("/gmail/v1/users/me/messages/" + mailId);

        if (response.statusCode() == 200) {
            JsonPath jsonPath = response.jsonPath();
            String mailBody = jsonPath.get("snippet");
            System.out.println("Mail Body: " + mailBody);
            Utils.setEnvVar("mailBodyLink", mailBody);
            System.out.println(mailBody);
            return mailBody;
        } else {
            System.err.println("Failed to fetch the email. Status code: " + response.statusCode());
            return null;
        }
    }
}
