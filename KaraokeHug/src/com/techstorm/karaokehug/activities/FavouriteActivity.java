package com.techstorm.karaokehug.activities;

import java.util.ArrayList;
import java.util.Map;

import com.techstorm.karaokehug.DatabaseCreator;
import com.techstorm.karaokehug.DisplayFavourite;
import com.techstorm.karaokehug.R;
import com.techstorm.karaokehug.R.id;
import com.techstorm.karaokehug.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;

public class FavouriteActivity extends Activity implements OnItemSelectedListener{

	 ListView userlistfavourite;
	 private ArrayList<String> user_name = new ArrayList<String>();
		private ArrayList<String> user_id = new ArrayList<String>();
		private ArrayList<String> user_lyric = new ArrayList<String>();
		private ArrayList<String> user_author = new ArrayList<String>();
		

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_favourite);
        userlistfavourite = (ListView) findViewById(R.id.userlistfavourite);
       
    	displayDataALL();
    	userlistfavourite.setClickable(true);
    	userlistfavourite.setOnItemSelectedListener(this);
    	userlistfavourite.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        
	}
	
	
	public void displayDataALL( ) {
		
			DatabaseCreator.getFavouriteData(user_name, user_id, user_lyric, user_author);
			DisplayFavourite disadpt2 = new DisplayFavourite(FavouriteActivity.this,user_id, user_name, user_lyric, user_author);
					userlistfavourite.setAdapter(disadpt2);
				
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		displayDataALL();
	}


	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		userlistfavourite = (ListView) findViewById(R.id.userlistfavourite);
		Map<String, Object> map = (Map<String, Object>)userlistfavourite.getItemAtPosition(arg2);
		 Intent intent = new Intent();
		 intent.putExtra("lyric", (String)map.get("lyric"));
		 intent.putExtra("author", (String)map.get("author"));
		 intent.putExtra("name", (String)map.get("name"));
		 intent.putExtra("id", (String)map.get("id"));
intent.setClass(getApplicationContext(), SongDetailActivity.class);
            startActivity(intent);
	}


	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		
	}
}