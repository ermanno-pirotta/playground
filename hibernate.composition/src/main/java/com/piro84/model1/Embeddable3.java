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
public class Embeddable3 {
	@Column( name = "name" )
	private String name;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
