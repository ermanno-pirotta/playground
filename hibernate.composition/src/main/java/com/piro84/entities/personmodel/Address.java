/**
 * (C) 2013, Trapeze Switzerland GmbH. All rights reserved.
 */
package com.piro84.entities.personmodel;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

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
     * not supported, please check the following post on stackoverflow:
     * http://stackoverflow.com/questions/5261659/is-it-possible-for-a-jpa-embeddable-to-contain-embeddable-or-collection-of-embed
     * 
     */
    @Transient
    @ElementCollection
    private List<AddressField> customFields;
        

    /**
     * Mapping a 1-1 relationship from an embeddable to an entity. The foreign key to the houseMate entity will be
     * in the T_Person table.
     */
    @OneToOne
    @JoinColumn(name="id")
	private Housemate houseMate;
    
    /**
     * not supported, please check the following post on stackoverflow:
     * http://stackoverflow.com/questions/5261659/is-it-possible-for-a-jpa-embeddable-to-contain-embeddable-or-collection-of-embed
     * 
     */
    @Transient
    @OneToMany
    @CollectionTable(name="T_adress_housematelist")
    private List<Housemate> houseMateList;
    
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

	public List<AddressField> getCustomFields() {
		return customFields;
	}

	public void setCustomFields(List<AddressField> customFields) {
		this.customFields = customFields;
	}

	public Housemate getHouseMate() {
		return houseMate;
	}

	public void setHouseMate(Housemate houseMate) {
		this.houseMate = houseMate;
	}

	public List<Housemate> getHouseMateList() {
		return houseMateList;
	}

	public void setHouseMateList(List<Housemate> houseMateList) {
		this.houseMateList = houseMateList;
	}


}
