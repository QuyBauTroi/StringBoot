package com.example.movieapp.rest;

import com.example.movieapp.entity.Episode;
import com.example.movieapp.repository.EpisodeRepository;
import com.example.movieapp.service.EpisodeService;
import com.example.movieapp.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/episodes")
@RequiredArgsConstructor
public class EpisodeApi {
    private final EpisodeService episodeService;
    private final EpisodeRepository episodeRepository;
    private final FileService fileService;

    @PostMapping
    public ResponseEntity<Episode> createEpisode(@RequestBody Episode episode) {
        Episode createdEpisode = episodeService.createEpisode(episode);
        return ResponseEntity.ok(createdEpisode);
    }

    @GetMapping
    public ResponseEntity<List<Episode>> getAllEpisodes() {
        List<Episode> episodes = episodeService.getAllEpisodes();
        return ResponseEntity.ok(episodes);
    }

    @PostMapping("/{id}/upload-video")
    public ResponseEntity<Episode> uploadVideo(@PathVariable Integer id, @RequestParam("video") MultipartFile video) {
        try {
            String videoUrl = fileService.uploadVideo(video);
            Episode updatedEpisode = episodeService.updateEpisodeVideoUrl(id, videoUrl);
            return ResponseEntity.ok(updatedEpisode);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEpisode(@PathVariable Integer id) {
        episodeService.deleteEpisode(id);
        return ResponseEntity.noContent().build();
    }

}
