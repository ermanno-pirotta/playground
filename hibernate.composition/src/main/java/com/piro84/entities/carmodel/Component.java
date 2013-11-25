package com.piro84.entities.carmodel;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;

@Embeddable
public class Component {

	private String name;
	
	@ElementCollection
	private List<SubComponent> mechanicalComponents;
	
	@ElementCollection
	private List<SubComponent> electricalComponents;
	
	@OneToMany
	@CollectionTable(name="T_Component_carModels")
	private List<Car> carModels;
	
	@OneToMany
	@CollectionTable(name="T_Component_superCarModels")
	private List<Car> superCarModels;

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

	public List<Car> getCarModels() {
		return carModels;
	}

	public void setCarModels(List<Car> carModels) {
		this.carModels = carModels;
	}

	public List<Car> getSuperCarModels() {
		return superCarModels;
	}

	public void setSuperCarModels(List<Car> superCarModels) {
		this.superCarModels = superCarModels;
	}

}
