package com.piro84.model2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="T_Model2_Entity2")
@Table(name="T_Model2_Entity2")
public class Entity2 {

	@Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "id" )
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
