package setup;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.testng.annotations.DataProvider;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddDataSet {
    @DataProvider(name = "FileWithCSVData")
    public Object[][] GetDataSet() throws IOException {
        String filepath = "./src/test/resources/data.csv";
        CSVParser csvparser = new CSVParser(new FileReader(filepath), CSVFormat.DEFAULT.withFirstRecordAsHeader());

        List<Object[]> data = new ArrayList<>();
        for (CSVRecord csvrecord : csvparser) {
            String itemName = csvrecord.get("itemName");
            String amount = csvrecord.get("amount");
            String remarks = csvrecord.get("remarks");
            data.add(new Object[]{itemName, amount, remarks});
        }
        csvparser.close();
        return data.toArray(new Object[0][]);
    }
}
