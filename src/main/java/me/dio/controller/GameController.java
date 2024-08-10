package me.dio.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.dio.model.Game;
import me.dio.service.GameService;

@RestController
@RequestMapping("/games")
@Tag(name = "Games Controller", description = "RESTful API for managing games")
public class GameController {

	private final GameService gameService;

	public GameController(GameService gameService) {
		super();
		this.gameService = gameService;
	}
	
	@GetMapping
	@Operation(summary = "Get all games", description = "Retrieves a list of all registered games")
	public ResponseEntity<List<Game>> findAll(){	
		return ResponseEntity.ok().body(gameService.findAll());
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Get a game by ID", description = "Retrieves a specific game based on its ID")
	public ResponseEntity<Game> findById(@PathVariable Long id){
		Game game = gameService.findById(id);
		return ResponseEntity.ok(game);
	}
	
	@PostMapping
	@Operation(summary = "Save a new game", description = "Saves a new game and return its data")
	public ResponseEntity<Game> save(@RequestBody Game gameToSave){
		Game gameSaved = gameService.save(gameToSave);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(gameSaved.getId()).toUri();
		return ResponseEntity.created(location).body(gameSaved);
	}
	
	@PutMapping("/{id}")
	@Operation(summary = "Update a game", description = "Updates an existing game data based on its ID")
	public ResponseEntity<Game> update(@PathVariable Long id, @RequestBody Game game) {
        gameService.update(id, game);
        return ResponseEntity.ok(game);
    }
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Delete a game", description = "Deletes an existing game based on its ID")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		gameService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
