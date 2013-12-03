package com.piro84.model2;

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
 * This is done to test the mapping cases which could not be tested in the model1 (i.e. entity->embeddable->embeddable[]). 
 * @author pie
 *
 */
@Entity(name="T_Model2_Entity1")
@Table(name="T_Model2_Entity1")
public class Entity1 {

	@Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "id" )
	private Integer id;
	
	@Embedded
	 @AttributeOverrides( { @AttributeOverride( name = "name", column = @Column(name = "embeddable_name", nullable = true )) })
	private Embeddable1 embeddable;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Embeddable1 getEmbeddable() {
		return embeddable;
	}

	public void setEmbeddable(Embeddable1 emb) {
		this.embeddable = emb;
	}

}
