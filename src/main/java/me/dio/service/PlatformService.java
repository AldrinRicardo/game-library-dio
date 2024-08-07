package me.dio.service;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import me.dio.model.Game;
import me.dio.model.Platform;
import me.dio.repository.PlatformRepository;

@Service
public class PlatformService {

	private PlatformRepository platformRepository;
	public PlatformService(PlatformRepository platformRepository) {
		super();
		this.platformRepository = platformRepository;
	}
	
    public Platform findById(Long id) {
        return platformRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }
    
    public Platform save(Platform gameToSave) {
    	if (platformRepository.existsById(gameToSave.getId())) {
    		throw new IllegalArgumentException("Platform already exists in the database.");
    	} else {
    		return platformRepository.save(gameToSave);
    	}
    }
    
    public boolean existsById(Game game) {
    	return platformRepository.existsById(game.getPlatform().getId());
    }
}
