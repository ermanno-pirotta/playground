/**
 * (C) 2013, Trapeze Switzerland GmbH. All rights reserved.
 */
package com.piro84.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * A simple object that models an address field.
 */
@Embeddable
public class AddressField {
    @Column( name = "content" )
    private String content;

    /**
     * Accessor method.
     * 
     * @param text the content to set
     */
    public void setContent( String text ) {
        this.content = text;
    }

    /**
     * Accessor method.
     * 
     * @return the content
     */
    public String getContent() {
        return content;
    }
}
