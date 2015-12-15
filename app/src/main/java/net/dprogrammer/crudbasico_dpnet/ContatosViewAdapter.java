package net.dprogrammer.crudbasico_dpnet;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by dprogrammer on 14/12/2015.
 */
public class ContatosViewAdapter extends RecyclerView.Adapter<ContatosViewAdapter.ViewHolder> {

    private List<Contato> contatos;
    private Context context;

    public ContatosViewAdapter(Context context, List<Contato> contatos){
        this.context = context;
        this.contatos = contatos;
    }

    @Override
    public ContatosViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_contato, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position){
        viewHolder.nome.setText(contatos.get(position).getNome());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nome;

        public ViewHolder(View itemLayoutView){
            super(itemLayoutView);

            nome = (TextView) itemLayoutView.findViewById(R.id.descricao_contato);
        }

    }

    @Override
    public int getItemCount() {
        return contatos.size();
    }

}

