package com.cognivudb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.cognivudb.entity.Asset;

public interface AssetService {
	Asset assetSave(Asset assetSave);

	List<Asset> findAll();

	Optional<Asset> findById(Long id);
	
	void saveAssetFile(MultipartFile file);

	Asset updateAsset(Asset asset, long id);
}
