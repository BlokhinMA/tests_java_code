package api;

import api.pojos.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class TestApi2 {

    private static final String URL = "https://reqres.in/";

    @Test
    public void checkEmails() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        List<User> users = given()
                .when()
                .get("api/users?page=2")
                .then()
                .log().all()
                .extract().body().jsonPath().getList("data", User.class);

        for (User user : users) Assertions.assertTrue(user.getEmail().contains("@reqres.in"));

    }

}
