package com.piro84.entities.hierarchymodel;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;

import org.hibernate.annotations.Any;
import org.hibernate.annotations.AnyMetaDef;
import org.hibernate.annotations.MetaValue;

@Embeddable
public class Embeddable1 {
	
	/**
	 * The 1-1 relationship has to be modeled with the hibernate specific @any annotation, because the attribute is of the {@link IAbstractEntity} type, which is an interface. 
	 * Normal inheritance patterns won't work since the {@link IAbstractEntity} does not have an abstract class to be mapped to. This is done to allow multiple inheritance at an interface level (unfortunately gotten via legacy code).
	 * Note that usually this shouldn't be done, and an abstract class should be introduced instead.
	 */
	@Any( metaColumn = @Column( name = "abstract_entity_type" ), fetch = FetchType.EAGER )
	@AnyMetaDef( idType = "int", metaType = "string", metaValues = {
			@MetaValue( value = "com.piro84.entities.hierarchymodel.Entity2", targetEntity = com.piro84.entities.hierarchymodel.Entity2.class ),
			@MetaValue( value = "com.piro84.entities.hierarchymodel.Entity1", targetEntity = com.piro84.entities.hierarchymodel.Entity1.class )
	} )
	@JoinColumn( name = "soul_human_id", referencedColumnName = "id", nullable = false )
	private IAbstractEntity abstractEntity1;

	public IAbstractEntity getAbstractEntity1() {
		return abstractEntity1;
	}

	public void setAbstractEntity(IAbstractEntity entity) {
		this.abstractEntity1 = entity;
	}
}
