package googleApis;

import groovy.json.JsonOutput;
import io.restassured.path.json.JsonPath;
import files.payLoads;

public class complexJsonParse {
    public static void main(String[] args) {


        JsonPath complexjson = new JsonPath(payLoads.CoursePrice());

        //No of courses returned by API
        int course = complexjson.getInt("courses.size()");
        System.out.println(course);

        // Purchase amount
        int amount = complexjson.getInt("dashboard.purchaseAmount");
        System.out.println(amount);

        //Title of first course
        String title = complexjson.get("courses[2].title");
        System.out.println(title);

        //print all course titles and their prices

        for(int i=0;i<course;i++) {
            String courseTitles= complexjson.get("courses[" + i + "].title"); //Placing variable between string
            System.out.println(courseTitles);
            System.out.println(complexjson.get("courses[" + i + "].price").toString());
        }

        //print no of copies sold by RPA
        System.out.println("Copies sold by RPA");
        for(int i=0;i<course;i++){
            String courseTitles= complexjson.get("courses[" + i + "].title");
            if(courseTitles.equalsIgnoreCase("RPA")){
                System.out.println(complexjson.get("courses[" + i + "].price").toString());
                break;
            }
        }
    }
}
