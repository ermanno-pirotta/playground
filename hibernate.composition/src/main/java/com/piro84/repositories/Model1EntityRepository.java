/**
 * (C) 2013, Trapeze Switzerland GmbH. All rights reserved.
 */
package com.piro84.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.piro84.model1.Entity1;


/**
 * Repository for persisting {@link Entity1} entities.
 */
public interface Model1EntityRepository extends JpaRepository<Entity1, Integer> {

}
