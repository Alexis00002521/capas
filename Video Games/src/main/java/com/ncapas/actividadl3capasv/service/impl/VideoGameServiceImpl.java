package com.ncapas.actividadl3capasv.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncapas.actividadl3capasv.Domain.Entities.VideoGame;
import com.ncapas.actividadl3capasv.repository.iVideoGameRepository;
import com.ncapas.actividadl3capasv.service.iVideoGameService;

@Service
public class VideoGameServiceImpl implements iVideoGameService {

    @Autowired
    private iVideoGameRepository videoGameRepository;

    @Override
    public VideoGame createVideoGame(VideoGame videoGame) {
        return videoGameRepository.save(videoGame);
    }

    @Override
    public java.util.List<VideoGame> getAllVideoGames() {
        return videoGameRepository.findAll();
    }
}
