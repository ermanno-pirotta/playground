package com.piro84.entities.carmodel;

import javax.persistence.Embeddable;

@Embeddable
public class SubComponent {

	private String model;

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
}
