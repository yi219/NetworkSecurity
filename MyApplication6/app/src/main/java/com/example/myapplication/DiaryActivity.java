package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DiaryActivity extends AppCompatActivity {
    DatabaseHelper dbhelper = new DatabaseHelper(this);
    DiaryProvider diaryProvider = new DiaryProvider();
    Intent intent;
    String diaryDate;
    String where;
    TextView textTitle;
    TextView textDate;
    TextView textContent;
    RatingBar textRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary);

        textDate = findViewById(R.id.textDate);
        textTitle = findViewById(R.id.textTitle);
        textContent = findViewById(R.id.textContent);
        textRating = findViewById(R.id.textRating);

        intent = getIntent();
        diaryDate = intent.getStringExtra("날짜");
        textDate.setText(diaryDate);
        where = "date = " + diaryDate;
        queryDiary(DiaryProvider.CONTENT_URI);

    }

    public void queryDiary(Uri uri){
        try {
            String[] columns = new String[] {"date", "rating", "title", "content"};
            String[] args = new String[] {"date"};
            Cursor cursor = getContentResolver().query(uri, columns, where,  null, null);
            textDate.setText(cursor.getString(cursor.getColumnIndex(columns[0])));
            textTitle.setText(cursor.getString(cursor.getColumnIndex(columns[1])));
            textContent.setText(cursor.getString(cursor.getColumnIndex(columns[2])));
            textRating.setRating(Float.parseFloat(cursor.getString(cursor.getColumnIndex(columns[4]))));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
