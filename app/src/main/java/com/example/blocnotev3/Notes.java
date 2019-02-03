package com.example.blocnotev3;

public class Notes {

    private String title;
    private Number exp;
    private String date;
    private String description;

    public Notes() {}

    public Notes(String title, Number exp, String date, String description) {
        this.title = title;
        this.exp = exp;
        this.date = date;
        this.description = description;
    }

    public String getTitle(){
        return title;
    }
    public Number getExp(){
        return exp;
    }
    public String getDate(){
        return date;
    }
    public String getDescription(){
        return description;
    }

}