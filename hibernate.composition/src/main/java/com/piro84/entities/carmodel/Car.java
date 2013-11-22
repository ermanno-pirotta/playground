package com.piro84.entities.carmodel;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Models a car. A car has some components which are made by sub-components. 
 * This is done to test the mapping cases which could not be tested in the person model (i.e. entity->embeddable->embeddable[]). 
 * @author pie
 *
 */
@Entity
@Table(name="T_Car")
public class Car {

	@Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "car_id" )
	private
    Integer id;
	
	@Embedded
	@AttributeOverrides( { @AttributeOverride( name = "name", column = @Column(name = "steer_name", nullable = true )) })
	private Component steer;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Component getSteer() {
		return steer;
	}

	public void setSteer(Component steer) {
		this.steer = steer;
	}

}
