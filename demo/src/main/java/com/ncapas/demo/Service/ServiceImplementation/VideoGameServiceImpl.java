package com.ncapas.demo.Service.ServiceImplementation;

import com.ncapas.demo.Domain.Entity.VideoGame;
import com.ncapas.demo.Repository.iVideoGameRepository;
import com.ncapas.demo.Service.iVideoGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoGameServiceImpl implements iVideoGameService {

    @Autowired
    private iVideoGameRepository videoGameRepository;

    @Override
    public VideoGame createVideoGame(VideoGame videoGame) {
        return videoGameRepository.save(videoGame);
    }
}
