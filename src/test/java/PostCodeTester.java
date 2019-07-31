import org.json.JSONArray;
import org.json.JSONObject;
import services.PostCodeService;


public class PostCodeTester {

    public static void main(String[] args) throws Exception {

        PostCodeService postcodeSvc = new PostCodeService();

        try{
            System.out.println("------------ Negative test ------------ ");
            System.out.println("------------ Verify that not valid postcode string will return error.");

            JSONObject response = postcodeSvc.getPostCode("NOT_EXIST");
            if ("Invalid postcode".equals(response.get("error"))){
                System.out.println("Negative test is PASSED!");
            }
            else{
                System.out.println("Negative test is FAILED!");
            }
            System.out.println(response.toString());


            System.out.println("------------ Validation test ------------ ");
            System.out.println("------------ Verify that postcode that returned in server response on 'GetNearest' exists and valid.");

            String validPostCode = "CB30FA";
            String postcodeToValidate = "";
            JSONObject response2 = postcodeSvc.getNearest(validPostCode);
            JSONArray result = (JSONArray) response2.get("result");

            for (int i=0; i < result.length() - 1; i++) {
                JSONObject item = ((JSONObject) result.get(i));
                if(!item.get("postcode").equals(validPostCode)){
                    postcodeToValidate = (String) item.get("postcode");
                }
            }

            System.out.println("Nearest postcodes received, going to request postcode info!");
            System.out.println(response2.toString());

            boolean response3 = postcodeSvc.isValid(postcodeToValidate);

            if (response3){
                JSONObject response4 = postcodeSvc.getPostCode(postcodeToValidate);
                if ((Integer) response4.get("status") == 200){
                    System.out.println("------------ Validation test is PASSED! ----------- ");
                }
            }
            else{
                System.out.println("------------ Validation test is FAILED! ----------- ");
            }


        }finally {
            System.out.println("Test run is finished!");
        }
    }
}
