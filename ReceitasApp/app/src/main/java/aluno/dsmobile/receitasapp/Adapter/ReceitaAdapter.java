package aluno.dsmobile.receitasapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import aluno.dsmobile.receitasapp.Models.Receita;
import aluno.dsmobile.receitasapp.R;
import aluno.dsmobile.receitasapp.ReceitaDetailActivity;

public class ReceitaAdapter extends RecyclerView.Adapter<ReceitaAdapter.ViewHolder> {

    private final List<Receita> receitas;
    private final Context context;

    public ReceitaAdapter(Context context, List<Receita> receitas) {
        this.context = context;
        this.receitas = receitas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_receita, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Receita receita = receitas.get(position);
        holder.tituloTextView.setText(receita.getTitulo());
        Glide.with(context)
                .load(receita.getImagemUrl())
                .into(holder.imagemImageView);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ReceitaDetailActivity.class);
            intent.putExtra("receita_id", receita.getId());
            context.startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {
        return receitas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imagemImageView;
        private final TextView tituloTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imagemImageView = itemView.findViewById(R.id.image_view_imagem);
            tituloTextView = itemView.findViewById(R.id.tv_card_title);
        }
    }
}
