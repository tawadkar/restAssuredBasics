package googleApis;

import io.restassured.path.json.JsonPath;

public class commonUtils {

    public static JsonPath rawtoJson(String response) {

        JsonPath getPlacePath = new JsonPath(response);
        return getPlacePath;
    }

    public static JsonPath addCommentJson (String response) {

        JsonPath addCommentPath = new JsonPath(response);
        return  addCommentPath;
    }
}
