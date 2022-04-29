package libraryApis;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;
import files.payLoads;
import googleApis.commonUtils;
import static io.restassured.RestAssured.given;

public class dynamicJson {

    @Test

    public void addBook(){
        RestAssured.baseURI= "http://216.10.245.166";

        String response = given().log().all().header("Content-Type","application/json")
                .body(payLoads.addBook())
                .when().post("/Library/Addbook.php")
                .then().log().all().assertThat().statusCode(200)
                .extract().response().asString();

        JsonPath book = commonUtils.rawtoJson(response);
        String id = book.get("ID");
        System.out.println(id);

    }

}
