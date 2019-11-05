package com.example.shafi.sqlitedb;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shafi.sqlitedb.helper.databaseHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private databaseHelper databaseHelper;
    EditText id, name;
    Button save, show, update, delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new databaseHelper(this);
        SQLiteDatabase sqLiteDatabase  = databaseHelper.getWritableDatabase();

        id = findViewById(R.id.etId);
        name = findViewById(R.id.etName);

        save = findViewById(R.id.btSave);
        show = findViewById(R.id.btShow);
        update = findViewById(R.id.btUpdate);
        delete = findViewById(R.id.btDelete);

        save.setOnClickListener(this);
        show.setOnClickListener(this);
        update.setOnClickListener(this);
        delete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        String id1 = id.getText().toString();
        String name1 = name.getText().toString();

        if (v.getId() == R.id.btSave){

            if(id1.equals("") && name1.equals("")){
                Toast.makeText(getApplicationContext(), "Please dont be id and name empty",Toast.LENGTH_SHORT).show();
            }else {

                long rowNumber = databaseHelper.saveData(id1, name1);

                if(rowNumber > -1){
                    Toast.makeText(getApplicationContext(), "data inserted", Toast.LENGTH_SHORT).show();
                    id.setText("");
                    name.setText("");

                }else {
                    Toast.makeText(getApplicationContext(), "data isnt inserted", Toast.LENGTH_SHORT).show();
                }


            }


        }else if(v.getId() == R.id.btShow){

            Intent show = new Intent(this, DataShow.class);
            startActivity(show);

        }else if(v.getId() == R.id.btUpdate){

            Boolean isUpdated = databaseHelper.updateData(id1, name1);

            if(isUpdated == true){
                Toast.makeText(getApplicationContext(), "data is updated", Toast.LENGTH_SHORT).show();
                id.setText("");
                name.setText("");
            }else {
                Toast.makeText(getApplicationContext(), "data is not updated", Toast.LENGTH_SHORT).show();
            }

        }else if(v.getId() == R.id.btDelete){

            int value = databaseHelper.deleteData(id1);
            if(value < 0){
                Toast.makeText(getApplicationContext(), "data is not deleted", Toast.LENGTH_SHORT).show();

            }else {
                Toast.makeText(getApplicationContext(),"data is deleted", Toast.LENGTH_SHORT).show();
                id.setText("");
            }

        }

    }
}
