package com.cognivudb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognivudb.entity.Asset;

public interface AssetRepo extends JpaRepository<Asset, Long>{

}
