package me.dio.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import me.dio.model.Game;
import me.dio.repository.GameRepository;

@Service
public class GameService {

	private GameRepository gameRepository;
	
	public GameService(GameRepository gameRepository) {
		super();
		this.gameRepository = gameRepository;
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
    	} else if (!(this.platformExistsById(gameToSave))) {
    		throw new NoSuchElementException("Platform not found on the library.");
    	}
    	return gameRepository.save(gameToSave);
    }
    
    public void update(Long id, Game gameToUpdate) {
        Game dbGame = this.findById(id);
        if (!dbGame.getId().equals(gameToUpdate.getId())) {
            throw new IllegalArgumentException("Update IDs must be the same.");
        }
        dbGame.setName(gameToUpdate.getName());
        dbGame.setPlatform(gameToUpdate.getPlatform());
        dbGame.setGenre(gameToUpdate.getGenre());
        dbGame.setYearOfRelease(dbGame.getYearOfRelease());
        gameRepository.flush();
    }
    
    public void delete(Long id) {
    	Game g = this.findById(id);
    	gameRepository.delete(g);
    }
    
    public boolean existsById(Game game) {
    	return gameRepository.existsById(game.getId());
    }
    
    public boolean platformExistsById(Game game) {
    	return gameRepository.existsById(game.getPlatform().getId());
    }
}
