package com.piro84.entities.hierarchymodel;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="T_GhostFamily")
public class GhostFamily {

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	@Column( name = "person_id" )
	private Integer id;
	
	@Embedded
	private Soul familySoul;
	
	/**
	 * Case for testing @entity->@embeddable[]->@Any relationship mapping.
	 */
	@ElementCollection
	private List<Soul> otherSouls;
	
	/**
	 * This is marked as @Transient otherwise the hibernate mapping will result in
	 * org.hibernate.MappingException: Repeated column in mapping for entity: com.piro84.entities.hierarchymodel.GhostFamily column: soul_human_type (should be mapped with insert="false" update="false")
	 * because @AttributeOverride and @AssociationOverride does not work with @Any attributes 
	 */
	@Transient
	@Embedded
	private Soul fatherSoul;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Soul getFamilySoul() {
		return familySoul;
	}

	public void setFamilySoul(Soul familySoul) {
		this.familySoul = familySoul;
	}

	public Soul getFatherSoul() {
		return fatherSoul;
	}

	public void setFatherSoul(Soul fatherSoul) {
		this.fatherSoul = fatherSoul;
	}

	public List<Soul> getOtherSouls() {
		return otherSouls;
	}

	public void setOtherSouls(List<Soul> otherSouls) {
		this.otherSouls = otherSouls;
	}
}
