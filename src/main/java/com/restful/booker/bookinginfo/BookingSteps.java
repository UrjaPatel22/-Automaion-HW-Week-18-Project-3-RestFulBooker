package com.restful.booker.bookinginfo;

import com.restful.booker.constants.Endpoints;
import com.restful.booker.model.AuthPojo;
import com.restful.booker.model.BookingPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class BookingSteps {




 public   static String token;


    @Step("Get token username :{0}, password:{1}")
    public String getToken(String username, String password){
        // set variables username and password
        AuthPojo authPojo = AuthPojo.getAuthPojo(username,password);
        //this will generate a token
        Response response = SerenityRest.given().log().all()
                .header("Content-Type","application/json")
                .body(authPojo)// pass the variable in the body
                .when()
                .post("https://restful-booker.herokuapp.com/auth")//create a Authorisation
                .then().extract().response();
        String token= response.jsonPath().get("token").toString();
        return token;
    }

    @Step("Create a new Booking  with Firstname : {0}, lastName : {1}, totalPrice : {2}, DepositPaid : {3},  booking : {4},additionlneeds: {5}")
    public ValidatableResponse createBooking(String firstName, String lastName, int totalprice, boolean depositpaid,
                                             HashMap<String, Object> booking, String additionalneeds) {


        BookingPojo bookingPojo = BookingPojo.getBookingPojo(firstName, lastName, totalprice, depositpaid, booking, additionalneeds);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .body(bookingPojo)
                .when()
                .post().then();
    }


    @Step("Getting booking information with bookingId: {0}")
    public ValidatableResponse getBookingInfoById(int bookingId) {
        //find the new record by id
        return SerenityRest.given().log().all()
                .header("Accept", "application/json")
                //.contentType(ContentType.JSON)
                .pathParam("bookingId", bookingId)
                .when()
                .get(Endpoints.GET_SINGLE_BOOKING_BY_ID)
                .then();
    }

//    @Step("Update a new Booking  with bookingId:{0},Firstname : {1}, lastName : {2}, totalPrice : {3}, DepositPaid : {4},  booking : {5},additionlneeds: {6}")
//    public ValidatableResponse updateBooking(int bookingId,String token,String firstName, String lastName, int totalprice, boolean depositpaid,
//                                             HashMap<String, Object> booking, String additionalneeds) {
//
//
//
//        BookingPojo bookingPojo = BookingPojo.getBookingPojo(firstName, lastName, totalprice, depositpaid, booking, additionalneeds);
//
//        return SerenityRest.given().log().all()
//                .contentType(ContentType.JSON)
//                .header("Accept", "application/json")
//                .header("Cookie","token="+token)
//                //.pathParam("bookingId", bookingId)
//                .body(bookingPojo)
//                .when()
//                .put("/"+bookingId)
//                .then();
//    }


    @Step("Update Booking information ")
    public Response updateBookingById(int bookingId,String token,String firstname, String lastname, int totalprice, boolean depositpaid, HashMap<String,Object> bookingdates,String additionalneeds){
        BookingPojo bookingPojo = BookingPojo.getBookingPojo(firstname, lastname, totalprice, depositpaid, bookingdates, additionalneeds);
        return SerenityRest.given().log().all()
                .when()
                .header("Content-Type","application/json")
                .header("Accept","application/json")
                .header("Cookie","token="+token)
                .body(bookingPojo)
                .when()
                .put("/"+bookingId);
    }


    @Step("Getting all the booking information")
    public ValidatableResponse getAllBookingInfo() {
        return SerenityRest.given().log().all()
                .when()
                .get()
                .then();
    }



    @Step("Update a new Booking  with bookingId:{0},Firstname : {1}, lastName : {2}, totalPrice : {3}, DepositPaid : {4},  booking : {5},additionlneeds: {6}")
    public ValidatableResponse updatePartialBooking(int bookingId,String token,String firstName, String lastName, int totalprice, boolean depositpaid,
                                             HashMap<String, Object> booking, String additionalneeds) {



        BookingPojo bookingPojo = BookingPojo.getBookingPojo(firstName, lastName, totalprice, depositpaid, booking, additionalneeds);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .header("Cookie","token="+token)
                .pathParam("bookingId", bookingId)
                .body(bookingPojo)
                .when()
                .patch(Endpoints.UPDATE_BOOKING_BY_ID)
                .then();
    }

    @Step("Deleting Booking information with BookingId: {0}")
    public ValidatableResponse deleteBooking(int bookingId,String token){
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .header("Cookie","token="+token)
                .pathParam("bookingId", bookingId)
                .when()
                .delete(Endpoints.DELETE_BOOKING_BY_ID)
                .then();
    }


}