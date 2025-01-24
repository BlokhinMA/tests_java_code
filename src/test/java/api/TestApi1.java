package api;

import api.pojos.RegistrationRequest;
import api.pojos.SuccessfulRegistrationResponse;
import api.pojos.UnsuccessfulRegistrationResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class TestApi1 {

    private static final String URL = "https://reqres.in/";

    @Test
    public void successfulRegistration() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        Integer id = 4;
        String token = "QpwL5tke4Pnpja7X4";
        RegistrationRequest request = new RegistrationRequest("eve.holt@reqres.in", "pistol");
        SuccessfulRegistrationResponse response = given()
                .body(request)
                .when()
                .post("api/register")
                .then()
                .log().all()
                .extract().as(SuccessfulRegistrationResponse.class);
        Assertions.assertEquals(id, response.getId());
        Assertions.assertEquals(token, response.getToken());
    }

    @Test
    public void unsuccessfulRegistration() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecBadRequest400());
        RegistrationRequest request = new RegistrationRequest("sydney@fife", null);
        UnsuccessfulRegistrationResponse response = given()
                .body(request)
                .when()
                .post("api/register")
                .then()
                .log().all()
                .extract().as(UnsuccessfulRegistrationResponse.class);
        Assertions.assertEquals("Missing password", response.getError());
    }

}
