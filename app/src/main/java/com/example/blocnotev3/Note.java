package com.example.blocnotev3;

import java.util.HashMap;
import java.util.Map;

public class Note {

    // ----------------
    // Attributs |
    // ----------------

    private String uid;
    private String title;
    private String description;

    public Note() {}

    // ----------------
    // Constructeur |
    // ----------------

    public Note(String uid, String title, String description) {
        this.uid = uid;
        this.title = title;
        this.description = description;
    }

    // ----------------
    // Getters Setters|
    // ----------------

    public String getUid() { return uid; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }

    public void setUid(String uid) { this.uid = uid; }
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }

    // ----------------
    // HASHMAP        |
    // ----------------

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("title", title);
        result.put("description",description);
        return result;
    }
}