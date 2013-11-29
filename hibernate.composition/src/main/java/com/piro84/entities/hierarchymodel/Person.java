package com.piro84.entities.hierarchymodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The entity is named to avoid conflicts with the {@link com.piro84.model1.Entity1} entity.
 * @author pie
 *
 */
@Entity(name="PersonInHierarchy")
@Table(name="T_Entity1InHierarchy")
public class Person implements IPerson {

	@Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "person_id" )
	private Integer id;
	
	@Column(name="name")
	private String name;


	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
