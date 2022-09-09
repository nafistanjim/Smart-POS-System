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

public class brandedit extends AppCompatActivity {

    EditText ed1, ed2, ed3;
    Button b1, b2, b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brandedit);

        ed1 = findViewById(R.id.brandid);
        ed2 = findViewById(R.id.brand);
        ed3 = findViewById(R.id.branddes);
        b1 = findViewById(R.id.btn);
        b2 = findViewById(R.id.btn2);
        b3 = findViewById(R.id.btn3);

        Intent i = getIntent();

        String id = i.getStringExtra("id").toString();
        String brand = i.getStringExtra("brand").toString();
        String des = i.getStringExtra("branddes").toString();

        ed1.setText(id);
        ed2.setText(brand);
        ed3.setText(des);

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Main.class);
                startActivity(i);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Edit();
            }
        });

    }

    public void Edit(){
        try
        {
            String brandid = ed1.getText().toString();
            String brandname = ed2.getText().toString();
            String branddescriptionn = ed3.getText().toString();
            SQLiteDatabase db = openOrCreateDatabase("superpos", Context.MODE_PRIVATE, null);

            String sql = "update brand set brand = ?,catdes = ? where id = ?";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,brandname);
            statement.bindString(2,branddescriptionn);
            statement.bindString(3,brandid);
            statement.execute();
            Toast.makeText(this,"Brand Updated",Toast.LENGTH_LONG).show();
            ed1.setText("");
            ed2.setText("");
            ed1.requestFocus();
        }
        catch(Exception ex)
        {
            Toast.makeText(this,"Brand Update Failed",Toast.LENGTH_LONG).show();
        }
    }

    public void delete()
    {
        try
        {
            String brandid = ed1.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("superpos", Context.MODE_PRIVATE, null);

            String sql = "delete from brand where id = ?";
            SQLiteStatement statement = db.compileStatement(sql);

            statement.bindString(3,brandid);
            statement.execute();
            Toast.makeText(this,"Brand Deleted",Toast.LENGTH_LONG).show();
            ed1.setText("");
            ed2.setText("");
            ed1.requestFocus();
        }
        catch(Exception ex)
        {
            Toast.makeText(this,"Brand Deletion Failed",Toast.LENGTH_LONG).show();
        }

    }
}