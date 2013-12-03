/**
 * (C) 2013, Trapeze Switzerland GmbH. All rights reserved.
 */
package com.piro84.model1;

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
public class Embeddable1 {
	
	@Column( name = "name" )
	private String name;

	/**
	 * The following @AttributeOverrides could be moved to the {@link Entity1} class, on the array of Embeddable2 attributes. 
	 * In this way though, we can keep the annotation for the arrays of embeddable much cleaner in the {@link Entity1}. 
	 */
    @Embedded
    @AttributeOverrides( { @AttributeOverride( name = "description", column = @Column( name = "city_description", nullable = true ) ),
    	@AttributeOverride( name = "name", column = @Column( name = "city_name", nullable = true ) )} )
    private Embeddable2 embeddable1;

    @Embedded
    @AttributeOverrides( { @AttributeOverride( name = "description", column = @Column( name = "country_description", nullable = true ) ),
    	@AttributeOverride( name = "name", column = @Column( name = "country_name", nullable = true ) )} )
    private Embeddable2 embeddable2;
    
    
    /**
     * not supported, please check the following post on stackoverflow:
     * http://stackoverflow.com/questions/5261659/is-it-possible-for-a-jpa-embeddable-to-contain-embeddable-or-collection-of-embed
     * 
     */
    @Transient
    @ElementCollection
    private List<Embeddable2> embeddableList1;
        

    /**
     * Mapping a 1-1 relationship from an embeddable to an entity. The foreign key to the entity2 entity will be
     * in the T_Entity1 table.
     */
    @Transient
    @OneToOne
    @JoinColumn(name="id")
	private Entity2 entity1;
    
    /**
     * not supported, please check the following post on stackoverflow:
     * http://stackoverflow.com/questions/5261659/is-it-possible-for-a-jpa-embeddable-to-contain-embeddable-or-collection-of-embed
     * 
     */
    @Transient
    @OneToMany
    @CollectionTable(name="T_adress_housematelist")
    private List<Entity2> entityList1;
    
    /**
     * Accessor method.
     * 
     * @param c the city to set
     */
    public void setEmbeddable1( Embeddable2 c ) {
        this.embeddable1 = c;
    }

    /**
     * Accessor method.
     * 
     * @return the city
     */
    public Embeddable2 getEmbeddable1() {
        return embeddable1;
    }

    /**
     * Accessor method.
     * 
     * @param c the country to set
     */
    public void setEmbeddable2( Embeddable2 c ) {
        this.embeddable2 = c;
    }

    /**
     * Accessor method.
     * 
     * @return the country
     */
    public Embeddable2 getEmbeddable2() {
        return embeddable2;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Embeddable2> getEmbeddableList1() {
		return embeddableList1;
	}

	public void setEmbeddableList1(List<Embeddable2> customFields) {
		this.embeddableList1 = customFields;
	}

	public Entity2 getEntity1() {
		return entity1;
	}

	public void setEntity1(Entity2 houseMate) {
		this.entity1 = houseMate;
	}

	public List<Entity2> getEntityList1() {
		return entityList1;
	}

	public void setEntityList1(List<Entity2> entityList1) {
		this.entityList1 = entityList1;
	}


}
