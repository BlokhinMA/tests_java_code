package api;

import api.pojos.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.ZoneId;

import static io.restassured.RestAssured.given;

public class TestApi4 {

    private static final String URL = "https://reqres.in/";

    @Test
    public void compareDates() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        Employee employeeRequest = new Employee("morpheus", "zion resident");
        Employee employeeResponse = given()
                .body(employeeRequest)
                .when()
                .patch("https://reqres.in/api/users/2")
                .then()
                .log().all()
                .extract().as(Employee.class);
        Assertions.assertEquals(LocalDate.now(), employeeResponse.getUpdatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }

}
