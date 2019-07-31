import org.json.JSONArray;
import services.PostCodeService;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Tanya Haus
 */
public class ApiClient {

    PostCodeService postCodeSvc = new PostCodeService();

    /**
     *
     * @param post_code
     * @return
     * @throws Exception
     */
    public String getParsedPostCode(String post_code) throws Exception {
        JSONObject response = postCodeSvc.getPostCode(post_code);
        JSONObject result = response.getJSONObject("result");
        String country = result.get("country").toString();
        String region = result.get("region").toString();
        return String.format("For your postcode %s, found next info: Country: %s, Region: %s.", post_code, country, region);
    }

    /**
     *
     * @param post_code
     * @return
     * @throws Exception
     */
    public List getParsedNearestPostsCode(String post_code) throws Exception{
        List<Map> parsed_result = new ArrayList();
        JSONObject response = postCodeSvc.getNearest(post_code);
        JSONArray result = (JSONArray) response.get("result");
        for (int i=0; i < result.length() - 1; i++){
            JSONObject res = (JSONObject) result.get(i);
            String country = res.get("country").toString();
            String region = res.get("region").toString();
            String postcode = res.get("postcode").toString();
            Map<String, String> ready_result = new HashMap<>();
            ready_result.put("postcode", postcode);
            ready_result.put("country", country);
            ready_result.put("region", region);
            parsed_result.add(ready_result);
        }
        return parsed_result;

    }


}//end of ApiClient class

