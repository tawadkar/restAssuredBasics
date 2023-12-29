package googleApis;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import restUtils.RestUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import commonUtils.jsonUtils;

public class TestClass  extends googleAPIs{

    @Test

    public void addLocation() throws IOException {
        String env = System.getProperty("env") == null ? "qa" :System.getProperty("env");
        Map<String,Object> data = jsonUtils.getJsonDataAsMap("test.json");
        //String endPoint = (String) data.get("addLocationEndpoint");
        String payload= new String(Files.readAllBytes(Paths.get("T:\\apiAutomation\\src\\test\\java\\files\\addPlace.json")));
      //  Response res = RestUtils.performPost(endPoint,new String (Files.readAllBytes(Paths.get("T:\\apiAutomation\\src\\test\\java\\files\\addPlace.json"))),new HashMap<>());
        Response res = addLocation(payload);
        Assert.assertEquals(res.statusCode(),200);


    }
}
