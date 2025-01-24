package api;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class TestApi3 {

    private static final String URL = "https://reqres.in/";

    @Test
    public void successfulDeletion() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecNoContent204());
        given()
                .when()
                .delete("api/users/2")
                .then()
                .log().all();
    }

}
