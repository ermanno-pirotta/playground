package com.piro84.entities.hierarchymodel;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Any;
import org.hibernate.annotations.AnyMetaDef;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.MetaValue;

/**
 * Sample class to demonstrate the usage of the @Any annotation in case of not truly polimorphic types. 
 * Please note that this is done to allow multiple inheritance via interfaces, which is conceptually wrong since polyphormism is intended for modeling behaviour and NOT data. 
 * In this class is also demonstrated the use of @Access( AccessType.PROPERTY ), which allows the developer to annotate getter methods instead of fields.
 * @author pie
 *
 */
@Entity(name="T_HierarchyModel_Entity3")
@Table(name="T_HierarchyModel_Entity3")
@Access( AccessType.PROPERTY )
public class Entity3 {

	private Integer id;
	
	private IAbstractEntity abstractEntity1;
	
	private List<IAbstractEntity> abstractEntityList1;

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	@Column( name = "d" )
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * The 1-1 relationship has to be modeled with the hibernate specific @any annotation, because the attribute is of the {@link IAbstractEntity} type, which is an interface. 
	 * Normal inheritance patterns won't work since the {@link IAbstractEntity} does not have an abstract class to be mapped to. This is done to allow multiple inheritance at an interface level (unfortunately gotten via legacy code).
	 * Note that usually this shouldn't be done, and an abstract class should be introduced instead.
	 */
	@Any( metaColumn = @Column( name = "abstract_type" ), fetch = FetchType.EAGER )
	@AnyMetaDef( idType = "int", metaType = "string", metaValues = {
			@MetaValue( value = "com.piro84.entities.hierarchymodel.Entity2", targetEntity = com.piro84.entities.hierarchymodel.Entity2.class ),
			@MetaValue( value = "com.piro84.entities.hierarchymodel.Entity1", targetEntity = com.piro84.entities.hierarchymodel.Entity1.class )
	} )
	@JoinColumn( name = "abstract_entity_id", referencedColumnName = "id", nullable = false )
	public IAbstractEntity getAbstractEntity1() {
		return abstractEntity1;
	}

	public void setAbstractEntity1(IAbstractEntity entity) {
		this.abstractEntity1 = entity;
	}

	@Transient
	@ManyToAny( metaColumn = @Column( name = "abstract_type" ), fetch = FetchType.EAGER )
	@AnyMetaDef( idType = "long", metaType = "string", metaValues = {
			@MetaValue( value = "com.piro84.entities.hierarchymodel.Entity2", targetEntity = com.piro84.entities.hierarchymodel.Entity2.class ),
			@MetaValue( value = "com.piro84.entities.hierarchymodel.Entity1", targetEntity = com.piro84.entities.hierarchymodel.Entity1.class )
	} )
	@JoinTable(name = "T_Entity1InHierarchy_children",joinColumns = @JoinColumn( name = "abstract_entity" ), inverseJoinColumns = @JoinColumn( name = "id" ) )
	public List<IAbstractEntity> getAbstractEntityList1() {
		return abstractEntityList1;
	}

	public void setAbstractEntityList1(List<IAbstractEntity> list) {
		this.abstractEntityList1 = list;
	}
	
}
