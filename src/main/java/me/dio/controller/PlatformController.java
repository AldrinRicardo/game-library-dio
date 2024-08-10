package me.dio.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.dio.model.Platform;
import me.dio.service.PlatformService;

@RestController
@RequestMapping("/platforms")
@Tag(name = "Platform Controller", description = "RESTful API for managing gaming platforms")
public class PlatformController {

	private final PlatformService platformService;

	public PlatformController(PlatformService platformService) {
		super();
		this.platformService = platformService;
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Get a platform by ID", description = "Retrieves a specific platform based on its ID")
	public ResponseEntity<Platform> findById(@PathVariable Long id){
		Platform platform = platformService.findById(id);
		return ResponseEntity.ok(platform);
	}
	
	@PostMapping
	@Operation(summary = "Save a new platform", description = "Saves a new platform and return its data")
	public ResponseEntity<Platform> save(@RequestBody Platform platformToSave){
		Platform platformSaved = platformService.save(platformToSave);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(platformSaved.getId()).toUri();
		return ResponseEntity.created(location).body(platformSaved);
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Delete a platform", description = "Deletes a platform based on its ID")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		platformService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
