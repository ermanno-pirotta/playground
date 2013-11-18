/**
 * (C) 2013, Trapeze Switzerland GmbH. All rights reserved.
 */
package com.piro84.entities;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

/**
 * TODO
 */
@Embeddable
public class Address {

    @Embedded
    AddressField city;

    @Embedded
    AddressField country;

}
