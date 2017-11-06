package com.levelsbeyond.notes.model;

public class Note {
    private int id;
    private String body;

    public Note(String body) {
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Note note = (Note) o;

        if (id != note.id) return false;
        return body.equals(note.body);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + body.hashCode();
        return result;
    }
}
