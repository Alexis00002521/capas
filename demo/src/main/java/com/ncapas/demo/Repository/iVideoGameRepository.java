package com.ncapas.demo.Repository;

import com.ncapas.demo.Domain.Entity.VideoGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iVideoGameRepository extends JpaRepository<VideoGame, Long> {
}
