package com.cognivudb.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cognivudb.entity.Asset;
import com.cognivudb.entity.Component;
import com.cognivudb.excel.ExcelToDBComponent;
import com.cognivudb.repository.AssetRepo;
import com.cognivudb.repository.ComponentRepo;
import com.cognivudb.service.ComponentService;

@Service
public class ComponentServiceImpl implements ComponentService{
	
	@Autowired
	private AssetRepo assetRepo;
	
	@Autowired
	private ComponentRepo componentRepo;

	@Override
	public Component componentSave(Component componentSave,long id) {
		Asset asset=assetRepo.findById(id).orElseThrow();
		componentSave.setAsset(asset);
		return componentRepo.save(componentSave);
	}

	@Override
	public List<Component> findAll() {
		return componentRepo.findAll();
	}

	@Override
	public Optional<Component> findById(Long id) {
		return componentRepo.findById(id);
	}

	@Override
	public void saveComponentFile(MultipartFile file,long id) {
		try {
			List<Component> component = ExcelToDBComponent.convertExcerlToListOfProducts(file.getInputStream());
			Asset asset=assetRepo.findById(id).orElseThrow();
			for (int i=0;i<component.size();i++) {
				component.get(i).setAsset(asset);
			}
			this.componentRepo.saveAll(component);
			
		} catch (IOException e) {
			throw new RuntimeException("fail to store excel data: " + e.getMessage());
		}
	}

}
