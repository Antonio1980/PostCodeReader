import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Reader {

    public static void main(String[] args){

        ApiClient apiClient = new ApiClient();

        Scanner input = new Scanner(System.in);

        System.out.print("Please enter String representing a post code.");

        String post_code = input.next();

        boolean ifValid = false;
        try {
            ifValid = apiClient.postCodeSvc.isValid(post_code).getBoolean("result");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (ifValid){
            System.out.print("\t Please choose an available action by number! \n" +
                             "\t 1. GET /postcodes/{POSTCODE} \n" +
                             "\t 2. GET /postcodes/{POSTCODE}/validate\n" +
                             "\t 3. GET /postcodes/{POSTCODE}/nearest\n");
        }
        else{
            System.out.println("PostCode is not valid. Please try again");
            System.exit(0);
        }

        int action = input.nextInt();

        switch (action){
            case 1:
                try {
                    List<String> res = apiClient.getParsedPostCode(post_code);
                    if (res != null){
                        System.out.println(String.format("For your postcode %s, found next info: Country: %s, Region: %s.",
                                post_code, res.get(0), res.get(1)));
                    }
                    else{
                        System.out.println("Postcodes not found for provided postcode: " + post_code);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                try {
                    String res = apiClient.ifCodeValid(post_code);
                    System.out.println("Provided postcode: " + post_code + " is VALID? -> " + res);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                try {
                    List<Map<String, String>> res = apiClient.getParsedNearestPostsCode(post_code);
                    if (res != null){
                        System.out.println("The nearest postcodes for provided postcode " + post_code + " is: \n");
                        System.out.println(res);
                    }
                    else{
                        System.out.println("Nearest postcodes not found for provided postcode: " + post_code);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                System.out.println("Default run");
        }

    }

}
