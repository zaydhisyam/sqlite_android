package com.ayobuataplikasi.sqlite_db;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateDeleteActivity extends AppCompatActivity {

    private mahasiswa mahasiswa;
    private EditText et_nama, et_email, et_noHp;
    private Button btn_update, btn_delete;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);

        Intent intent = getIntent();
        mahasiswa = (mahasiswa) intent.getSerializableExtra("mhs");
        databaseHelper = new DatabaseHelper(this);

        et_nama = findViewById(R.id.et_nama);
        et_email = findViewById(R.id.et_email);
        et_noHp = findViewById(R.id.et_noHp);
        btn_delete = findViewById(R.id.btn_delete);
        btn_update = findViewById(R.id.btn_update);

        et_nama.setText(mahasiswa.getNama());
        et_email.setText(mahasiswa.getEmail());
        et_noHp.setText(mahasiswa.getNoHp());

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseHelper.updateMhs(
                        mahasiswa.getNim(), et_nama.getText().toString(),
                        et_email.getText().toString(), et_noHp.getText().toString());
                Toast.makeText(UpdateDeleteActivity.this, "Update berhasil", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UpdateDeleteActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseHelper.deleteMhs(mahasiswa.getNim());
                Toast.makeText(UpdateDeleteActivity.this, "Delete berhasil", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UpdateDeleteActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }
}
