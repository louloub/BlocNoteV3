package com.example.blocnotev3;

import java.util.HashMap;
import java.util.Map;

public class Note {

    // ----------------
    // Attributs |
    // ----------------

    private String uid;
    private String title;


    public Note() {}

    // ----------------
    // Constructeur |
    // ----------------

    public Note(String uid, String title) {
        this.uid = uid;
        this.title = title;
    }

    // ----------------
    // Getters Setters|
    // ----------------

    public String getUid() { return uid; }
    public String getTitle() { return title; }

    public void setUid(String uid) { this.uid = uid; }
    public void setTitle(String title) { this.title = title; }

    // ----------------
    // HASHMAP        |
    // ----------------

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("title", title);
        return result;
    }
}