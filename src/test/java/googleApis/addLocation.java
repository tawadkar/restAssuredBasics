package googleApis;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import files.payLoads;

public class addLocation {
    public static void main(String[] args) {
        /* given() - contains all th input parameters
          when() - Submit the API, resource, http method
          then() - validate the response
         */

        RestAssured.baseURI= "https://rahulshettyacademy.com";
        given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body(payLoads.addPlace())
                .when().post("maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200)
                .body("scope",equalTo("APP"))
                .header("Server","Apache/2.4.41 (Ubuntu)");
    }
}
