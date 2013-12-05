package com.piro84.model1;

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
 * Simple entity for testing entity-embeddable relationships.
 * The model can not be used for any purpose other than testing, since it does not model in any way the reality.
 */
@Entity( name = "T_Model1_Entity1" )
@Table( name = "T_Model1_Entity1" )
public class Entity1 {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "id" )
    Integer id;

    @Column( name = "name", nullable=false )
    String name;

    /**
     * one-to-many with embeddable
     * http://blog.eyallupu.com/2010/06/hibernate-exception-simultaneously.html
     * With the @CollectionTable annotation I can override the default naming for the table which will contain the embeddable
     * With the @JoinColumn within the @CollectionTable, I can override the name of the foreign key from the T_Model1_Entity1_privateAddresses.
     */
    @ElementCollection
    @CollectionTable(name="T_Model1_Entity1_embeddableList1",joinColumns=@JoinColumn(name="OWNER_ID"))
    private List<Embeddable1> embeddableList1;

    /**
     * one-to-many with embeddable.
     * By default hibernate creates a second table for the collection, with a name entity1_embeddablelist2.
     */
    @ElementCollection
    @CollectionTable(name="T_Model1_Entity1_embeddableList2",joinColumns=@JoinColumn(name="OWNER_ID"))
    private List<Embeddable1> embeddableList2;

    /**
     *  one-to-one composition to demostrate mapping with overrides
     * - note the java bean property access in the @AttributeOverride
     * - note that for each attribute in the Address class I need to override the colum definitions
     * - note that the Address#name attribute is also overridden, because it clashes with the Person#name column definition
     * - note that the embeddable colums end up in the T_Model1_Entity1 table; it would be possible to group then in a separate table
     * with @SecondaryTable(name="T_Address") if there wasn't a bug opened for this: https://hibernate.atlassian.net/browse/HHH-8021
     * 
     * The @AssociationOverride is used for overriding the column name in case of entity->embeddable->entity 1-1 association
     */
    @Embedded
    @AttributeOverrides( { @AttributeOverride( name = "embeddable1.name", column = @Column(name = "embeddable1_city_name", nullable = true ) ),
    	@AttributeOverride( name = "embeddable1.description", column = @Column( name = "embeddable1_city_description", nullable = true )),
    	@AttributeOverride( name = "embeddable2.name", column = @Column( name = "embeddable1_country_name", nullable = true ) ),
    	@AttributeOverride( name = "embeddable2.description", column = @Column( name = "embeddable1_country_description", nullable = true )),
    	@AttributeOverride( name = "embeddable1.embeddable.name", column = @Column( name = "embeddable1_embeddable2_embeddable_name1", nullable = true )),
    	@AttributeOverride( name = "embeddable2.embeddable.name", column = @Column( name = "embeddable1_embeddable2_embeddable_name2", nullable = true )),
    	@AttributeOverride( name = "name", column = @Column( name = "embeddable1_address_name", nullable = true )),
    	@AttributeOverride( name = "entity1.id", column = @Column(name = "bunkerAddress_housemate_id", nullable = true ))} )
    @AssociationOverride(name = "entity1", joinColumns = @JoinColumn(name = "embeddable1_entity1_id"))
    private Embeddable1 embeddable1;
    
    /**
     * second one-to-one composition. The important thing to look at is that the attributes are overridden here as well, since
     * they will end up in the T_Model1_Entity1 table as the others.
     */
    @Embedded
    @AttributeOverrides( { @AttributeOverride( name = "embeddable1.name", column = @Column(name = "embeddable2_city_name", nullable = true ) ),
    	@AttributeOverride( name = "embeddable1.description", column = @Column(name = "embeddable2_city_description", nullable = true )),
    	@AttributeOverride( name = "embeddable2.name", column = @Column(name = "embeddable2_country_name", nullable = true ) ),
    	@AttributeOverride( name = "embeddable2.description", column = @Column( name = "embeddable2_country_description", nullable = true )),
    	@AttributeOverride( name = "embeddable2.embeddable.name", column = @Column( name = "embeddable2_embeddable2_embeddable_name1", nullable = true )),
    	@AttributeOverride( name = "embeddable2.embeddable.name", column = @Column( name = "embeddable2_embeddable2_embeddable_name2", nullable = true )),
    	@AttributeOverride( name = "name", column = @Column(name = "embeddable2_address_name", nullable = true ))} )
    @AssociationOverride(name = "entity1", joinColumns = @JoinColumn(name = "embeddable2_entity1_id"))
    private Embeddable1 embeddable2;
    
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
     * @param list the addresses to set
     */
    public void setEmbeddableList1( List<Embeddable1> list ) {
        this.embeddableList1 = list;
    }

    /**
     * Accessor method.
     * 
     * @return the addresses
     */
    public List<Embeddable1> getEmbeddableList1() {
        return embeddableList1;
    }

    /**
     * Accessor method.
     * 
     * @param list the homeAddresses to set
     */
    public void setEmbeddableList2( List<Embeddable1> list ) {
        this.embeddableList2 = list;
    }

    /**
     * Accessor method.
     * 
     * @return the homeAddresses
     */
    public List<Embeddable1> getEmbeddableList2() {
        return embeddableList2;
    }

	public Embeddable1 getEmbeddable1() {
		return embeddable1;
	}

	public void setEmbeddable1(Embeddable1 embeddable) {
		this.embeddable1 = embeddable;
	}

	public Embeddable1 getEmbeddable2() {
		return embeddable2;
	}

	public void setEmbeddable2(Embeddable1 embeddable) {
		this.embeddable2 = embeddable;
	}
}
