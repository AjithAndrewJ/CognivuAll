package com.cognivudb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.cognivudb.entity.Component;

public interface ComponentService {
	Component componentSave(Component componentSave,long id);

	List<Component> findAll();

	Optional<Component> findById(Long id);
	
	void saveComponentFile(MultipartFile file,long id);
}
