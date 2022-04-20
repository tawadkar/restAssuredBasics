package googleApis;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import files.payLoads;
import io.restassured.path.json.JsonPath;

public class addLocation {
    public static void main(String[] args) {
        /* given() - contains all th input parameters
          when() - Submit the API, resource, http method
          then() - validate the response
         */

        RestAssured.baseURI= "https://rahulshettyacademy.com";
       String response = given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body(payLoads.addPlace())
                .when().post("maps/api/place/add/json")
                .then().assertThat().statusCode(200)
                .body("scope",equalTo("APP"))
                .header("Server","Apache/2.4.41 (Ubuntu)")
                .extract().response().asString();

                System.out.println(response);
                //Parse the Json file, i.e extract values from the response
                JsonPath parsedResponse = new JsonPath(response);
                String placeid = parsedResponse.getString("place_id");
                System.out.println("Extracted Place id from Response"+ placeid);

    }
}
