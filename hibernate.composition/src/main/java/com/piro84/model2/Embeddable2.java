package com.piro84.model2;

import javax.persistence.Embeddable;

@Embeddable
public class Embeddable2 {

	private String model;

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
}
