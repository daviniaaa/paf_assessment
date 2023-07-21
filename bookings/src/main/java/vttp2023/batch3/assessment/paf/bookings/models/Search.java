package vttp2023.batch3.assessment.paf.bookings.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class Search {
    @NotNull @NotEmpty
    private String country;
    @Min(value = 1) @Max(value = 10)
    private int person;
    @Min(value = 1) @Max(value = 10000)
    private double priceMin;
    @Min(value = 1) @Max(value = 10000)
    private double priceMax;
    
    public Search() {
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public int getPerson() {
        return person;
    }
    public void setPerson(int person) {
        this.person = person;
    }
    public double getPriceMin() {
        return priceMin;
    }
    public void setPriceMin(double priceMin) {
        this.priceMin = priceMin;
    }
    public double getPriceMax() {
        return priceMax;
    }
    public void setPriceMax(double priceMax) {
        this.priceMax = priceMax;
    }

    
}
