package com.FerrySaptawan.sqlliitef;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BukuActivity extends AppCompatActivity {
    EditText kode, judul, pengarang, penerbit, isbn;
    Button simpan, tampil, hapus, edit;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buku);

        kode = findViewById(R.id.edtkodebuku);
        judul = findViewById(R.id.edtjudul);
        pengarang = findViewById(R.id.edtpengarang);
        penerbit = findViewById(R.id.edtpenerbit);
        isbn = findViewById(R.id.edtisbn);
        simpan = findViewById(R.id.btnsimpan);
        tampil = findViewById(R.id.btntampil);
        hapus = findViewById(R.id.btnhapus);
        edit = findViewById(R.id.btnedit);
        db = new DBHelper(this);

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txtkode = kode.getText().toString();
                String txtjudul = judul.getText().toString();
                String txtpengarang = pengarang.getText().toString();
                String txtpenerbit = penerbit.getText().toString();
                String txtisbn = isbn.getText().toString();

                if (TextUtils.isEmpty(txtkode) || TextUtils.isEmpty(txtjudul) || TextUtils.isEmpty(txtpengarang)
                        || TextUtils.isEmpty(txtpenerbit) || TextUtils.isEmpty(txtisbn)) {
                    Toast.makeText(BukuActivity.this, "Semua Field Wajib diIsi", Toast.LENGTH_LONG).show();
                } else {
                    boolean checkkode = db.checkkode(txtkode);
                    if (!checkkode) {
                        boolean insert = db.insertDataBuku(txtkode, txtjudul, txtpengarang, txtpenerbit, txtisbn);
                        if (insert) {
                            Toast.makeText(BukuActivity.this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(BukuActivity.this, "Data gagal disimpan", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(BukuActivity.this, "Data Mahasiswa Sudah Ada", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        tampil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = db.tampildatabuku();
                if (res.getCount() == 0) {
                    Toast.makeText(BukuActivity.this, "Tidak Ada Data", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(getApplicationContext(), BiodataBuku.class);
                startActivity(intent);
            }
        });


        hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String kodeToDelete = kode.getText().toString();

                boolean isDeleted = db.deleteDataBuku(kodeToDelete);

                if (isDeleted) {
                    Toast.makeText(BukuActivity.this, "Record deleted successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(BukuActivity.this, "Failed to delete record", Toast.LENGTH_SHORT).show();
                }
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newkode = kode.getText().toString();
                String newjudul = judul.getText().toString();
                String newpengarang= pengarang.getText().toString();
                String newpenerbit = penerbit.getText().toString();
                String newisbn = isbn.getText().toString();

                if (TextUtils.isEmpty(newkode) || TextUtils.isEmpty(newjudul) || TextUtils.isEmpty(newpengarang)
                        || TextUtils.isEmpty(newpenerbit) || TextUtils.isEmpty(newisbn)) {
                    Toast.makeText(BukuActivity.this, "Semua Field Wajib diIsi", Toast.LENGTH_LONG).show();
                } else {
                    boolean isUpdated = db.updateDataBuku(newkode, newjudul, newpengarang, newpenerbit, newisbn);

                    if (isUpdated) {
                        Toast.makeText(BukuActivity.this, "Data berhasil diupdate", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(BukuActivity.this, "Data gagal diupdate", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}