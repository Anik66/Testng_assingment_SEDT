package setup;

import Utils.Utils;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.configuration.ConfigurationException;
import org.testng.annotations.Test;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class ReadGmail {


    private static Properties prop = new Properties();


    private static void loadProperties() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("./src/test/resources/config.properties");
        prop.load(fileInputStream);
    }


    private static void saveProperties() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("./src/test/resources/config.properties");
        prop.store(fileOutputStream, null);
    }


    @Test(priority = 1)
    public void getGmailList() throws IOException, ConfigurationException {
        loadProperties();
        RestAssured.baseURI = "https://gmail.googleapis.com";
        Response response = given().contentType("application/json")
                .header("Authorization", "Bearer " + prop.getProperty("gmail_token"))
                .when().get("/gmail/v1/users/me/messages");

        JsonPath jsonPath = response.jsonPath();
        String mailId = jsonPath.get("messages[0].id");
        System.out.println(mailId);


        prop.setProperty("mail_id", mailId);
        saveProperties();
    }




    public String readMail() throws IOException, ConfigurationException {
        loadProperties();
        String gmailToken = prop.getProperty("gmail_token");
        String mailId = prop.getProperty("mail_id");

        if (mailId == null || mailId.isEmpty()) {
            System.err.println("Mail ID is not available. Please fetch the Gmail list first.");
            return null;
        }

        RestAssured.baseURI = "https://gmail.googleapis.com";
        Response response = given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + gmailToken)
                .when()
                .get("/gmail/v1/users/me/messages/" + mailId);

        if (response.statusCode() == 200) {
            JsonPath jsonPath = response.jsonPath();
            String mailBody_regi = jsonPath.get("snippet");
            Utils.setEnvVar("mailBodyLink", mailBody_regi);
            System.out.println(mailBody_regi);
            return mailBody_regi;
        } else {
            System.err.println("Failed to fetch the mail. Status code: " + response.statusCode());
            return null;
        }
    }
}
