package com.ncapas.actividadl3capasv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ncapas.actividadl3capasv.Domain.Entities.VideoGame;

@Repository
public interface iVideoGameRepository extends JpaRepository<VideoGame, Long> {
}
