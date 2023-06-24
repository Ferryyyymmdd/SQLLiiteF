package com.FerrySaptawan.sqlliitef;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BiodataBuku extends AppCompatActivity {
    private DBHelper dbHelper;
    private TextView textViewData;

    Button btnhapus;

    EditText hapus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biodata_buku);

        dbHelper = new DBHelper(this);
        textViewData = findViewById(R.id.textViewData);
        btnhapus = findViewById(R.id.btnhapus);
        hapus = findViewById(R.id.hapus);

        btnhapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nobukToDelete = hapus.getText().toString();

                boolean isDeleted = dbHelper.deleteDataBuku(nobukToDelete);

                if (isDeleted) {
                    Toast.makeText(BiodataBuku.this, "Record deleted successfully", Toast.LENGTH_SHORT).show();
                    refreshData();
                } else {
                    Toast.makeText(BiodataBuku.this, "Failed to delete record", Toast.LENGTH_SHORT).show();
                }
            }
        });


        Cursor res = dbHelper.tampildatabuku();
        if (res.getCount() == 0) {
            Toast.makeText(BiodataBuku.this, "Tidak Ada Data", Toast.LENGTH_SHORT).show();
        } else {
            StringBuilder data = new StringBuilder();
            while (res.moveToNext()) {
                String kode = res.getString(0);
                String judul = res.getString(1);
                String pengarang = res.getString(2);
                String penerbit = res.getString(3);
                String isbn = res.getString(4);

                data.append("Kode: ").append(kode).append("\n");
                data.append("Judul: ").append(judul).append("\n");
                data.append("Pengarang: ").append(pengarang).append("\n");
                data.append("Penerbit: ").append(penerbit).append("\n");
                data.append("ISBN: ").append(isbn).append("\n\n\n");
            }
            textViewData.setText(data.toString());
        }

        res.close();
    }
    private void loadData() {
        Cursor res = dbHelper.tampildatabuku();
        if (res.getCount() == 0) {
            Toast.makeText(BiodataBuku.this, "Tidak Ada Data", Toast.LENGTH_SHORT).show();
        } else {
            StringBuilder data = new StringBuilder();
            while (res.moveToNext()) {
                String kode = res.getString(0);
                String judul = res.getString(1);
                String pengarang = res.getString(2);
                String penerbit = res.getString(3);
                String isbn = res.getString(4);

                data.append("Kode: ").append(kode).append("\n");
                data.append("Judul: ").append(judul).append("\n");
                data.append("Pengarang: ").append(pengarang).append("\n");
                data.append("Penerbit: ").append(penerbit).append("\n");
                data.append("ISBN: ").append(isbn).append("\n\n\n");
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
