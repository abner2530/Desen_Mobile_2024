package com.example.atividade2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import com.example.atividade2.R;
import com.example.atividade2.MainActivity;
public class ItemArrayAdapter extends RecyclerView.Adapter<ItemArrayAdapter.ViewHolder> {

    private int listItemLayout;

    private ArrayList<Item> itemList;

    public ItemArrayAdapter(int layoutId, ArrayList<Item> itemList) {
        listItemLayout = layoutId;
        this.itemList = itemList;
    }

    @Override
    public int getItemCount() {
        return itemList == null ? 0 : itemList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(listItemLayout, parent, false);
        ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Item currentItem = itemList.get(position);
        holder.itemText.setText(currentItem.getName());
        holder.itemImage.setImageResource(currentItem.getImageResource());
        holder.itemDesc.setText(currentItem.getDescricao());
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView itemText;

        public TextView itemDesc;
        public ImageView itemImage;


        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemText = itemView.findViewById(R.id.item_temp);
            itemImage = itemView.findViewById(R.id.item_image);
            itemDesc = itemView.findViewById(R.id.desc_temp);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), " " + itemText.getText(), Toast.LENGTH_SHORT).show();
        }
    }
}