package com.FerrySaptawan.sqlliitef;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SecretChamber extends AppCompatActivity {
    private DBHelper dbHelper;
    private TextView textViewData;

    Button btnhapus, btnBack;

    EditText hapus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secret_chamber);

        dbHelper = new DBHelper(this);
        textViewData = findViewById(R.id.textViewData);
        btnhapus = findViewById(R.id.btnhapus);
        btnBack = findViewById(R.id.back);
        hapus = findViewById(R.id.hapus);


        btnhapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userToDelete = hapus.getText().toString();

                boolean isDeleted = dbHelper.deleteDatauser(userToDelete);

                if (isDeleted) {
                    Toast.makeText(SecretChamber.this, "Record deleted successfully", Toast.LENGTH_SHORT).show();
                    refreshData();
                } else {
                    Toast.makeText(SecretChamber.this, "Failed to delete record", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecretChamber.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });


        Cursor res = dbHelper.tampildatauser();
        if (res.getCount() == 0) {
            Toast.makeText(SecretChamber.this, "Tidak Ada Data", Toast.LENGTH_SHORT).show();
        } else {
            StringBuilder data = new StringBuilder();
            while (res.moveToNext()) {
                String user = res.getString(0);
                String pass = res.getString(1);

                data.append("USER    : ").append(user).append("\n");
                data.append("PASSWORD: ").append(pass).append("\n\n\n");
            }
            textViewData.setText(data.toString());
        }

        res.close();
    }

    private void loadData() {
        Cursor res = dbHelper.tampildatauser();
        if (res.getCount() == 0) {
            Toast.makeText(SecretChamber.this, "Tidak Ada Data", Toast.LENGTH_SHORT).show();
        } else {
            StringBuilder data = new StringBuilder();
            while (res.moveToNext()) {
                String user = res.getString(0);
                String pass = res.getString(1);

                data.append("USER: ").append(user).append("\n");
                data.append("PASSWORD: ").append(pass).append("\n\n\n");
            }

            textViewData.setText(data.toString());
            res.close();
        }
    }

    private void refreshData() {
        textViewData.setText("");
        loadData();
    }
}
