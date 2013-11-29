package com.piro84.model2;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Embeddable
public class Embeddable1 {

	private String name;
	
	@ElementCollection
	@CollectionTable(name="T_Model2_Embeddable1_embeddableList1",joinColumns=@JoinColumn(name="OWNER_ID"))
	private List<Embeddable2> embeddableList1;
	
	@ElementCollection
	@CollectionTable(name="T_Model2_Embeddable1_embeddableList2",joinColumns=@JoinColumn(name="OWNER_ID"))
	private List<Embeddable2> embeddableList2;
	
	@OneToMany
	@CollectionTable(name="T_Model2_Embeddable1_Entity1_List1")
	private List<Entity1> entityList1;
	
	@OneToMany
	@CollectionTable(name="T_Model2_Embeddable1_Entity1_List2")
	private List<Entity1> entityList2;

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public List<Embeddable2> geEmbeddableList1() {
		return embeddableList1;
	}

	public void setEmbeddableList1(List<Embeddable2> components) {
		this.embeddableList1 = components;
	}

	public List<Embeddable2> getEmbeddableList2() {
		return embeddableList2;
	}

	public void setEmbeddableList2(List<Embeddable2> electricalComponents) {
		this.embeddableList2 = electricalComponents;
	}

	public List<Entity1> getEntityList1() {
		return entityList1;
	}

	public void setEntityList1(List<Entity1> list) {
		this.entityList1 = list;
	}

	public List<Entity1> getEntityList2() {
		return entityList2;
	}

	public void setEntityList2(List<Entity1> list) {
		this.entityList2 = list;
	}

}
