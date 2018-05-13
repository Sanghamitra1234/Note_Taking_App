package com.example.sang.loginsystemv;

public class NoteModel {
    private int note_id;
    private  String note_name;
    private  String note_content;

    public NoteModel() {
    }

    public NoteModel(int note_id, String note_name, String note_content) {
        this.note_id = note_id;
        this.note_name = note_name;
        this.note_content = note_content;
    }

    public int getNote_id() {
        return note_id;
    }

    public void setNote_id(int note_id) {
        this.note_id = note_id;
    }

    public String getNote_name() {
        return note_name;
    }

    public void setNote_name(String note_name) {
        this.note_name = note_name;
    }

    public String getNote_content() {
        return note_content;
    }

    public void setNote_content(String note_content) {
        this.note_content = note_content;
    }
}
