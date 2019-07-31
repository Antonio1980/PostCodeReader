import org.json.JSONArray;
import services.PostCodeService;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


class ApiClient {

    PostCodeService postCodeSvc = new PostCodeService();

    List<String> getParsedPostCode(String post_code) throws Exception {
        ArrayList<String> parsed_response = new ArrayList<>();
        JSONObject response = postCodeSvc.getPostCode(post_code);
        JSONObject result = response.getJSONObject("result");
        String country = result.get("country").toString();
        String region = result.get("region").toString();
        parsed_response.add(country);
        parsed_response.add(region);
        return parsed_response;
    }

    List<Map<String, String>> getParsedNearestPostsCode(String post_code) throws Exception{
        ArrayList<Map<String, String>> parsed_result = new ArrayList<>();
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

    String ifCodeValid(String post_code) throws Exception{
        JSONObject response = postCodeSvc.isValid(post_code);
        boolean result = response.getBoolean("result");
        if (result){
            return "TRUE";
        }
        else{
            return "FALSE";
        }
    }


}

