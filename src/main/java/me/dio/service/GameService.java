package me.dio.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import me.dio.model.Game;
import me.dio.repository.GameRepository;
import me.dio.repository.PlatformRepository;

@Service
public class GameService {

	private PlatformRepository platformRepository;
	private GameRepository gameRepository;
	
	public GameService(GameRepository gameRepository, PlatformRepository platformRepository) {
		super();
		this.gameRepository = gameRepository;
		this.platformRepository = platformRepository;
	}
	
	public List<Game> findAll() {
		return gameRepository.findAll();
	}
	
	public Game findById(Long id) {
        return gameRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }
    
    public Game save(Game gameToSave) {
    	if (this.existsById(gameToSave)) {
    		throw new IllegalArgumentException("Game is already in the library.");	
    	} else if (!(platformRepository.existsById(gameToSave.getPlatform().getId()))) {
    		throw new NoSuchElementException("Platform not found on the library.");
    	}
    	return gameRepository.save(gameToSave);
    }
    
    public Game update(Long id, Game gameToUpdate) {
    	Game dbGame = this.findById(id);
    	if (dbGame.getId().equals(gameToUpdate.getId())) {
    		dbGame.setName(gameToUpdate.getName());
    		dbGame.setPlatform(gameToUpdate.getPlatform());
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
    
    public boolean existsById(Game game) {
    	return gameRepository.existsById(game.getPlatform().getId());
    }
    
}
