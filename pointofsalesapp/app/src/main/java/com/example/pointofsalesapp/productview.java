package com.example.pointofsalesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class productview extends AppCompatActivity {

    ListView lst1;
    ArrayList<String> titles = new ArrayList<String>();
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productview);
        lst1 = findViewById(R.id.list);
        SQLiteDatabase db = openOrCreateDatabase("superpos", Context.MODE_PRIVATE,null);

        final Cursor c = db.rawQuery("select * from product", null);
        int id = c.getColumnIndex("id");
        int product = c.getColumnIndex("product");
        int productdes = c.getColumnIndex("productdes");
        int catagory = c.getColumnIndex("catagory");
        int brand = c.getColumnIndex("brand");
        int qty = c.getColumnIndex("qty");
        int price = c.getColumnIndex("price");

        titles.clear();

        arrayAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,titles);
        lst1.setAdapter(arrayAdapter);

        final ArrayList<prod> produ = new ArrayList<prod>();
        if (c.moveToFirst()){
            do{
                prod pr = new prod();
                pr.id = c.getString(id);
                pr.product = c.getString(product);
                pr.catagory = c.getString(catagory);
                pr.brand = c.getString(brand);
                pr.qty = c.getString(qty);
                pr.price = c.getString(price);

                produ.add(pr);

                titles.add(c.getString(id)+ '\t' + c.getString(product) + '\t' + c.getString(catagory) + '\t' + c.getString(brand)+ '\t' + c.getString(qty) + '\t' + c.getString(price));
            }while(c.moveToNext());
            arrayAdapter.notifyDataSetChanged();
            lst1.invalidateViews();
        }
    }
}