package com.example.movieapp.service;

import com.example.movieapp.entity.Episode;
import com.example.movieapp.repository.EpisodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EpisodeService {
    private final EpisodeRepository episodeRepository;

    public List<Episode> getEpisodeListOfMovie(Integer movieId) {
        return episodeRepository.findByMovie_IdOrderByDisplayOrderAsc(movieId);
    }

    public List<Episode> getEpisodeListOfMovie(Integer movieId, Boolean status) {
        return episodeRepository.findByMovie_IdAndMovie_StatusOrderByDisplayOrderAsc(movieId, status);
    }


    public Episode createEpisode(Episode episode) {
        episode.setVideoUrl(null); // Set video URL to null
        episode.setCreatedAt(LocalDateTime.now());
        return episodeRepository.save(episode);
    }

    public List<Episode> getAllEpisodes() {
        return episodeRepository.findAllByOrderByDisplayOrderAsc();
    }
    public Episode updateEpisodeVideoUrl(Integer episodeId, String videoUrl) {
        Episode episode = episodeRepository.findById(Math.toIntExact(episodeId))
                .orElseThrow(() -> new RuntimeException("Episode not found"));
        episode.setVideoUrl(videoUrl);
        return episodeRepository.save(episode);
    }
    public void deleteEpisode(Integer id) {
        if (episodeRepository.existsById(id)) {
            episodeRepository.deleteById(id);
        } else {
            throw new RuntimeException("Episode not found");
        }
    }
}
