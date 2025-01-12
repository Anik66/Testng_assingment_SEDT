package Utils;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class Utils {
    public static int generateRandomId(int min,int max) {
        double randomId = Math.random()*(max-min)+min;
        return (int) randomId ;
    }
    public static void saveData(JSONObject jsonObject) throws IOException, ParseException {
        String filepath = "./src/test/resources/Users.json";
        JSONParser JsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) JsonParser.parse(new FileReader(filepath));


        jsonArray.add(jsonObject);
        FileWriter writer = new FileWriter(filepath);
        writer.write(jsonArray.toJSONString());
        writer.flush();
        writer.close();
    }


    Properties prop;
    public void getEnvVar() throws IOException {
        prop=new Properties();
        FileInputStream fs=new FileInputStream("./src/test/resources/config.properties");
        prop.load(fs);
    }
    public static void setEnvVar(String key, String value) throws ConfigurationException {
        PropertiesConfiguration config=new PropertiesConfiguration("./src/test/resources/config.properties");
        config.setProperty(key,value);
        config.save();
    }
}
