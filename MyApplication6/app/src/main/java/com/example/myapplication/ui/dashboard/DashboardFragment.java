package com.example.myapplication.ui.dashboard;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication.DatabaseHelper;
import com.example.myapplication.DiaryActivity;
import com.example.myapplication.DiaryAdd;
import com.example.myapplication.DiaryProvider;
import com.example.myapplication.ListViewAdapter;
import com.example.myapplication.R;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private ListView listview;
    private ListViewAdapter adapter;
    private DiaryProvider diaryProvider;
    String uriString = "content://com.example.myapplication/DiaryProvider";
    Uri uri = new Uri.Builder().build().parse(uriString);



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        diaryProvider = new DiaryProvider();

        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        /*final TextView textView = root.findViewById(R.id.text_dashboard);
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        listview = root.findViewById(R.id.diarylist);
        getData();

        return root;
    }


    public void getData(){
        DatabaseHelper helper = new DatabaseHelper(getContext());
        SQLiteDatabase database = helper.getReadableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM diary", null);

        adapter = new ListViewAdapter();

        while(cursor.moveToNext()){
            adapter.addItemList(cursor.getString(0), cursor.getInt(3), cursor.getString(1), cursor.getString(2));
        }

        listview.setAdapter(adapter);

    }

}
