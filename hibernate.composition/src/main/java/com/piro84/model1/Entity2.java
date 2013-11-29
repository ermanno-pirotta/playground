package com.piro84.model1;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="T_Model1_Entity2")
@Table(name="T_Model1_Entity2")
public class Entity2 {
	
	@Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column
	private
    Integer id;

    @Column( name = "name", nullable=false )
	private
    String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
    
}
