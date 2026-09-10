package org.apekking;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class CheckoutProcessTest {

    @Test
    public void testCheckoutProcessExecution() {
        String payload = """
                {
                    "orderId": "ORD-999",
                    "customerName": "udil",
                    "item": "Laptop Gaming",
                    "quantity": 1,
                    "amount": 15000000.0
                }
                """;

        given()
            .contentType(ContentType.JSON)
            .body(payload)
        .when()
            .post("/checkout")
        .then()
            .statusCode(201)
            .body("id", notNullValue())
            .body("orderId", equalTo("ORD-999"))
            .body("customerName", equalTo("Ilham Firmansyah"));
    }
}