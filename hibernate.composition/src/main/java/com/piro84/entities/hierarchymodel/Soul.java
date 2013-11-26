package com.piro84.entities.hierarchymodel;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;

import org.hibernate.annotations.Any;
import org.hibernate.annotations.AnyMetaDef;
import org.hibernate.annotations.MetaValue;

@Embeddable
public class Soul {
	
	/**
	 * The 1-1 relationship has to be modeled with the hibernate specific @any annotation, because the attribute is of the {@link IHuman} type, which is an interface. 
	 * Normal inheritance patterns won't work since the {@link IHuman} does not have an abstract class to be mapped to. This is done to allow multiple inheritance at an interface level (unfortunately gotten via legacy code).
	 * Note that usually this shouldn't be done, and an abstract class should be introduced instead.
	 */
	@Any( metaColumn = @Column( name = "soul_human_type" ), fetch = FetchType.EAGER )
	@AnyMetaDef( idType = "int", metaType = "string", metaValues = {
			@MetaValue( value = "com.piro84.entities.hierarchymodel.Hero", targetEntity = com.piro84.entities.hierarchymodel.Hero.class ),
			@MetaValue( value = "com.piro84.entities.hierarchymodel.Person", targetEntity = com.piro84.entities.hierarchymodel.Hero.class )
	} )
	@JoinColumn( name = "soul_human_id", referencedColumnName = "id", nullable = false )
	private IHuman human;

	public IHuman getHuman() {
		return human;
	}

	public void setHuman(IHuman human) {
		this.human = human;
	}
}
