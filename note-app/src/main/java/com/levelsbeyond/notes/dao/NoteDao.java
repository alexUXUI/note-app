package com.levelsbeyond.notes.dao;

import com.levelsbeyond.notes.exc.DaoException;
import com.levelsbeyond.notes.model.Note;

import java.util.List;

public interface NoteDao {
    void add(Note note) throws DaoException;

    Note findById(int id);

    List<Note> findAll();
}
