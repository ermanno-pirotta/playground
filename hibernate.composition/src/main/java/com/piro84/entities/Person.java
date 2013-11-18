package com.piro84.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@javax.persistence.Entity
@Table( name = "PERSON" )
public class Person {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "PERSON_ID" )
    Integer id;

    @Column( name = "NAME" )
    String name;

    @ElementCollection
    List<Address> addresses;

    public Integer getId() {
        return id;
    }

    public void setId( Integer postId ) {
        this.id = postId;
    }

    public String getName() {
        return name;
    }

    public void setName( String title ) {
        this.name = title;
    }
}
