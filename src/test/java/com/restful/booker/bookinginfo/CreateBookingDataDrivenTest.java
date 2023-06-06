package com.restful.booker.bookinginfo;


import com.restful.booker.testbase.TestBase;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;

import java.util.HashMap;

//@Concurrent(threads = "4X")
//@UseTestDataFrom("src/test/java/resources/testdata/bookinginfo.csv")
////@RunWith(SerenityParameterizedRunner.class)

public class CreateBookingDataDrivenTest extends TestBase {

    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private String checkin;
    private String checkout;
    private String additionalneeds;
    @Steps
    BookingSteps bookingSteps;
    @Title("Data driven test for adding multiple bookings to the application")
    @Test
    public void createMultipleBooking(){
        HashMap<String, Object> booking = new HashMap<String, Object>();
        booking.put("checkin", "2022-01-01");
        booking.put("checkout", "2022-02-01");
        bookingSteps.createBooking(firstname,lastname,totalprice,depositpaid,booking,additionalneeds).statusCode(200);
    }
}


