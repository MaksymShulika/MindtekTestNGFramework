package tests;

import com.github.wnameless.json.flattener.JsonFlattener;
import com.github.wnameless.json.unflattener.JsonUnflattener;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ApiUtils;
import utilities.JDBCUtils;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class LogisticsDriverApiTests {


    @Test (groups = {"api"})
    public void validatePostDriverApiCall() throws SQLException {
        /*
        1. User send post driver api call
        2. User validates status code
        3. User send get created driver api call exist
        4. User validates that driver exist
        5. User validates that driver is persisted in database
         */
        // Creating driver
        String requestData = ApiUtils.getJson("Driver");

        Map<String, Object> requestDataMap = JsonFlattener.flattenAsMap(requestData);

        String name = "Maksym" + new Random().nextInt(10000);
        String email = "abc" + new Random().nextInt(10000) + "@gmail.com";


        requestDataMap.put("full_name", name);
        requestDataMap.put("contacts[0].email", email);


        requestData = JsonUnflattener.unflatten(requestDataMap.toString());


        //1
        Response response = given().baseUri("https://elarbridgelogisticsmindtek.space/en-us/api/v2")
                .and().accept("application/json")
                .and().contentType("application/json")
                .and().header("Authorization", "Token 9d3994dd2afd7d1d8ae9ecf4d77e45932bb210d6")
                .and().body(requestData)
                .and().log().all()
                .when().post("/driver/");

        response.then().log().all();
        // 2
        response.then().statusCode(201);

        String driverID = response.body().jsonPath().getString("id");

        // 3
        Response getDriverResponse = given().baseUri("https://elarbridgelogisticsmindtek.space/en-us/api/v2")
                .and().accept("application/json")
                .and().header("Authorization", "Token 9d3994dd2afd7d1d8ae9ecf4d77e45932bb210d6")
                .and().log().all()
                .when().get("/driver/" + driverID + "/");

        //4
        getDriverResponse.then().log().all();
        getDriverResponse.then().statusCode(200);
        getDriverResponse.then().body("full_name", Matchers.equalTo(name));
        getDriverResponse.then().body("contacts[0].email", Matchers.equalTo(email));

        //5
        JDBCUtils.establishConnection();
        List<Map<String, Object>> dbData = JDBCUtils.runQuery("select * from core_driver where id = " + driverID);

        Assert.assertEquals(getDriverResponse.body().jsonPath().getString("full_name"), dbData.get(0).get("full_name"));
        getDriverResponse.then().body("full_name", Matchers.equalTo(dbData.get(0).get("full_name")));




    }
}
