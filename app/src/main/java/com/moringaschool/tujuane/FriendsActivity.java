package com.moringaschool.tujuane;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FriendsActivity extends AppCompatActivity {
    @BindView(R.id.locationTextView) TextView mLocationTextView;
    @BindView(R.id.listView) ListView mListView;
    private String[] friends = new String[] {"CHRIS MARTIN", "MATILDA DELOVE",
            "RUCHI RISPER", "ANITA ANORLDS", "EMANUEL MANU", "TESS TESA"};
    private String[] description = new String[] {"Engineer,28yrs,Tall dark and Handsome,near you", "Student,20yrs,fun and loving,dist:5km", "Nurse,28yrs,Fun and outgoing,dist:3miles",
            "Writer,24yrs,new in the hood,looking for friends,dist:2KM", "Coder, 30yrs,want to coders,dist:2miles", "Fashionist,20yrs, looking for outgoing person,dist:2Mts" };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        MyFriendsArrayAdapter adapter = new MyFriendsArrayAdapter(this, android.R.layout.simple_list_item_1, friends, description);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                String friend = ((TextView)view).getText().toString();
                Toast.makeText(FriendsActivity.this, friend, Toast.LENGTH_LONG).show();
                Log.v("FriendsActivity", "In the onItemClickListener!");
            }
        });
        mLocationTextView.setText("Friends near: " + location);
    }


}
