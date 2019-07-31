package services;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.commons.logging.Log;
import org.apache.http.conn.HttpHostConnectException;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.TooManyListenersException;
import java.util.concurrent.TimeUnit;



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
        System.out.println(this.baseUrl.concat(postcode));
        return Unirest.get(this.baseUrl.concat(postcode)).asJson().getBody().getObject();
    }

    /**
     *
     * @param postcode
     * @return
     * @throws Exception
     */
    public JSONObject getNearest(String postcode) throws UnirestException {

        System.out.println(this.baseUrl);
        HttpResponse<JsonNode> jsonResponse = Unirest.get(this.baseUrl.concat(postcode).concat("/nearest")).asJson();

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
        return Unirest.get(this.baseUrl.concat(postcode).concat("/validate")).asJson()
                .getBody().getObject().getBoolean("result");
    }

}//end of PostCodeService class
