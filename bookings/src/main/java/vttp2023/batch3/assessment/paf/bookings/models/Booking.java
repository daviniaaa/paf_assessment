package vttp2023.batch3.assessment.paf.bookings.models;

import java.sql.Date;

public class Booking {
    private String resvId;
    private String name;
    private String email;
    private String accId;
    private Date arrivalDate;
    private int duration;
    public Booking() {
    }

    
    public String getResvId() {
        return resvId;
    }


    public void setResvId(String resvId) {
        this.resvId = resvId;
    }


    public String getAccId() {
        return accId;
    }


    public void setAccId(String accId) {
        this.accId = accId;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Date getArrivalDate() {
        return arrivalDate;
    }
    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }
    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }

    
}
