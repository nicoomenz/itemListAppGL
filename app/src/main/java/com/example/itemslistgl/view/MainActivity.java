package com.example.itemslistgl.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.itemslistgl.Interface.ApiService;
import com.example.itemslistgl.Model.Item;
import com.example.itemslistgl.R;
import com.example.itemslistgl.adapter.ItemsAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.itemslistgl.utils.Constans.BASE_URL;

public class MainActivity extends AppCompatActivity {

    private TextView mJsonTxtview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService api = retrofit.create(ApiService.class);

        Call<List<Item>> call = api.getItem();

        call.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {

                if (!response.isSuccessful()) {
                    mJsonTxtview.setText("Codigo: " + response.code());
                    return;
                }
                showData(response.body());
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                mJsonTxtview.setText(t.getMessage());
            }
        });

    }

    private void showData(final List<Item> itemList) {

        RecyclerView.Adapter mAdapter;
        RecyclerView.LayoutManager mLayoutManager;



        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ItemsAdapter(itemList, R.layout.items_view_list, new ItemsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Item item, int position) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("item", itemList.get(position));
                startActivity(intent);
            }
        });
        //mejora la performance
        mRecyclerView.setHasFixedSize(true);
        // Añade un efecto por defecto, si le pasamos null lo desactivamos por completo
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        //divide el recyclerView
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(dividerItemDecoration);
    }


}
