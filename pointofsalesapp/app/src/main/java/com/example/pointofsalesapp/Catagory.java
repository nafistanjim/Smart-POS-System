package com.example.pointofsalesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Catagory extends AppCompatActivity {

    EditText ed1,ed2;
    Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catagory);

        ed1 = findViewById(R.id.catagory);
        ed2 = findViewById(R.id.catagorydes);
        b1 = findViewById(R.id.btn);
        b2 = findViewById(R.id.btn2);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Catagory.this,Main.class);
                startActivity(i);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
            }
        });
    }

    public void insert(){
        try
        {
            String catagory = ed1.getText().toString();
            String description = ed2.getText().toString();
            SQLiteDatabase db = openOrCreateDatabase("superpos", Context.MODE_PRIVATE, null);
            db.execSQL("CREATE TABLE IF NOT EXISTS catagory(id INTEGER PRIMARY KEY AUTOINCREMENT,catagory VARCHAR, catdes VARCHAR)");

            String sql = "insert into catagory (catagory,catdes)values(?,?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,catagory);
            statement.bindString(2,description);
            statement.execute();
            Toast.makeText(this,"Category Created",Toast.LENGTH_LONG).show();
            ed1.setText("");
            ed2.setText("");
            ed1.requestFocus();
        }
        catch(Exception ex)
        {
            Toast.makeText(this,"Category Creation Failed",Toast.LENGTH_LONG).show();
        }
    }
}