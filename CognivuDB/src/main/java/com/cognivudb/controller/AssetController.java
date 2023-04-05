package com.cognivudb.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cognivudb.entity.Asset;
import com.cognivudb.excel.ExcelToDBAsset;
import com.cognivudb.service.AssetService;

@CrossOrigin(origins = "http://10.15.7.245:4200")
@RestController
public class AssetController {
	@Autowired
	private AssetService  assetService;
	
	@PostMapping("api/assetSave")
	public ResponseEntity<Asset> saveAsset(@RequestBody Asset assetSave){
		return new ResponseEntity<Asset>(assetService.assetSave(assetSave),HttpStatus.OK);
	}
	
	@GetMapping("api/getAllAsset")
	public ResponseEntity<List<Asset>> findAll() {
		List<Asset> asset = assetService.findAll();
		return new ResponseEntity<>(asset, HttpStatus.OK);
	}

	@GetMapping("api/getAsset/assetID={id}")
	public ResponseEntity<Asset> findById(@PathVariable long id) {
		Optional<Asset> asset = assetService.findById(id);
		if (asset.isPresent()) {
			return new ResponseEntity<>(asset.get(), HttpStatus.OK);
		} else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("api/updateAsset/assetID={id}")
	public ResponseEntity<Asset> updateAsset(@PathVariable long id,@RequestBody Asset asset){
		return new ResponseEntity<Asset>(assetService.updateAsset(asset,id),HttpStatus.OK); 
	}
	
	  @PostMapping("api/asset/upload")
	  public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {

	    if (ExcelToDBAsset.checkExcelFormat(file)) {
	    	this.assetService.saveAssetFile(file);
	    	return ResponseEntity.ok(Map.of("message","File is uploaded and saved to db"));
	    }

	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload excel file");
	  }
}
