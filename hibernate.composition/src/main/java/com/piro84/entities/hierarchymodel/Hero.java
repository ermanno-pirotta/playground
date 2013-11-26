package com.piro84.entities.hierarchymodel;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
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
@Entity
@Table(name="T_HeroInHierarchy")
public class Hero implements IHero {
	
	@Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "hero_id" )
	private Integer id;
	
	@Column
	private String secretName;
	
	@Embedded
	@AttributeOverrides( { @AttributeOverride( name = "name", column = @Column(name = "flyPower_name", nullable = true ) )})
	private SuperPower flyPower;
	
	@Embedded
	@AttributeOverrides( { @AttributeOverride( name = "name", column = @Column(name = "speedPower_name", nullable = true ) ),
		@AttributeOverride( name = "strongerHuman_id", column = @Column(name = "test_id", nullable = true ) )})
	private SuperPower speedPower;
	
	@ElementCollection
	private List<SuperPower> otherPowers;

	@Override
	public String getSecretName() {
		return secretName;
	}

	public void setSecretName(String secretName) {
		this.secretName = secretName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	

	public SuperPower getFlyPower() {
		return flyPower;
	}

	public void setFlyPower(SuperPower flyPower) {
		this.flyPower = flyPower;
	}


	public SuperPower getSpeedPower() {
		return speedPower;
	}

	public void setSpeedPower(SuperPower speedPower) {
		this.speedPower = speedPower;
	}


	public List<SuperPower> getOtherPowers() {
		return otherPowers;
	}

	public void setOtherPowers(List<SuperPower> otherPowers) {
		this.otherPowers = otherPowers;
	}
}
