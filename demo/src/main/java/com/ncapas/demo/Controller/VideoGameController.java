package com.ncapas.demo.Controller;

import com.ncapas.demo.Domain.Entity.VideoGame;
import com.ncapas.demo.Service.iVideoGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/videogames")
public class VideoGameController {
    @Autowired
    private iVideoGameService videoGameService;

    @PostMapping
    public ResponseEntity<VideoGame> createVideoGame(@RequestBody VideoGame videoGame) {
        VideoGame createdVideoGame = videoGameService.createVideoGame(videoGame);
        return ResponseEntity.ok(createdVideoGame);
    }
}
