package com.example.login_register_demo.repository;

import com.example.login_register_demo.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note,Long> {
    List<Note> findByTile(String tile);
}
