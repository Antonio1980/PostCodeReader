package services;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.commons.logging.Log;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * @author Tanya Haus
 */
public class PostCodeService extends ServiceBase{

    String header_key = "Content-Type";
    String header_value = "application/json";
    private String baseUrl = "http://postcodes.io/postcodes/";
    private Log log;

    /**
     *
     * @param postcode
     * @return
     * @throws Exception
     */
    public JSONObject getPostCode(String postcode) throws Exception {
        String uri = this.baseUrl.concat(postcode);

        System.out.println("API URL: " + uri);
        return Unirest.get(uri).asJson().getBody().getObject();
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
        HttpResponse<JsonNode> jsonResponse = Unirest.get(uri).asJson();
        return jsonResponse.getBody().getObject();
    }

    /**
     *
     * @param postcode
     * @return
     * @throws JSONException
     * @throws UnirestException
     */
    public boolean isValid(String postcode) throws JSONException, UnirestException {
        String uri = this.baseUrl.concat(postcode).concat("/validate");
        System.out.println(uri);
        return Unirest.get(uri).asJson()
                .getBody().getObject().getBoolean("result");
    }

}//end of PostCodeService class
