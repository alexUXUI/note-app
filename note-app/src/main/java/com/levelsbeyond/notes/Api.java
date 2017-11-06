package com.levelsbeyond.notes;

import com.google.gson.Gson;

import com.levelsbeyond.notes.dao.NoteDao;
import com.levelsbeyond.notes.dao.Sql2oNoteDao;
import com.levelsbeyond.notes.model.Note;
import org.sql2o.Sql2o;

import static spark.Spark.after;
import static spark.Spark.get;
import static spark.Spark.post;

public class Api {
    public static void main(String[] args) {
        Sql2o sql2o = new Sql2o("jdbc:h2:~/notes.db;INIT=RUNSCRIPT from 'classpath:db/init.sql'", "", "");
        NoteDao noteDao = new Sql2oNoteDao(sql2o);
        Gson gson = new Gson();

        post("/api/notes", "application/json", (req, res) -> {
            Note note = gson.fromJson(req.body(), Note.class);
            noteDao.add(note);
            res.status(201);
            return note;
        }, gson::toJson);

        get("/api/notes", "application/json",
                (req, res) -> noteDao.findAll(), gson::toJson);

        get("/api/notes/:id", "application/json", (req, res) -> {
            int id = Integer.parseInt(req.params("id"));
            Note note= noteDao.findById(id);
            return note;
        }, gson::toJson);

        after((req, res) -> {
            res.type("application/json");
        });
    }


}
