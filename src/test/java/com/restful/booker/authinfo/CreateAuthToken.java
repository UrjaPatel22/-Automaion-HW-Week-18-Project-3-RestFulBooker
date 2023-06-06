package com.restful.booker.authinfo;

import com.restful.booker.model.AuthPojo;
import com.restful.booker.testbase.TestBaseAuth;
import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import org.junit.Test;

public class CreateAuthToken extends TestBaseAuth {

    @Title("Creating new auth token to access to PUT and DELETE booking requests")
    @Test
    public void createNewAuthToken() {
        AuthPojo authPojo = new AuthPojo();
        authPojo.setUsername("admin");
        authPojo.setPassword("password123");
        String token = SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(authPojo)
                .when()
                .post()
                .then()
                .extract()
                .path("token");
        System.out.println(token);
    }
}
