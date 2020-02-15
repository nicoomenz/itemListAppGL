package com.example.itemslistgl.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.itemslistgl.Model.Item;
import com.example.itemslistgl.R;
import com.squareup.picasso.Picasso;


public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        Item item = intent.getParcelableExtra("item"); //recupero el item

        //recupero titulo, imagen y descripcion del item
        String title = item.getTitle();
        String description = item.getDescription();
        String image = item.getImageURL();

        //seteo la view activity_Second
        TextView titleMain = findViewById(R.id.titleMain_activity2);
        titleMain.setText(title);
        TextView descriptionAc2 = findViewById(R.id.description_activity2);
        descriptionAc2.setText(description);
        ImageView imageAc2 = findViewById(R.id.imageMain_activity2);
        Picasso.get().load(image).placeholder(R.drawable.loadingimage).error(R.drawable.imagenotfound).into(imageAc2);


    }
}
