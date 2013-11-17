package com.piro84.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.piro84.entities.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
