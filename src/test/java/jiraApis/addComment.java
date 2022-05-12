package jiraApis;

import io.restassured.RestAssured;
import googleApis.commonUtils;
import static io.restassured.RestAssured.given;
import  files.payLoads;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import java.io.File;

public class addComment {

    public static void main(String[] args) {
        RestAssured.baseURI="http://localhost:8080";

        //Login to Jira
        /* Session Filter object session stores the entire response
        * given().relaxedHTTPSValidation() >> Bypasses certificate validations on secured websites
        * */
        SessionFilter session = new SessionFilter();
        String loginResponse = given().relaxedHTTPSValidation().header("Content-Type","application/json")
                        .body(payLoads.LoginToJira()).log().all()
                        .filter(session).when().post("/rest/auth/1/session")
                        .then().log().all().extract().response().asString();


        //Add comment and extract comment id
        String message = "Parsing complex Jira Json Response and Adding assertions";
       String addCommentResponse = given().pathParam("comment_id","10002").log().all()
                .header("Content-Type","application/json")
                .body(payLoads.addComment(message))
                .filter(session).when().post("/rest/api/2/issue/{comment_id}/comment")
                .then().log().all().assertThat().statusCode(201).extract().response().asString();

        JsonPath js = commonUtils.addCommentJson(addCommentResponse);
        String commentId = js.getString("id");
        System.out.println("COMMENT IDS ARE***************************"+commentId+"********************************");

        //Add Attachments
        given().pathParam("comment_id","10002").header("X-Atlassian-Token","no-check")
                .header("Content-Type","multipart/form-data")
                .filter(session)
                .multiPart("file",new File("T:\\apiAutomation\\src\\test\\java\\jiraApis\\jira.txt"))
                .when().post("/rest/api/2/issue/{comment_id}/attachments")
                .then().log().all().assertThat().statusCode(200);

        //Get Issue
        //Path parameter drills down to specific issue, query paramater filters out specific field from the response
        String issueDetails = given().filter(session).pathParam("comment_id","10002")
                                     .queryParam("fields","comment")
                                     .log().all().when().get("/rest/api/2/issue/{comment_id}")
                                     .then().log().all().extract().response().asString();

         JsonPath js1 = new JsonPath(issueDetails);
         int commentsCount = js1.getInt("fields.comment.comments.size()");
         for(int i=0;i<commentsCount;i++){
              String commentIdIssue = js1.get("fields.comment.comments["+i+"].id").toString();
              System.out.println(commentIdIssue);
              if(commentIdIssue.equalsIgnoreCase(commentId)){
                  String message1 = js1.get("fields.comment.comments["+i+"].body").toString();
                  System.out.println(message1);
                  Assert.assertEquals(message1,message);
              }
         }
    }


}
