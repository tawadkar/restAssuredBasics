package googleApis;

import files.payLoads;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class sumValidation {

    @Test
    public void sumValidation(){

        int sum=0;
        JsonPath complexjson = new JsonPath(payLoads.CoursePrice());
        int course = complexjson.getInt("courses.size()");
        for(int i=0;i<course;i++){
            String courseTitles= complexjson.get("courses[" + i + "].title");
            int price = complexjson.get("courses[" + i + "].price");
            int copies = complexjson.get("courses[" + i + "].copies");
            int amount = price*copies;
            System.out.println("Amount for " + courseTitles+ " is "+ amount);
            sum= sum +amount;
        }
        System.out.println("Total "+ sum);
        int purchaseAmt = complexjson.getInt("dashboard.purchaseAmount");
        Assert.assertEquals(sum,purchaseAmt);
    }
}
