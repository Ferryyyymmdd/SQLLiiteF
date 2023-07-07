package com.FerrySaptawan.sqlliitef;
import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BiodataMahasiswa extends AppCompatActivity {
    private DBHelper dbHelper;
    private TextView textViewData;

    Button btnhapus;

    EditText hapus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biodata_mahasiswa);

        dbHelper = new DBHelper(this);
        textViewData = findViewById(R.id.textViewData);
        btnhapus = findViewById(R.id.btnhapus);
        hapus = findViewById(R.id.hapus);

        btnhapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nimToDelete = hapus.getText().toString();

                boolean isDeleted = dbHelper.deleteData(nimToDelete);

                if (isDeleted) {
                    Toast.makeText(BiodataMahasiswa.this, "Record deleted successfully", Toast.LENGTH_SHORT).show();
                    refreshData();
                } else {
                    Toast.makeText(BiodataMahasiswa.this, "Failed to delete record", Toast.LENGTH_SHORT).show();
                }
            }
        });


        Cursor res = dbHelper.tampildata();
        if (res.getCount() == 0) {
            Toast.makeText(BiodataMahasiswa.this, "Tidak Ada Data", Toast.LENGTH_SHORT).show();
        } else {
            StringBuilder data = new StringBuilder();
            while (res.moveToNext()) {
                String nim = res.getString(0);
                String nama = res.getString(1);
                String jeniskelamin = res.getString(2);
                String alamat = res.getString(3);
                String email = res.getString(4);

                data.append("NIM: ").append(nim).append("\n");
                data.append("Nama: ").append(nama).append("\n");
                data.append("Jenis Kelamin: ").append(jeniskelamin).append("\n");
                data.append("Alamat: ").append(alamat).append("\n");
                data.append("Email: ").append(email).append("\n\n\n");
            }
            textViewData.setText(data.toString());
        }

        res.close();
    }

    private void loadData() {
        Cursor res = dbHelper.tampildatabuku();
        if (res.getCount() == 0) {
            Toast.makeText(BiodataMahasiswa.this, "Tidak Ada Data", Toast.LENGTH_SHORT).show();
        } else {
            StringBuilder data = new StringBuilder();
            while (res.moveToNext()) {
                String nim = res.getString(0);
                String nama = res.getString(1);
                String jeniskelamin = res.getString(2);
                String alamat = res.getString(3);
                String email = res.getString(4);

                data.append("NIM: ").append(nim).append("\n");
                data.append("Nama: ").append(nama).append("\n");
                data.append("Jenis Kelamin: ").append(jeniskelamin).append("\n");
                data.append("Alamat: ").append(alamat).append("\n");
                data.append("Email: ").append(email).append("\n\n\n");
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
