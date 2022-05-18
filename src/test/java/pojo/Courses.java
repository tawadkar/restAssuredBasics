package pojo;

import java.util.List;

public class Courses {
    //Since courses have List of JSONs its wrapped in List
    private List<WebAutomation> webAutomation;
    private List<Api> api;
    private List<Mobile> mobile;

  //Return type is WebAutomation Class
    public List<WebAutomation> getWebAutomation() {
        return webAutomation;
    }

    public void setWebAutomation(List<WebAutomation> webAutomation) {
        this.webAutomation = webAutomation;
    }

    //Return type is Api class
    public List<Api> getApi() {
        return api;
    }

    public void setApi(List<Api> api) {
        this.api = api;
    }
    //Return type is Mobile Class
    public List<Mobile> getMobile() {
        return mobile;
    }

    public void setMobile(List<Mobile> mobile) {
        this.mobile = mobile;
    }
}
