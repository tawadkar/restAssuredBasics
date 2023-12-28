package googleApis;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import files.payLoads;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class addLocation {
    public static void main(String[] args) throws IOException {
        /* given() - contains all th input parameters
          when() - Submit the API, resource, http method
          then() - validate the response
         */

        RestAssured.baseURI= "https://rahulshettyacademy.com";
       String response = given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
                //.body(payLoads.addPlace())
               // .readAllBytes=> converts content of file from string to byte, new String=> converts back from byte to string
                .body(new String(Files.readAllBytes(Paths.get("T:\\apiAutomation\\src\\test\\java\\files\\addPlace.json"))))
                .when().post("maps/api/place/add/json")
                .then().assertThat().statusCode(200)
                .body("scope",equalTo("APP"))
                .header("Server","Apache/2.4.52 (Ubuntu)")
                .extract().response().asString();

                System.out.println(response);
                //Parse the Json file, i.e extract values from the response
                JsonPath parsedResponse = new JsonPath(response);
                String placeid = parsedResponse.getString("place_id");
                System.out.println("Extracted Place id from Response"+ placeid);

                //Update Place
              String updatedAddress ="Summer Walk Africa";
                given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body("{\n" +
                        "\"place_id\":\""+placeid+"\",\n" +
                        "\"address\":\""+updatedAddress+"\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}\n")
                .when().put("maps/api/place/update/json")
                .then().assertThat().log().all().statusCode(200).body("msg",equalTo("Address successfully updated"));

               //Get Place
        String getPlaceResponse = given().log().all().queryParam("key","qaclick123")
                .queryParam("place_id",placeid)
                .when().get("maps/api/place/get/json")
                .then().assertThat().log().all().statusCode(200).extract().response().asString();

           JsonPath getPlacePath =  commonUtils.rawtoJson(getPlaceResponse);
           String actualAddress  = getPlacePath.getString("address");
           System.out.println(actualAddress);
           Assert.assertEquals(actualAddress,updatedAddress);

    }
}
