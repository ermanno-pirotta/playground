/**
 * (C) 2013, Trapeze Switzerland GmbH. All rights reserved.
 */
package com.piro84.entities;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

/**
 * A simple object that models an address. </br> An address can have multiple
 * fields.
 */
@Embeddable
public class Address {

    @Embedded
    private AddressField city;

    @Embedded
    private AddressField country;

    /**
     * Accessor method.
     * 
     * @param c the city to set
     */
    public void setCity( AddressField c ) {
        this.city = c;
    }

    /**
     * Accessor method.
     * 
     * @return the city
     */
    public AddressField getCity() {
        return city;
    }

    /**
     * Accessor method.
     * 
     * @param c the country to set
     */
    public void setCountry( AddressField c ) {
        this.country = c;
    }

    /**
     * Accessor method.
     * 
     * @return the country
     */
    public AddressField getCountry() {
        return country;
    }

}
