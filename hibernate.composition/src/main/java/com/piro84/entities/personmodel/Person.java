package com.piro84.entities.personmodel;

import java.util.List;

import javax.persistence.AssociationOverride;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

/**
 * Simple entity modeling a Person object for testing entity-embeddable relationships.
 * The model can not be used for any purpose other than testing, since it does not model in any way the reality.
 */
@Entity
@Table( name = "T_Person" )
public class Person {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "person_id" )
    Integer id;

    @Column( name = "name", nullable=false )
    String name;

    /**
     * one-to-many with embeddable
     * http://blog.eyallupu.com/2010/06/hibernate-exception-simultaneously.html
     * With the @CollectionTable annotation I can override the default naming for the table which will contain the embeddable
     * With the @JoinColumn within the @CollectionTable, I can override the name of the foreign key from the T_Person_privateAddresses.
     */
    @ElementCollection
    @CollectionTable(name="T_Person_privateAddresses",joinColumns=@JoinColumn(name="OWNER_ID"))
    private List<Address> privateAddresses;

    /**
     * one-to-many with embeddable.
     * By default hibernate creates a second table for the collection, with a name person_homeaddresses.
     */
    @ElementCollection
    private List<Address> homeAddresses;

    /**
     *  one-to-one composition to demostrate mapping with overrides
     * - note the java bean property access in the @AttributeOverride
     * - note that for each attribute in the Address class I need to override the colum definitions
     * - note that the Address#name attribute is also overridden, because it clashes with the Person#name column definition
     * - note that the embeddable colums end up in the T_Person table; it would be possible to group then in a separate table
     * with @SecondaryTable(name="T_Address") if there wasn't a bug opened for this: https://hibernate.atlassian.net/browse/HHH-8021
     * 
     * The @AssociationOverride is used for overriding the column name in case of entity->embeddable->entity 1-1 association
     */
    @Embedded
    @AttributeOverrides( { @AttributeOverride( name = "city.name", column = @Column(name = "bunkerAddress_city_name", nullable = true ) ),
    	@AttributeOverride( name = "city.description", column = @Column(name = "bunkerAddress_city_description", nullable = true )),
    	@AttributeOverride( name = "country.name", column = @Column(name = "bunkerAddress_country_name", nullable = true ) ),
    	@AttributeOverride( name = "country.description", column = @Column( name = "bunkerAddress_country_description", nullable = true )),
    	@AttributeOverride( name = "name", column = @Column(name = "bunkerAddress_address_name", nullable = true )),
    	@AttributeOverride( name = "houseMate.id", column = @Column(name = "bunkerAddress_housemate_id", nullable = true ))} )
    @AssociationOverride(name = "houseMate", joinColumns = @JoinColumn(name = "bunkerAddress_housemate_id"))
    private Address bunkerAddress;
    
    /**
     * second one-to-one composition. The important thing to look at is that the attributes are overridden here as well, since
     * they will end up in the T_Person table as the others.
     */
    @Embedded
    @AttributeOverrides( { @AttributeOverride( name = "city.name", column = @Column(name = "secretAddress_city_name", nullable = true ) ),
    	@AttributeOverride( name = "city.description", column = @Column( name = "secretAddress_city_description", nullable = true )),
    	@AttributeOverride( name = "country.name", column = @Column( name = "secretAddress_country_name", nullable = true ) ),
    	@AttributeOverride( name = "country.description", column = @Column( name = "secretAddress_country_description", nullable = true )),
    	@AttributeOverride( name = "name", column = @Column( name = "secretAddress_address_name", nullable = true ))} )
    @AssociationOverride(name = "houseMate", joinColumns = @JoinColumn(name = "secretAddress_housemate_id"))
    private Address secretAddress;
    
    
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
    public void setPrivateAddresses( List<Address> personAddresses ) {
        this.privateAddresses = personAddresses;
    }

    /**
     * Accessor method.
     * 
     * @return the addresses
     */
    public List<Address> getPrivateAdresses() {
        return privateAddresses;
    }

    /**
     * Accessor method.
     * 
     * @param addresses the homeAddresses to set
     */
    public void setHomeAddresses( List<Address> addresses ) {
        this.homeAddresses = addresses;
    }

    /**
     * Accessor method.
     * 
     * @return the homeAddresses
     */
    public List<Address> getHomeAddresses() {
        return homeAddresses;
    }

	public Address getSecretAddress() {
		return secretAddress;
	}

	public void setSecretAddress(Address secretAddress) {
		this.secretAddress = secretAddress;
	}

	public Address getBunkerAddress() {
		return bunkerAddress;
	}

	public void setBunkerAddress(Address bunkerAddress) {
		this.bunkerAddress = bunkerAddress;
	}
}
