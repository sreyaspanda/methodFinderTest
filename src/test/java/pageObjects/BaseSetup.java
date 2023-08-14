package pageObjects;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class BaseSetup {
    public WebDriver setUp() {
        RestAssured.baseURI = "http://localhost:8080";
        return new SafariDriver();
    }

    public void startTestRun() {
        RestAssured.when().get("/api/startRun").then().statusCode(200);
    }

    public void saveRecord(String fileName) throws ParseException, IOException {
        Response response = RestAssured.get("/api/methodsList");

        // Extract the JSON response body
        String responseBody = response.getBody().asString();

        // Add a key-value pair to the JSON data
        JSONObject respObject = new JSONObject();
        JSONObject jsonObject = (JSONObject) new JSONParser().parse(responseBody);
        respObject.put(fileName, jsonObject);

        // Write the modified JSON data to a file
        writeJsonToFile(respObject, fileName);
    }

    public static void writeJsonToFile(JSONObject jsonObject, String fileName) throws IOException {
        String filePath = "methodLists/" + fileName; // Specify your desired file path
        File jsonFile = new File(filePath);
        if (!jsonFile.exists()) {
            jsonFile.createNewFile();
        }

        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(jsonObject.toJSONString());
        }
    }
}
