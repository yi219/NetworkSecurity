package com.example.myapplication;

import android.widget.RatingBar;

public class ListViewItem {
    private String dateStr;
    private float rating;
    private String titleStr;
    private String contentStr;

    public void setDate(String date){
        dateStr = date;
    }
    public void setTitle(String title){
        titleStr = title;
    }
    public void setContent(String content){
        contentStr = content;
    }
    public void setRating(float ratingbar){
        rating = ratingbar;
    }

    public String getDate(){
        return this.dateStr;
    }
    public String getTitle(){
        return this.titleStr;
    }
    public String getContent(){
        return this.contentStr;
    }
    public float getRating(){
        return this.rating;
    }
}
