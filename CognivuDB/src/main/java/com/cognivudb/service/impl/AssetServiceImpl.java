package com.cognivudb.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cognivudb.entity.Asset;
import com.cognivudb.excel.ExcelToDBAsset;
import com.cognivudb.repository.AssetRepo;
import com.cognivudb.service.AssetService;

@Service
public class AssetServiceImpl implements AssetService{
	
	@Autowired
	private AssetRepo assetRepo;

	@Override
	public Asset assetSave(Asset assetSave) {
		return assetRepo.save(assetSave);
	}

	@Override
	public List<Asset> findAll() {
		return assetRepo.findAll();
	}

	@Override
	public Optional<Asset> findById(Long id) {
		return assetRepo.findById(id);
	}

	@Override
	public void saveAssetFile(MultipartFile file) {
		try {
			List<Asset> products = ExcelToDBAsset.convertExcerlToListOfProducts(file.getInputStream());
			this.assetRepo.saveAll(products);
			
//			List<Asset> a=assetRepo.findAll();
//			for(int i=0;i<a.size();i++) {
//				for(int j=i+1;j<a.size();j++) {
//					if(a.get(i).getAssetName()==a.get(j).getAssetName()) {
//						System.out.println("the document name already exist");
//						this.assetRepo.deleteAll(products);
//					}
//				}
//			}
			
		} catch (IOException e) {
			throw new RuntimeException("fail to store excel data: " + e.getMessage());
		}
	}

	@Override
	public Asset updateAsset(Asset asset, long id) {
		Asset ast=assetRepo.findById(id).orElseThrow();
		ast.setAssetName(asset.getAssetName());
		ast.setManufacturer(asset.getManufacturer());
		ast.setModel(asset.getModel());
		ast.setSerialNumber(asset.getSerialNumber());
		ast.setLocation(asset.getLocation());
		ast.setStatus(asset.getStatus());
		return assetRepo.save(ast);
	}

}
