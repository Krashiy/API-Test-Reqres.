package api;

import helpers.Data;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojoClasses.*;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ApiTest {

  @Test
  public void checkAvatarContainsId(){
    Specification.installSpecification(Specification.requestSpec(Data.BASE_URL), Specification.responseSpecOK200());
    List<UserData> users = given()
            .when()
            .get(Data.USERS_URL)
            .then()
            .extract().body().jsonPath().getList("data", UserData.class);
    List<Integer> ids = users.stream().map(user -> user.getId()).toList();
    List<String> avatars = users.stream().map(user -> user.getAvatar()).toList();
    for(int i = 0; i < avatars.size(); i++){
      Assert.assertTrue(avatars.get(i).contains(ids.get(i).toString()));
    }
  }
  @Test
  public void successRegister(){
    Specification.installSpecification(Specification.requestSpec(Data.BASE_URL), Specification.responseSpecOK200());
    RegisterData user = new RegisterData("eve.holt@reqres.in", "pistol");
    SuccessRegister successReg = given()
            .body(user)
            .when()
            .post(Data.REGISTER_URL)
            .then()
            .extract().as(SuccessRegister.class);
    Assert.assertNotNull(successReg.getId());
    Assert.assertNotNull(successReg.getToken());
  }

  @Test
  public void unSuccessRegister(){
    Specification.installSpecification(Specification.requestSpec(Data.BASE_URL), Specification.responseSpecError400());
    RegisterData user = new RegisterData("eve.holt@reqres.in", "");
    UnSuccessRegister unSuccessReg = given()
            .body(user)
            .when()
            .post(Data.REGISTER_URL)
            .then().log().all()
            .extract().as(UnSuccessRegister.class);
    Assert.assertNotNull(unSuccessReg.getError());
  }
  @Test
  public void checkSortedByYear(){
    Specification.installSpecification(Specification.requestSpec(Data.BASE_URL), Specification.responseSpecOK200());
    List<ResourceData> resourceData = given()
            .when()
            .get(Data.RESOURCE_URL)
            .then()
            .extract().body().jsonPath().getList("data", ResourceData.class);

    List<Integer> sortedYears = resourceData.stream().map(data -> data.getYear()).sorted().toList();
    for(int i = 0; i < resourceData.size(); i++){
      Assert.assertEquals(resourceData.get(i).getYear(), sortedYears.get(i));
    }
  }
  @Test
  public void updateUser(){
    Specification.installSpecification(Specification.requestSpec(Data.BASE_URL), Specification.responseSpecOK200());
    UpdateUser user = new UpdateUser("morpheus", "zion resident");
    SuccessUpdatedUser successUpdate = given()
            .body(user)
            .when()
            .patch(Data.UPDATE_URL)
            .then()
            .extract().as(SuccessUpdatedUser.class);
    Assert.assertNotNull(successUpdate.getUpdatedAt());
    Assert.assertEquals(successUpdate.getName(), "morpheus");
    Assert.assertEquals(successUpdate.getJob(), "zion resident");
  }
  @Test
  public void deleteUser(){
    Specification.installSpecification(Specification.requestSpec(Data.BASE_URL), Specification.responseSpecUnique(204));
    Response response = given()
            .when()
            .delete(Data.DELETE_URL)
            .then()
            .extract().response();
    Assert.assertEquals(response.getStatusCode(), 204);
  }
}
