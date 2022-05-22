package googleApis;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojo.addLocation;
import pojo.Location;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
public class serialization {
    public static void main(String[] args) {
        addLocation l = new addLocation();
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

        RestAssured.baseURI="https://rahulshettyacademy.com";
      Response response=  given().queryParams("key","qaclick123").log().all()
                .body(l)
                .when().post("maps/api/place/add/json")
                .then().assertThat().statusCode(200).extract().response();
      String locationResponse = response.asString();
      System.out.println(locationResponse);
    }


}
