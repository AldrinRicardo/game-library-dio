package me.dio.service;

import java.util.NoSuchElementException;

import me.dio.model.Game;
import me.dio.repository.GameRepository;
import me.dio.repository.PlatformRepository;

public class GameService {

	private GameRepository gameRepository;
	public GameService(GameRepository gameRepository, PlatformRepository platformRepository) {
		super();
		this.gameRepository = gameRepository;
	}
	
    public Game findById(Long id) {
        return gameRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }
    
    public Game save(Game gameToSave) {
    	if (gameRepository.existsById(gameToSave.getId())) {
    		throw new IllegalArgumentException("Game already exists in the database.");
    	} else {
    		return gameRepository.save(gameToSave);
    	}
    }
    
    public Game update(Long id, Game gameToUpdate) {
    	Game dbGame = this.findById(id);
    	if (dbGame.getId().equals(gameToUpdate.getId())) {
    		dbGame.setName(gameToUpdate.getName());
    		dbGame.setPlatforms(gameToUpdate.getPlatforms());
    		dbGame.setGenre(gameToUpdate.getGenre());
    		dbGame.setYearOfRelease(gameToUpdate.getYearOfRelease());
    		return gameRepository.save(dbGame);
    	} else {
    		throw new IllegalArgumentException("Both game's ID must be identical.");
    	}
    }
    
    public void delete(Game gameToDelete) {
    	gameRepository.delete(gameToDelete);
    }
}
