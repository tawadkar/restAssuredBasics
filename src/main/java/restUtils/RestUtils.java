package restUtils;
import  io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.Map;
public class RestUtils {

    public static Response performPost(String endPoint, String requestPayload, Map<String,String> header){

       return RestAssured.given().log().all()
               .baseUri(endPoint)
               .queryParam("key","qaclick123")
               .headers(header)
               .contentType(ContentType.JSON)
               .body(requestPayload)
               .post()
               .then().log().all().extract().response();
    }

    public static Response performPost(String endPoint, Map<String,Object> requestPayload, Map<String,String> header){

        return RestAssured.given().log().all()
                .baseUri(endPoint)
                .queryParam("key","qaclick123")
                .headers(header)
                .contentType(ContentType.JSON)
                .body(requestPayload)
                .post()
                .then().log().all().extract().response();
    }

}
