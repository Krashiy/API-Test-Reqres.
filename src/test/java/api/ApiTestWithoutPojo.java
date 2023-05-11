package api;

import helpers.Data;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ApiTestWithoutPojo {

  @Test
  public void checkAvatarContainsId(){
    Specification.installSpecification(Specification.requestSpec(Data.BASE_URL), Specification.responseSpecOK200());
    Response response = given()
            .when()
            .get(Data.USERS_URL)
            .then()
            .extract().response();
    JsonPath jsonPath = response.jsonPath();
    List<Integer> ids = jsonPath.get("data.id");
    List<String> avatars = jsonPath.get("data.avatar");
    for(int i = 0; i < avatars.size(); i++){
      Assert.assertTrue(avatars.get(i).contains(ids.get(i).toString()));
    }
  }
  @Test
  public void checkSortedByYear(){
    Specification.installSpecification(Specification.requestSpec(Data.BASE_URL), Specification.responseSpecOK200());
    Response response = given()
            .when()
            .get(Data.RESOURCE_URL)
            .then()
            .extract().response();
    JsonPath jsonPath = response.jsonPath();
    List<Integer> years = jsonPath.get("data.year");
    List<Integer> sortedYears = years.stream().sorted().toList();
    for(int i = 0; i < years.size(); i++){
      Assert.assertEquals(years.get(i), sortedYears.get(i));
    }
  }
}
