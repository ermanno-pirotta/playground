package com.piro84.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.piro84.entities.carmodel.Car;

/**
 * Repository for the {@link Car} type.
 * @author pie
 *
 */
public interface CarRepository extends JpaRepository<Car, Integer> {

}
