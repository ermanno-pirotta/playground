/**
 * (C) 2013, Trapeze Switzerland GmbH. All rights reserved.
 */
package com.piro84.model1;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * A simple object that models an address field.
 */
@Embeddable
public class Embeddable2 {
	@Column( name = "name" )
	private String name;
	
    @Column( name = "content" )
    private String description;

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
}
