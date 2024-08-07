package me.dio.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import me.dio.model.Platform;
import me.dio.service.PlatformService;

@RestController
@RequestMapping("/platforms")
public class PlatformController {

	private final PlatformService platformService;

	public PlatformController(PlatformService platformService) {
		super();
		this.platformService = platformService;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Platform> findById(@PathVariable Long id){
		Platform platform = platformService.findById(id);
		return ResponseEntity.ok(platform);
	}
	
	@PostMapping
	public ResponseEntity<Platform> save(@RequestBody Platform platformToSave){
		Platform platformSaved = platformService.save(platformToSave);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(platformSaved.getId()).toUri();
		return ResponseEntity.created(location).body(platformSaved);
	}
	
}
