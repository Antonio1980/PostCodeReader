import com.mashape.unirest.http.exceptions.UnirestException;
import java.util.Scanner;


public class Reader {

    public static void main(String[] args) throws Exception {

        ApiClient apiClient = new ApiClient();

        Scanner input = new Scanner(System.in);

        System.out.print("Please enter String representing a post code.");

        String post_code = input.next();

        apiClient.postCodeSvc.isValid(post_code);

        boolean ifValid = false;
        try {
            ifValid = apiClient.postCodeSvc.isValid(post_code);
        } catch (UnirestException e) {
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
                    System.out.println(apiClient.getParsedPostCode(post_code));
                } catch (UnirestException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                try {
                    System.out.println(apiClient.postCodeSvc.isValid(post_code));
                } catch (UnirestException e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                try {
                    System.out.println(apiClient.getParsedNearestPostsCode(post_code));
                } catch (UnirestException e) {
                    e.printStackTrace();
                }
                break;
            default:
                System.out.println("Default run");
        }

    }

}
