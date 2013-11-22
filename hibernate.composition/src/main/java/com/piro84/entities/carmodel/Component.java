package com.piro84.entities.carmodel;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;

@Embeddable
public class Component {

	private String name;
	
	@ElementCollection
	private List<SubComponent> mechanicalComponents;
	
	@ElementCollection
	private List<SubComponent> electricalComponents;
	

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public List<SubComponent> getMechanicalComponents() {
		return mechanicalComponents;
	}

	public void setMechanicalComponents(List<SubComponent> components) {
		this.mechanicalComponents = components;
	}

	public List<SubComponent> getElectricalComponents() {
		return electricalComponents;
	}

	public void setElectricalComponents(List<SubComponent> electricalComponents) {
		this.electricalComponents = electricalComponents;
	}

}
