package com.example.shafi.sqlitedb;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.shafi.sqlitedb.helper.databaseHelper;

import java.util.ArrayList;

public class DataShow extends AppCompatActivity {

    private ListView listView;
    private databaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_show);

        listView = findViewById(R.id.listView);
        databaseHelper = new databaseHelper(this);

        loadData();
    }


    public void loadData(){
        ArrayList<String> listData = new ArrayList<>();

        Cursor cursor = databaseHelper.showAllData();

        if (cursor.getCount() == 0){
            Toast.makeText(getApplicationContext(), "No data its empty",Toast.LENGTH_SHORT).show();
        }else {

            while (cursor.moveToNext()){
                listData.add(cursor.getString(0)+" \t  "+cursor.getString(1));
            }

            final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.sample_list_view, R.id.tvName,listData);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String selectValue = parent.getItemAtPosition(position).toString();
                    Toast.makeText(getApplicationContext(), "selected Value : "+selectValue, Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
