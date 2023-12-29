package googleApis;

import java.util.HashMap;
import java.util.Map;
import commonUtils.Base;
import io.restassured.response.Response;
import restUtils.RestUtils;

public class googleAPIs {

    public Response addLocation(Map<String,Object> addLocationPayload){
      String endpoint= (String) Base.dataFromJsonFile.get("addLocationEndpoint");

      return RestUtils.performPost(endpoint,addLocationPayload,new HashMap<>());
    }

    public Response addLocation(String addLocationPayload){
        String endpoint  =  (String) Base.dataFromJsonFile.get("addLocationEndpoint");
        return RestUtils.performPost(endpoint,addLocationPayload,new HashMap<>());
    }
}
