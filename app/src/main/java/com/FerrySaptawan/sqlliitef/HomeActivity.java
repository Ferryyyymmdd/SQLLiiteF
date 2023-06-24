package com.FerrySaptawan.sqlliitef;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.denzcoskun.imageslider.constants.ScaleTypes;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private ImageSlider imageSlider;

    Button btnmaha, btnbuku;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        imageSlider = findViewById(R.id.imageSlider);
        btnmaha = findViewById(R.id.btnMahasiswa);
        btnbuku = findViewById(R.id.btndatabuku);

        btnmaha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MahasiswaActivity.class);
                startActivity(intent);
            }
        });

        btnbuku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, BukuActivity.class);
                startActivity(intent);
            }
        });


        List<SlideModel> imageList = new ArrayList<>();
        imageList.add(new SlideModel(R.drawable.image, "Starry Night", ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.image2, "Starry Night Over the Rhone", ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.image3, "The Potato Eaters", ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.image4, "De pastorie in Nuenen", ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.image5, "Wheatfield with a Reaper", ScaleTypes.FIT));

        imageSlider.setImageList(imageList);
    }

}