package com.epam.songservice.service;

import com.epam.songservice.model.Song;
import com.epam.songservice.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {

    private final SongRepository songRepository;

    @Autowired
    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public Song save(Song song) {
        return songRepository.save(song);
    }

    public Song findById(Integer id) {
        return songRepository.findById(id).orElse(null);
    }

    public void deleteByIds(List<Integer> ids) {
        songRepository.deleteAllById(ids);
    }
}
