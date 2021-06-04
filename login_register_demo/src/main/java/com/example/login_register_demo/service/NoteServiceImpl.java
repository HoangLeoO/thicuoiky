package com.example.login_register_demo.service;

import com.example.login_register_demo.model.Note;
import com.example.login_register_demo.model.UserEntity;
import com.example.login_register_demo.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements  NoteService {
    @Autowired
    private NoteRepository noteRepository;


    @Override
    public List<Note> findAllNote() {
        return noteRepository.findAll();
    }

    @Override
    public void deleteNoteById(Long id) {
        noteRepository.deleteById(id);
    }

    @Override
    public Note findNoteById(Long id) {
        return noteRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Note note, UserEntity userEntity) {
        note.setUserEntity(userEntity);
        noteRepository.save(note);
    }

    @Override
    public void save(Note note) {
        noteRepository.save(note);
    }

    @Override
    public List<Note> findByTile(String tile) {
        return noteRepository.findByTile(tile);
    }
}
