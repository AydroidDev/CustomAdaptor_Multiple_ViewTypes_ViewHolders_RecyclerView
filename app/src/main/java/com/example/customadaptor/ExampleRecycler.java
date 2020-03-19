package com.example.customadaptor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

/**
 * Created by Genjitsu on 20/03/2020.
 */

public class ExampleRecycler extends AppCompatActivity {

    private ExampleAdapter mAdapter;

    private ArrayList<ExampleItem> mExampleList;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;

    int PHOTO = 0;
    int VIDEO = 1;

    int listSize = 4;
    int[] viewTypes = new int[listSize];


    String imageLink = "https://g.hizliresim.com/alex-pettyfer-01";

    String videoLink = "https://instagram.fsaw1-4.fna.fbcdn.net/v/t50.2886-16/88281411_240939810272750_6032082819699755303_n.mp4?_nc_ht=instagram.fsaw1-4.fna.fbcdn.net&_nc_cat=102&_nc_ohc=Y-4u58ammoYAX_Xt-QI&oe=5E7643A0&oh=611789c13ba64aef95783daf07543936";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example_recycler);

        mExampleList = new ArrayList<>();

        mExampleList.add(new ExampleItem(imageLink,videoLink,"test"));
        mExampleList.add(new ExampleItem(imageLink,videoLink,"test"));
        mExampleList.add(new ExampleItem(imageLink,videoLink,"test"));
        mExampleList.add(new ExampleItem(imageLink,videoLink,"test"));

        for(int i = 0; i < 4; i++){

            if ( i % 2 == 0) {
                viewTypes[i] = PHOTO;
            } else {
                viewTypes[i] = VIDEO;
            }
        }
        buildRecyclerView(viewTypes);

    }

    public void buildRecyclerView(int[] viewTypes) {


        mRecyclerView = findViewById(R.id.exRecycler);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(mExampleList,this,viewTypes);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //changeItem(position, "Clicked");
            }

        });

    }
}
