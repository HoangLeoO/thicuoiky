package com.example.login_register_demo.service;

import com.example.login_register_demo.model.Note;
import com.example.login_register_demo.model.UserEntity;

import java.util.List;

public interface NoteService {
    List<Note> findAllNote();

    void  deleteNoteById(Long id);

    Note findNoteById(Long id);

    void save(Note note, UserEntity userEntity);
    void save(Note note);

    List<Note> findByTile(String tile);
}
