package com.levelsbeyond.notes.dao;

import com.levelsbeyond.notes.exc.DaoException;
import com.levelsbeyond.notes.model.Note;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oNoteDao implements NoteDao {
    private final Sql2o sql2o;

    public Sql2oNoteDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Note note) throws DaoException {
        String sql = "INSERT INTO notes(body) VALUES(:body)";
        try (Connection conn = sql2o.open()) {
            int id = (int) conn.createQuery(sql)
                    .bind(note)
                    .executeUpdate()
                    .getKey();
            note.setId(id);
        } catch (Sql2oException ex) {
            throw new DaoException(ex, "Couldn't add a note :(");
        }
    }

    @Override
    public List<Note> findAll() {
        String sql = "SELECT * FROM notes";
        try(Connection conn = sql2o.open()) {
            return conn.createQuery(sql)
                    .executeAndFetch(Note.class);
        }
    }

    @Override
    public Note findById(int id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * from notes WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Note.class);
        }

    }


}
