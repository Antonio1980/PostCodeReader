package services;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;


public class PostCodeService extends ServiceBase{

    private String baseUrl;

    public PostCodeService() {
        this.baseUrl = super.baseUrl + "/postcodes/";
    }

    public JSONObject getPostCode(String postcode) throws UnirestException {
        String uri = this.baseUrl.concat(postcode);

        System.out.println("API URL: " + uri);
        return Unirest.get(uri).header(this.header_key, this.header_value).asJson().getBody().getObject();
    }

    public JSONObject getNearest(String postcode) throws UnirestException {
        String uri = this.baseUrl.concat(postcode).concat("/nearest");

        System.out.println(uri);
        return Unirest.get(uri).header(this.header_key, this.header_value).asJson().getBody().getObject();
    }

    public boolean isValid(String postcode) throws UnirestException {
        String uri = this.baseUrl.concat(postcode).concat("/validate");

        System.out.println(uri);
        return Unirest.get(uri).header(this.header_key, this.header_value).asJson().getBody().getObject().getBoolean("result");
    }

}
