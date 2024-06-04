package com.epam.songservice.controller;

import com.epam.songservice.model.Song;
import com.epam.songservice.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/songs")
public class SongController {

    private final SongService songService;

    @Autowired
    public SongController(SongService songService) {
        this.songService = songService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Integer>> createSong(@RequestBody Song song) {
        var savedSong = songService.save(song);
        var responseBody = Map.of("id", savedSong.getId());
        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Song> getSong(@PathVariable Integer id) {
        Song song = songService.findById(id);
        if (song == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(song);
        }
    }

    @DeleteMapping
    public ResponseEntity<Map<String, List<Integer>>> deleteSongs(@RequestParam List<Integer> ids) {
        songService.deleteByIds(ids);
        var responseBody = Map.of("ids", ids);
        return ResponseEntity.ok(responseBody);
    }
}
