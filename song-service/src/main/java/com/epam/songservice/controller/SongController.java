package com.epam.songservice.controller;

import com.epam.songservice.model.Song;
import com.epam.songservice.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/songs")
public class SongController {

    private final SongService songService;

    @Autowired
    public SongController(SongService songService) {
        this.songService = songService;
    }

    @PostMapping
    public ResponseEntity<Song> createSong(@RequestBody Song song) {
        Song savedSong = songService.save(song);
        return ResponseEntity.ok(savedSong);
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
    public ResponseEntity<Void> deleteSongs(@RequestParam List<Integer> ids) {
        songService.deleteByIds(ids);
        return ResponseEntity.noContent().build();
    }
}
