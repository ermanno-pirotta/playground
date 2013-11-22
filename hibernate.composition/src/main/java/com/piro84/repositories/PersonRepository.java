/**
 * (C) 2013, Trapeze Switzerland GmbH. All rights reserved.
 */
package com.piro84.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.piro84.entities.personmodel.Person;

/**
 * Repository for persisting {@link Person} entities.
 */
public interface PersonRepository extends JpaRepository<Person, Integer> {

}
