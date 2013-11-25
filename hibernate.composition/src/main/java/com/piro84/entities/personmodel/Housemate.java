package com.piro84.entities.personmodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="T_Housemate")
public class Housemate {
	
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
