package com.restful.booker.bookinginfo;

import com.restful.booker.testbase.TestBase;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

@RunWith(SerenityRunner.class)
public class BookingCRUDTest extends TestBase {
    static String token;

    static String username ="admin";
    static String password ="password123";

static int bookingId;
    static String firstname;
    static String lastname;
    static int totalprice;
    static boolean depositpaid;
   static  HashMap<String, Object> booking;
    static String additionalneeds;
  @Steps
  BookingSteps bookingSteps;



    @Title("This test will generate token")
    @Test
    public void test001(){
        token = bookingSteps.getToken(username,password);
        System.out.println(token);
    }
    @Title("This will create a new Booking")
    @Test
    public void test002() {
        HashMap<String, Object> booking = new HashMap<String, Object>();
        booking.put("checkin", "2022-01-01");
        booking.put("checkout", "2022-02-01");
        ValidatableResponse response= bookingSteps.createBooking(firstname,lastname,totalprice,depositpaid,booking,additionalneeds);
        bookingId = response.log().all().extract().path("bookingid");

        response.log().all().statusCode(200);
    }

    @Title("Verify if the booking was added to the application")
    @Test
    public void test003() {
        System.out.println("booking id is: " + bookingId);
        ValidatableResponse response = bookingSteps.getBookingInfoById(bookingId);
        response.log().all().statusCode(200);

    }

//    @Title("Update the Booking information and Verify the Updated information")
//    @Test
//    public  void test004(){
//        firstname=firstname + "_Updated";
//        lastname=lastname +"_Updated";
//        HashMap<String, Object> booking = new HashMap<String, Object>();
//        booking.put("checkin", "2022-01-01");
//        booking.put("checkout", "2022-02-01");
//      ValidatableResponse response=  bookingSteps.updateBooking(bookingId,token,firstname, lastname, totalprice, depositpaid, booking, additionalneeds);
//        response.log().all() .statusCode(200);
//
//
//    }

    @Title("This test will update existing booking information and verify the booking has been updated by booking id ")
    @Test
    public void test004(){
        HashMap<String,Object> bookingdates = new HashMap<String, Object>();
        bookingdates.put("checkin","2018-01-01");
        bookingdates.put("checkout","2019-01-01");
        firstname=firstname + "_Updated";
        totalprice =1600;
        depositpaid=false;
        Response response = bookingSteps.updateBookingById(bookingId,token,firstname,lastname,totalprice,depositpaid,bookingdates,additionalneeds);
        response.then().log().all().statusCode(200);
        int totalprice = response.then().extract().path("totalprice");
        System.out.println(totalprice);

    }

    @Title("Get booking")
    @Test
    public void test005() {
        ValidatableResponse response = bookingSteps.getAllBookingInfo().log().all();
                response.statusCode(200);
    }


    @Title("Update the partial Booking information and Verify the Updated information")
    @Test
    public  void test006(){
        firstname=firstname + "_Updated";
        HashMap<String, Object> booking = new HashMap<String, Object>();
        booking.put("checkin", "2022-01-01");
        booking.put("checkout", "2022-02-01");
        ValidatableResponse response=  bookingSteps.updatePartialBooking(bookingId,token,firstname, lastname, totalprice, depositpaid, booking, additionalneeds);
        response .statusCode(200);

    }

    @Title("Delete the Booking and verify if the booking is deleted")
    @Test
    public void test007(){
        bookingSteps.deleteBooking(bookingId,token)
                .statusCode(201);
    }



}
