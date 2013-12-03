package com.piro84.entities.hierarchymodel;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity(name="T_HierarchyModel_Entity4")
@Table(name="T_HierarchyModel_Entity4")
public class Entity4 {

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	@Column( name = "id" )
	private Integer id;
	
	@Embedded
	private Embeddable1 embeddable1;
	
	/**
	 * Case for testing @entity->@embeddable[]->@Any relationship mapping.
	 */
	@ElementCollection
	@CollectionTable(name="T_HierarchyModel_Entity4_embeddableList1")
	private List<Embeddable1> embeddableList1;
	
	/**
	 * This is marked as @Transient otherwise the hibernate mapping will result in
	 * org.hibernate.MappingException: Repeated column in mapping for entity: com.piro84.entities.hierarchymodel.GhostFamily column: soul_human_type (should be mapped with insert="false" update="false")
	 * because @AttributeOverride and @AssociationOverride does not work with @Any attributes 
	 */
	@Transient
	@Embedded
	private Embeddable1 embeddable2;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Embeddable1 getEmbeddable1() {
		return embeddable1;
	}

	public void setEmbeddable1(Embeddable1 embeddable) {
		this.embeddable1 = embeddable;
	}

	public Embeddable1 getEmbeddable2() {
		return embeddable2;
	}

	public void setEmbeddable2(Embeddable1 embeddable) {
		this.embeddable2 = embeddable;
	}

	public List<Embeddable1> getEmbeddableList1() {
		return embeddableList1;
	}

	public void setEmbeddableList1(List<Embeddable1> list) {
		this.embeddableList1 = list;
	}
}
