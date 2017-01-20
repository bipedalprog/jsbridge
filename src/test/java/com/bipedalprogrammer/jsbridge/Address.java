package com.bipedalprogrammer.jsbridge;

/**
 * Created by bipedal on 1/17/17.
 */
public abstract class Address {
    private String country;

    public Address(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
