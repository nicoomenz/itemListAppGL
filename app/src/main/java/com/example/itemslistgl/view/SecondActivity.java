package com.example.itemslistgl.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.itemslistgl.Model.Item;
import com.example.itemslistgl.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        Item item = intent.getParcelableExtra("item");

        String title = item.getTitle();
        String description = item.getDescription();
        String image = item.getImageURL();

        TextView titleMain = findViewById(R.id.titleMain_activity2);
        titleMain.setText(title);
        TextView descriptionAc2 = findViewById(R.id.description_activity2);
        descriptionAc2.setText(description);
        ImageView imageAc2 = findViewById(R.id.imageMain_activity2);
        Picasso.get().load(image).into(imageAc2);


    }
}
