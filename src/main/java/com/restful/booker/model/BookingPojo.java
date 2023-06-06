package com.restful.booker.model;

import com.restful.booker.utils.TestUtils;

import java.util.HashMap;

public class BookingPojo {
    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;



    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }

    public boolean isDepositpaid() {
        return depositpaid;
    }

    public void setDepositpaid(boolean depositpaid) {
        this.depositpaid = depositpaid;
    }

    public HashMap<String, Object> getBookingdates() {
        return bookingdates;
    }

    public void setBookingdates(HashMap<String, Object> bookingdates) {
        this.bookingdates = bookingdates;
    }

    public String getAdditionalneeds() {
        return additionalneeds;
    }

    public void setAdditionalneeds(String additionalneeds) {
        this.additionalneeds = additionalneeds;
    }

    private HashMap<String, Object> bookingdates;
    private String additionalneeds;

    public static BookingPojo getBookingPojo(String firstname, String lastname, int totalprice, boolean depositpaid,  HashMap<String, Object> booking,String additionalneeds)
    {
        BookingPojo bookingPojo = new BookingPojo();

        bookingPojo.setFirstname("John" + TestUtils.getRandomValue());
        bookingPojo.setLastname("Smith" + TestUtils.getRandomValue());
        bookingPojo.setTotalprice(totalprice);
        bookingPojo.setDepositpaid(depositpaid);
        bookingPojo.setBookingdates(booking);
        bookingPojo.setAdditionalneeds("Access to Gym");
        return  bookingPojo;
    }
}
