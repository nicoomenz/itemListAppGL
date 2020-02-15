package com.example.itemslistgl.adapter;



import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.itemslistgl.Model.Item;
import com.example.itemslistgl.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    private List<Item> items;
    private int layout;
    private OnItemClickListener itemClickListener;

    public ItemsAdapter(List<Item> items, int layout, OnItemClickListener listener) {
        this.items = items;
        this.layout = layout;
        this.itemClickListener = listener;
    }

    @NonNull
    @Override
    public ItemsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(items.get(position), itemClickListener);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView titleMain;
        public TextView description;
        public ImageView image;


        public ViewHolder(View itemView) {
            super(itemView);
            this.titleMain = (TextView) itemView.findViewById(R.id.titleMain);
            this.description = (TextView) itemView.findViewById(R.id.description);
            this.image = (ImageView) itemView.findViewById(R.id.imageMain);
        }

        public void bind(final Item item, final OnItemClickListener listener) {
            // Procesamos los datos a renderizar
            this.titleMain.setText(item.getTitle());
            this.description.setText(item.getDescription());
            Picasso.get()
                    .load(item.getImageURL())
                    .into(this.image);
            // Definimos que por cada elemento de nuestro recycler view, tenemos un click listener
            // que se comporta de la siguiente manera...
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // ... pasamos nuestro objeto modelo (este caso String) y posición
                    listener.onItemClick(item, getAdapterPosition());
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Item item, int position);


    }
}
