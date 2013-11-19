package com.piro84.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Simple entity modeling a Person object.
 */
@javax.persistence.Entity
@Table( name = "PERSON" )
public class Person {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "PERSON_ID" )
    Integer id;

    @Column( name = "NAME" )
    String name;

    @ElementCollection
    private List<Address> addresses;

    /**
     * Accessor method.
     * 
     * @return the database identifier for this person
     */
    public Integer getId() {
        return id;
    }

    /**
     * Accessor method.
     * 
     * @param personId the database identifier for the person
     */
    public void setId( Integer personId ) {
        this.id = personId;
    }

    /**
     * Accessor method.
     * 
     * @return the name of the person
     */
    public String getName() {
        return name;
    }

    /**
     * Accessor method.
     * 
     * @param personName
     */
    public void setName( String personName ) {
        this.name = personName;
    }

    /**
     * Accessor method.
     * 
     * @param personAddresses the addresses to set
     */
    public void setAddresses( List<Address> personAddresses ) {
        this.addresses = personAddresses;
    }

    /**
     * Accessor method.
     * 
     * @return the addresses
     */
    public List<Address> getAddresses() {
        return addresses;
    }
}
