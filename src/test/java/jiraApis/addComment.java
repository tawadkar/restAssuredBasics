package jiraApis;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import  files.payLoads;
import io.restassured.filter.session.SessionFilter;

public class addComment {

    public static void main(String[] args) {
        RestAssured.baseURI="http://localhost:8080";

        //Login to Jira
        /* Session Filter object session stores the entire response
        *
        * */
        SessionFilter session = new SessionFilter();
        String loginResponse = given().header("Content-Type","application/json")
                        .body(payLoads.LoginToJira()).log().all()
                        .filter(session).when().post("/rest/auth/1/session")
                        .then().log().all().extract().response().asString();



        given().pathParam("comment_id","10002").log().all()
                .header("Content-Type","application/json")
                .body(payLoads.addComment())
                .filter(session).when().post("/rest/api/2/issue/{comment_id}/comment")
                .then().log().all().assertThat().statusCode(201);
    }
}
