package com.piro84.entities.hierarchymodel;

import javax.persistence.Embeddable;

/**
 * Represents the hero powers.
 * @author pie
 *
 */
@Embeddable 
class SuperPower{
	
	private String name;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}