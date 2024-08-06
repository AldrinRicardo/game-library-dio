package me.dio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import me.dio.model.Platform;

public interface PlatformRepository extends JpaRepository<Platform, Long> {

}
