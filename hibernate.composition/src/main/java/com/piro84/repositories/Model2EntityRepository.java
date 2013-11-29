package com.piro84.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.piro84.model2.Entity1;


/**
 * Repository for the {@link Entity1} type.
 * @author pie
 *
 */
public interface Model2EntityRepository extends JpaRepository<Entity1, Integer> {

}
