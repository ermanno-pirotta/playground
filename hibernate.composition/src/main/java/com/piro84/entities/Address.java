/**
 * (C) 2013, Trapeze Switzerland GmbH. All rights reserved.
 */
package com.piro84.entities;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

/**
 * A simple object that models an address. </br> An address can have multiple
 * fields.
 */
@Embeddable
public class Address {
	
	@Column( name = "name" )
	private String name;

    @Embedded
    @AttributeOverrides( { @AttributeOverride( name = "description", column = @Column( name = "city_description", nullable = true ) ),
    	@AttributeOverride( name = "name", column = @Column( name = "city_name", nullable = true ) )} )
    private AddressField city;

    @Embedded
    @AttributeOverrides( { @AttributeOverride( name = "description", column = @Column( name = "country_description", nullable = true ) ),
    	@AttributeOverride( name = "name", column = @Column( name = "country_name", nullable = true ) )} )
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
