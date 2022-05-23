package googleApis;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.Location;
import pojo.addLocation;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class reqResSpecBuilder {

    public static void main(String[] args) {
        pojo.addLocation l = new addLocation();
        l.setAccuracy(50);
        l.setAddress("29 side layout cohen 09");
        l.setLanguage("French-IN");
        l.setPhone_number("(+91) 983 893 3937");
        l.setWebsite("https://rahulshettyacademy.com");
        l.setName("Tanmay Frontline house");

        //Set type has return type as List, therefore we created list before passing it to setTypes
        //types is a separate list of strings and do not contain key value pairs hence pojo is not created
        //  key=getter value=setter
        List<String> typeList = new ArrayList<>();
        typeList.add("shoe park");
        typeList.add("shop");
        l.setTypes(typeList);

        /*
         * Location is separate array having key value pairs therefore we created separate pojo class
         */
        Location lo = new Location();
        lo.setLat(-38.383494);
        lo.setLng(33.427362);
        l.setLocation(lo);

        /*
        Return type for RequestSpecBuilder is RequestSpecification
         */
        RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
                .setContentType(ContentType.JSON).build();

        /*
        Return type for ResponseSpecBuilder is ResponseSpecification
         */
        ResponseSpecification respec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

        //Request Body
        RequestSpecification res = given().spec(req)
                .body(l);

         //Response Body
          Response response= res.when().post("maps/api/place/add/json")
                .then().spec(respec).extract().response();
        String locationResponse = response.asString();
        System.out.println(locationResponse);
    }
}
