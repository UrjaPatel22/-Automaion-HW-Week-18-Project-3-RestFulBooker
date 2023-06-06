package com.restful.booker.bookinginfo;

import com.restful.booker.constants.Endpoints;
import com.restful.booker.testbase.TestBase;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class BookingTagsTest extends TestBase {

    @WithTags({@WithTag("bookingfeature:SMOKE"),
            @WithTag("bookingfeature:POSITIVE")})
    @Title("This test will verify if a status code of 200 is returned for GET request")
    @Test
    public void verifyIfTheStatusCodeIs200() {
        SerenityRest.rest()
                .given()
                .when()
                .get()
                .then()
                .statusCode(200)
                .log().all();
    }

    @WithTag("bookingfeature:NEGATIVE")

    @Title("Provide a 415 status code when incorrect HTTP method is used to access resource")
    @Test
    public void invalidMethod() {
        SerenityRest.rest()
                .given()
                .when()
                .post(Endpoints.GET_ALL_BOOKING)
                .then()
                .statusCode(415)
                .log().all();
    }

    @WithTags({@WithTag("bookingfeature:SMOKE"),
            @WithTag("bookingfeature:NEGATIVE")})
    @Title("This test will provide an error code of 404 when user tries to access an invalid resource")
    @Test
    public void inCorrectResource() {
        SerenityRest.rest()
                .given()
                .when()
                .get("/booking123")
                .then()
                .statusCode(404)
                .log().all();
    }
}
