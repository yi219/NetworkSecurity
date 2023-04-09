package com.example.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.ui.dashboard.DashboardFragment;

public class DiaryAdd extends AppCompatActivity {
    ImageButton addBtn;
    Intent intent;
    String diaryDate;
    TextView dateText;
    EditText editTitle;
    EditText editContent;
    RatingBar rating;
    String uriString = "content://com.example.myapplication/DiaryProvider";
    Uri uri = new Uri.Builder().build().parse(uriString);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_add);

        intent = getIntent();
        diaryDate = intent.getStringExtra("날짜");

        addBtn = findViewById(R.id.addBtn);

        dateText = findViewById(R.id.dateText);
        dateText.setText(diaryDate);
        editTitle = findViewById(R.id.editTitle);
        editContent = findViewById(R.id.editContent);
        rating = findViewById(R.id.editRating);


        addBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                addDiary();
                finish();
            }
        });
    }

    public void addDiary(){
        Cursor cursor = this.getContentResolver().query(uri, null, null, null, null, null);
        ContentValues values = new ContentValues();
        values.put("date", String.valueOf(dateText.getText()));
        values.put("title", String.valueOf(editTitle.getText()));
        values.put("content", String.valueOf(editContent.getText()));
        values.put("rating", String.valueOf(rating.getRating()));

        uri = this.getContentResolver().insert(uri, values);
    }




}
