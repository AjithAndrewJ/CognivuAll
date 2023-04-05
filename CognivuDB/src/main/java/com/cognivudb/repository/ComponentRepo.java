package com.cognivudb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognivudb.entity.Component;

public interface ComponentRepo extends JpaRepository<Component, Long>{

}
