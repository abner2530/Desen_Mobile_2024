package com.example.atividade1;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewCustomAdapter extends BaseAdapter implements AdapterView.OnItemClickListener{
    Context context;
    String[] agentes;
    String[] classes;
    String[] telas = { "jett", "sage", "omen", "astra" };
    int[] FotoAgentes = {R.drawable.jett, R.drawable.sage, R.drawable.omen, R.drawable.astra, R.drawable.kayo};

    ListViewCustomAdapter(Context context, String[] agentes, String[] classes){
        this.context = context;
        this.agentes = agentes;
        this.classes = classes;
    }

    @Override
    public int getCount() {
        return agentes.length;
    }

    @Override
    public Object getItem(int position) {
        return agentes[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null){

            convertView= LayoutInflater.from(context)
                    .inflate(R.layout.activity_list_view_custom_adapter, parent, false);

            holder = new ViewHolder();
            holder.tv_nome = convertView.findViewById(R.id.tv_agente);
            holder.iv_agente = convertView.findViewById(R.id.iv_agente);
            holder.tv_classe = convertView.findViewById(R.id.tv_classe);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_nome.setText(agentes[position]);
        holder.tv_classe.setText(classes[position]);
        holder.iv_agente.setImageResource(FotoAgentes[position]);

        return convertView;
    }
    public void setOnItemClickListener(AdapterView<?> parent) {
        parent.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        String Tela = telas[position];

        Intent intent = null;
        try {
            intent = new Intent(context, Class.forName("com.example.atividade1." + Tela));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        intent.putExtra("posicao", position);

        context.startActivity(intent);
    }
    static class ViewHolder{
        // Holds references to the views within an item layout
        TextView tv_nome;
        TextView tv_classe;
        ImageView iv_agente;

    }
}
