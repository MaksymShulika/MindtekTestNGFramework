package utilities;

import com.github.wnameless.json.flattener.JsonFlattener;
import com.github.wnameless.json.unflattener.JsonUnflattener;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import io.restassured.response.Response;
import org.json.simple.parser.JSONParser;
import pojo.Api_clients;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiUtils {



    public static String getToken() {
        String clientName = "Maks";
        String clientEmail = BrowserUtils.getRandomEmail();

        String requestApi_clients = getJson("Api_clients");
        Map<String, Object> jsonMap = JsonFlattener.flattenAsMap(requestApi_clients);
        jsonMap.put("clientName", clientEmail);
        jsonMap.put("clientEmail", clientEmail);
        requestApi_clients = JsonUnflattener.unflatten(jsonMap.toString());

//        Api_clients api_clients = new Api_clients();
//
//
//
//        api_clients.setClientName(clientName);
//        api_clients.setClientEmail(clientEmail);
//
//        Gson gson = new Gson();
//
//        String requestApi_clients = gson.toJson(api_clients);

        Response response = given().baseUri(ConfigReader.getProperty("BooksAPIBaseUri"))
                .and().accept("application/json")
                .and().contentType("application/json")
                .and().body(requestApi_clients)
                .when().post("api-clients");

        return response.body().jsonPath().getString("accessToken");
    }

    public static String getJson(String name){

        String path = "C:\\Users\\User\\IdeaProjects\\MindtekTestNGFramework\\src\\test\\resources\\testdata\\"+name+".json";

        JSONParser jsonParser = new JSONParser();
        String data = "";
        try {
            FileReader reader = new FileReader(path);
            data = jsonParser.parse(reader).toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return data;
    }
}
