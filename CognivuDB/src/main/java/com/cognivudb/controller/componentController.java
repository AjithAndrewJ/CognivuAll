package com.cognivudb.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cognivudb.entity.Component;
import com.cognivudb.excel.ExcelToDBComponent;
import com.cognivudb.service.ComponentService;

@RestController
public class componentController {
	@Autowired
	private ComponentService componentService;
	
	@PostMapping("api/componentSave/assetID={id}")
	public ResponseEntity<Component> saveComponent(@RequestBody Component componentSave,@PathVariable long id){
		return new ResponseEntity<Component>(componentService.componentSave(componentSave,id),HttpStatus.OK);
	}
	
	@GetMapping("api/getAllComponent")
	public ResponseEntity<List<Component>> findAll() {
	    List<Component> component = componentService.findAll();
	    return new ResponseEntity<>(component, HttpStatus.OK);
	}

	@GetMapping("api/getComponent/componentID={id}")
	public ResponseEntity<Component> findById(@PathVariable long id) {
		Optional<Component> component = componentService.findById(id);
		if (component.isPresent()) {
			return new ResponseEntity<>(component.get(), HttpStatus.OK);
	    } else {
	    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	  @PostMapping("api/component/upload/assetID={id}")
	  public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file,@PathVariable long id) {

	    if (ExcelToDBComponent.checkExcelFormat(file)) {
	    	this.componentService.saveComponentFile(file,id);
	    	return ResponseEntity.ok(Map.of("message","File is uploaded and saved to db"));
	    }

	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload excel file");
	  }
}
