package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {
    private TextView date;
    private TextView title;
    private TextView content;
    private RatingBar rating;
    private LinearLayout clickItem;
    ListViewItem listViewItem;

    private ArrayList<ListViewItem> listViewItemList = new ArrayList<ListViewItem>();

    public ListViewAdapter(){

    }

    @Override
    public int getCount(){
        return listViewItemList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        final int pos = position;
        final Context context = parent.getContext();

        if(convertView ==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.diary_list, parent, false);
        }

        date = convertView.findViewById(R.id.diarydate);
        rating = convertView.findViewById(R.id.ratingBar);
        title = convertView.findViewById(R.id.viewTitle);
        content = convertView.findViewById(R.id.viewContent);
        clickItem = convertView.findViewById(R.id.clickItem);

        listViewItem = listViewItemList.get(position);

        date.setText(listViewItem.getDate());
        rating.setRating(listViewItem.getRating());
        title.setText(listViewItem.getTitle());
        content.setText(listViewItem.getContent());

        return convertView;

    }

    public void addItemList(String date, int rating, String title, String content){
        ListViewItem listdata = new ListViewItem();

        listdata.setDate(date);
        listdata.setRating(rating);
        listdata.setTitle(title);
        listdata.setContent(content);


        listViewItemList.add(listdata);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public Object getItem(int position){
        return listViewItemList.get(position);
    }

}
