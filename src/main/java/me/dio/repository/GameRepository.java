package me.dio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import me.dio.model.Game;

public interface GameRepository extends JpaRepository<Game, Long> {

}
