package com.ayobuataplikasi.sqlite_db;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnAdd, btnGetAll;
    private EditText et_nim, et_nama, et_email, et_noHp;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);
        btnAdd = findViewById(R.id.btn_add);
        btnGetAll = findViewById(R.id.btn_getAll);
        et_nim = findViewById(R.id.et_nim);
        et_nama = findViewById(R.id.et_nama);
        et_email = findViewById(R.id.et_email);
        et_noHp = findViewById(R.id.et_noHp);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseHelper.addMhs(et_nim.getText().toString(), et_nama.getText().toString(),
                        et_email.getText().toString(), et_noHp.getText().toString());
                et_nim.setText("");
                et_nama.setText("");
                et_email.setText("");
                et_noHp.setText("");
                Toast.makeText(MainActivity.this, "Data berhasil ditambah", Toast.LENGTH_SHORT).show();
            }
        });

        btnGetAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, getAllMhsActivity.class);
                startActivity(intent);
            }
        });
    }
}
