package aluno.dsmobile.aula_appinvestidor.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import aluno.dsmobile.aula_appinvestidor.Model.Ativo;
import aluno.dsmobile.aula_appinvestidor.R;

import java.util.List;

public class AtivoRecyclerViewAdapter extends RecyclerView.Adapter<AtivoRecyclerViewAdapter.AtivoViewHolder> {

    private List<Ativo> newList;

    public AtivoRecyclerViewAdapter(List<Ativo> newList) {
        this.newList = newList;
    }

    @NonNull
    @Override
    public AtivoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ativo, parent, false);
        return new AtivoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AtivoViewHolder holder, int position) {
        Ativo ativo = newList.get(position);
        holder.tv_title.setText(ativo.getNomeAtivo());
        holder.tv_subject.setText(ativo.getNoticia());
    }

    @Override
    public int getItemCount() {
        return newList.size();
    }


    public static class AtivoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_title, tv_subject;
        ConstraintLayout card_layout;

        public AtivoViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_title = itemView.findViewById(R.id.tv_card_title);
            tv_subject = itemView.findViewById(R.id.tv_card_subject);
            card_layout = itemView.findViewById(R.id.card_layout);

            tv_title.setOnClickListener(this);

            tv_subject.setOnClickListener(this);

            card_layout.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), "Você clicou no item: " + tv_title, Toast.LENGTH_SHORT).show();

        }


    }
}
