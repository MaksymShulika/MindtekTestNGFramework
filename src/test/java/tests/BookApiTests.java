package tests;

import com.github.wnameless.json.flattener.JsonFlattener;
import com.github.wnameless.json.unflattener.JsonUnflattener;
import com.google.gson.Gson;
import dataprovider.BooksApiDataProviders;
import io.restassured.response.Response;
import lombok.Data;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pojo.Orders;
import utilities.ApiUtils;
import utilities.ConfigReader;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;


public class BookApiTests {

//    Orders orders = new Orders();
//    Gson gson = new Gson();


    @Test(groups = {"api"})
    public void validatePostOrderBooksApiCall(){



        // Check request to books by Api call GET

        Response response = given().baseUri(ConfigReader.getProperty("BooksAPIBaseUri"))
                .and().accept("application/json")
                .and().log().all()
                .when().get("/books");

        response.then().log().all();

        List<Integer> ids = response.body().jsonPath().getList("id");//taking list of ids
        List<Boolean> available = response.body().jsonPath().getList("available");
        System.out.println("Here ids of books ==>" + ids);
        System.out.println("Here books available or no ==>" + available);

        // Checks if we have available books to order

        if (ids.size()!=0){

            Integer bookId = -1;
            for (int i = 0; i < available.size(); i++){
                if (available.get(i)){
                    bookId = ids.get(i);
                    break;
                }
            }


            String orderName = "Maksym";

            String requestPayload = ApiUtils.getJson("Orders");// String -> Map

            Map<String, Object> jsonMap = JsonFlattener.flattenAsMap(requestPayload);
            jsonMap.put("bookId", bookId);
            jsonMap.put("customerName", orderName);
            requestPayload = JsonUnflattener.unflatten(jsonMap.toString());

            //orders.setBookId(bookId);
            //orders.setCustomerName(orderName);
            //String requestOrders = gson.toJson(orders);

            String token = ApiUtils.getToken();


            // We make order with POST Order api call for available book
            response = given().baseUri(ConfigReader.getProperty("BooksAPIBaseUri"))
                    .and().accept("application/json")
                    .and().contentType("application/json")
                    .and().header("Authorization", token)
                    .and().body(requestPayload)
                    .and().log().all()
                    .when().post("/orders");

            response.then().log().all();

            String orderId = response.body().jsonPath().getString("orderId");
            System.out.println("Here order id ==>" + orderId);

            response = given().baseUri(ConfigReader.getProperty("BooksAPIBaseUri"))
                    .and().accept("application/json")
                    .and().header("Authorization", token)
                    .and().log().all()
                    .when().get("/orders/" + orderId);
            response.then().log().all()

                    .and().statusCode(200);

//            Assert.assertEquals(response.body().jsonPath().getString("customerName"), orderName);

                                                // |Matchers| is a validation calls
            response.then().body("customerName", Matchers.equalTo(orderName))
                    .and().body("id", Matchers.equalTo(orderId))
                    .and().body("bookId", Matchers.equalTo(bookId))
                    .and().header("Content-Type", Matchers.equalTo("application/json; charset=utf-8"))
                    .and().body("quantity", Matchers.greaterThan(0));

            //JWT -> Json Web Token (i use in my work)

            /**
             * 3 parts
             * 1. Header
             * 2. Payload
             * 3. Verify signature
             */

            //pojo calls


        }
    }

    @Test(groups = {"api"})
    public void validateQueryParamsLimitForGetBookApi(){
        Response response = given().baseUri(ConfigReader.getProperty("BooksAPIBaseUri"))
                .and().accept("application/json")
                .and().queryParam("limit", 3)
                .and().log().all()
                .when().get("/books");

        System.out.println("===========================================");

        response.then().log().all();
        response.then().body("",Matchers.hasSize(3));
        List<Integer> ids = response.body().jsonPath().getList("id");
        Assert.assertEquals(ids.size(), 3);
    }

    @Test(groups = {"api"})
    public void validateQueryParamsTypeForGetBookApi(){
        Response response = given().baseUri(ConfigReader.getProperty("BooksAPIBaseUri"))
                .and().accept("application/json")
                .and().queryParam("type", "fiction")
                .and().log().all()
                .when().get("/books");

        System.out.println("===========================================");

        response.then().log().all();

        response.then().body("type", Matchers.everyItem(Matchers.equalTo("fiction")));// for list

        List<String> fictions = response.body().jsonPath().getList("type");
        for (String fiction : fictions){
            Assert.assertTrue(fiction.equalsIgnoreCase("fiction"));
        }
    }

    @Test(groups = {"api"}, dataProvider = "query_parameters", dataProviderClass = BooksApiDataProviders.class)
    public void validateQueryParamsLimitTypeForGetBookApi(String type, int limit){
        Response response = given().baseUri(ConfigReader.getProperty("BooksAPIBaseUri"))
                .and().accept("application/json")
                .and().queryParam("type", type)
                .and().queryParam("limit", limit)
                .and().log().all()
                .when().get("/books");

        System.out.println("===========================================");

        response.then().log().all();

        response.then().body("", Matchers.hasSize(limit),   //for length
                "type", Matchers.everyItem(Matchers.equalTo(type)));// for list



    }

}
