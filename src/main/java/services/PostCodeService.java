package services;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;


/**
 * @author Tanya Haus
 */
public class PostCodeService extends ServiceBase{

    private String baseUrl;

    public PostCodeService() {
        this.baseUrl = super.baseUrl + "/postcodes/";
    }

    /**
     *
     * @param postcode
     * @return
     * @throws Exception
     */
    public JSONObject getPostCode(String postcode) throws UnirestException {
        String uri = this.baseUrl.concat(postcode);

        System.out.println("API URL: " + uri);
        return Unirest.get(uri).header(this.header_key, this.header_value).asJson().getBody().getObject();
    }

    /**
     *
     * @param postcode
     * @return
     * @throws Exception
     */
    public JSONObject getNearest(String postcode) throws UnirestException {
        String uri = this.baseUrl.concat(postcode).concat("/nearest");
        System.out.println(uri);
        HttpResponse<JsonNode> jsonResponse = Unirest.get(uri).header(this.header_key, this.header_value).asJson();
        return jsonResponse.getBody().getObject();
    }

    /**
     *
     * @param postcode
     * @return
     * @throws UnirestException
     */
    public boolean isValid(String postcode) throws UnirestException {
        String uri = this.baseUrl.concat(postcode).concat("/validate");
        System.out.println(uri);
        return Unirest.get(uri).header(this.header_key, this.header_value).asJson()
                .getBody().getObject().getBoolean("result");
    }

}//end of PostCodeService class
