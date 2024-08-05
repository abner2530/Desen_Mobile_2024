package com.example.atividade3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CardView extends RecyclerView.Adapter<CardView.NewHolder> {
    private List<ModelCard> listCard;
    public CardView(List<ModelCard> listCard) {this.listCard = listCard;}

    @NonNull
    @Override
    public NewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_card_view, parent, false);
        return new NewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NewHolder holder, int position) {
        ModelCard news = listCard.get(position);
        holder.tv_titulo.setText(news.getTitulo());
        holder.tv_descricao.setText(news.getDescricao());
        holder.card_layout.setBackgroundResource(news.getBackground());
    }

    @Override
    public int getItemCount() {
        return listCard.size();
    }

    public static class NewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_titulo, tv_descricao;
        ConstraintLayout card_layout;

        public NewHolder(@NonNull View itemView) {
            super(itemView);

            tv_titulo = itemView.findViewById(R.id.tv_card_title);
            tv_descricao = itemView.findViewById(R.id.tv_card_descricao);
            card_layout = itemView.findViewById(R.id.card_layout);

            tv_titulo.setOnClickListener(this);
            tv_descricao.setOnClickListener(this);
            card_layout.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(), "Você clicou no item: " + tv_titulo.getText(), Toast.LENGTH_SHORT).show();
        }
    }

}