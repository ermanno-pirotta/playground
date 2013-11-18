/**
 * (C) 2013, Trapeze Switzerland GmbH. All rights reserved.
 */
package com.piro84.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * TODO
 */
@Embeddable
public class AddressField {
    @Column( name = "content" )
    String content;
}
