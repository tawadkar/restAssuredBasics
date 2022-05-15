package oAuth;
import static io.restassured.RestAssured.given;
import googleApis.commonUtils;
import io.restassured.path.json.JsonPath;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class OAuthTest {

    public static void main(String[] args) throws InterruptedException {
      /*  System.setProperty("webdriver.chrome.driver","C:\\Users\\tanma\\Downloads\\chromedriver.exe");
        WebDriver driver  = new ChromeDriver();
        driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php");
        driver.findElement(By.cssSelector("input[type='email']")).sendKeys("tanmayywadkar@gmail.com");
        driver.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("input[type='password']")).sendKeys("BoRninS@ngli");
        driver.findElement(By.cssSelector("input[type='password']")).sendKeys(Keys.ENTER);
        Thread.sleep(2000);*/
      //  String url = driver.getCurrentUrl();

        /* Steps to generate below url
         * 1. Navigate to https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php
         * 2.Enter gmail Username and Password
         * 3.Below url will be generated
         * */
        String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AX4XfWge5OC45gDAFWXM7_Tpgc9--YaSqFJkLN4m5hly405J-CrrDrY5XtuVdFtqNCgKUA&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=consent";
        String partialurl = url.split("code=")[1];
        String code = partialurl.split("&scope")[0];
        System.out.println("AUTHORIZATION CODE FROM GOOGLE TO GENERATE ACCESS TOKEN***" +code);

      //urlEncodingEnabled=false will not encode the url i.e special characters will not be converted to numeric digits
      String accessTokenFromGoogle=  given().urlEncodingEnabled(false)
                .queryParams("code",code)
                .queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
                .queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
                .queryParams("grant_type","authorization_code")
                .queryParams("state", "verifyfjdss")
                .queryParams("session_state", "ff4a89d1f7011eb34eef8cf02ce4353316d9744b..7eb8")
              // .queryParam("scope", "email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email")
                .when().log().all()
                .post("https://www.googleapis.com/oauth2/v4/token").asString();
            System.out.println("Access Token from GOOGLE*********" + accessTokenFromGoogle);

        JsonPath js = commonUtils.rawtoJson(accessTokenFromGoogle);
        String accessToken = js.getString("access_token");
        System.out.println("ACCESS TOKEN IS FOR GET COURSE *********" +accessToken);

      String accessTokenResponse = given().queryParam("access_token",accessToken)
              .when().log().all()
              .get("https://rahulshettyacademy.com/getCourse.php").asString();
      System.out.println(accessTokenResponse);

    }
}