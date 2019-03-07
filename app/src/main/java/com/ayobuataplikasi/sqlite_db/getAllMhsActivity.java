package com.ayobuataplikasi.sqlite_db;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;

public class getAllMhsActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<mahasiswa> arrayList;
    private CustomAdapter customAdapter;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_all_mhs);

        listView = findViewById(R.id.lv);
        databaseHelper = new DatabaseHelper(this);
        arrayList = databaseHelper.getAllMhs();
        customAdapter = new CustomAdapter(this, arrayList);
        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getAllMhsActivity.this, UpdateDeleteActivity.class);
                intent.putExtra("mhs", arrayList.get(i));
                startActivity(intent);
            }
        });
    }
}
