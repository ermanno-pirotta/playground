package com.piro84.entities.hierarchymodel;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The peculiarity of this class is that it uses an inner class as component (@Embeddable).
 * @author pie
 *
 */
@Entity(name="T_HierarchyModel_Entity2")
@Table(name="T_HierarchyModel_Entity2")
public class Entity2 implements IEntity2 {
	
	@Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "id" )
	private Integer id;
	
	@Column
	private String name;
	
	@Embedded
	@AttributeOverrides( { @AttributeOverride( name = "name", column = @Column(name = "embeddable1_name", nullable = true ) )})
	private Embeddable2 embeddable1;
	
	@Embedded
	@AttributeOverrides( { @AttributeOverride( name = "name", column = @Column(name = "embeddable2_name", nullable = true ) )})
	private Embeddable2 embeddable2;
	
	@ElementCollection
	@CollectionTable(name="T_HierarchyModel_Entity2_embeddableList1")
	private List<Embeddable2> embeddableList1;

	@Override
	public String getName() {
		return name;
	}

	public void setName(String secretName) {
		this.name = secretName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	

	public Embeddable2 getEmbeddable1() {
		return embeddable1;
	}

	public void setEmbeddable1(Embeddable2 embeddable) {
		this.embeddable1 = embeddable;
	}


	public Embeddable2 getEmbeddable2() {
		return embeddable2;
	}

	public void setEmbeddable2(Embeddable2 embeddable) {
		this.embeddable2 = embeddable;
	}


	public List<Embeddable2> getEmbeddableList1() {
		return embeddableList1;
	}

	public void setEmbeddableList1(List<Embeddable2> list) {
		this.embeddableList1 = list;
	}
}
