/**
 * (C) 2013, Trapeze Switzerland GmbH. All rights reserved.
 */
package com.piro84.model1;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

/**
 * A simple object that models an address field.
 */
@Embeddable
public class Embeddable2 {
	@Column( name = "name" )
	private String name;
	
    @Column( name = "content" )
    private String description;
    
    @Embedded
    @AttributeOverrides( { @AttributeOverride( name = "name", column = @Column( name = "embeddable_name", nullable = true ) )} )
    private Embeddable3 embeddable;

    /**
     * Accessor method.
     * 
     * @param text the content to set
     */
    public void setDescription( String text ) {
        this.description = text;
    }

    /**
     * Accessor method.
     * 
     * @return the content
     */
    public String getDescription() {
        return description;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Embeddable3 getEmbeddable() {
		return embeddable;
	}

	public void setEmbeddable(Embeddable3 embeddable) {
		this.embeddable = embeddable;
	}
}
