package com.ncapas.actividadl3capasv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import java.util.List;

import com.ncapas.actividadl3capasv.Domain.Entities.VideoGame;
import com.ncapas.actividadl3capasv.service.iVideoGameService;
import com.ncapas.actividadl3capasv.util.ApiConstants;

@RestController
@RequestMapping(ApiConstants.VIDEOGAMES_BASE_PATH)
@CrossOrigin(origins = "*")
public class VideoGameController {

    @Autowired
    private iVideoGameService videoGameService;

    @PostMapping
    public ResponseEntity<VideoGame> createVideoGame(@RequestBody VideoGame videoGame) {
        VideoGame createdVideoGame = videoGameService.createVideoGame(videoGame);
        return ResponseEntity.ok(createdVideoGame);
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<VideoGame>> getAllVideoGames() {
        List<VideoGame> games = videoGameService.getAllVideoGames();
        return ResponseEntity.ok(games);
    }
}
