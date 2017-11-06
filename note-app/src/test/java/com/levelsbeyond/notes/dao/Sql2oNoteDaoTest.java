package com.levelsbeyond.notes.dao;

import com.levelsbeyond.notes.model.Note;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Sql2o;
import org.sql2o.Connection;

import static org.junit.Assert.*;

public class Sql2oNoteDaoTest {

    private Sql2oNoteDao dao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/init.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        dao = new Sql2oNoteDao(sql2o);
        // keep connection through entire test so that it isn't wiped out
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingNoteSetsId() throws Exception {
        Note note = new Note("I am a sample note!");

        int originalNoteid = note.getId();
        dao.add(note);

        assertNotEquals(originalNoteid, note.getId());
    }

    @Test
    public void addedCoursesAreReturnedFromFindAll() throws Exception {
        Note note = newTestNote("Im a test note, hear me roar!");

        dao.add(note);

        assertEquals(1, dao.findAll().size());
    }

    @Test
    public void noCoursesReturnsEmptyList() throws Exception {
        assertEquals(0, dao.findAll().size());
    }

    @Test
    public void exisitngCoursesCannotBeFoundById() throws Exception {
        Note note= newTestNote("Im an note");
        dao.add(note);

        Note foundNote = dao.findById(note.getId());

        assertEquals(note, foundNote);
    }

    private Note newTestNote(String body) {
        return new Note(body);
    }
}