package com.example.dictionary.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionary.Models.Definition;
import com.example.dictionary.R;

import java.util.List;

public class DefinitionAdapter extends RecyclerView.Adapter<DefinitionAdapter.ViewHolder> {
    private Context context;
    private List<Definition> definitions;

    public DefinitionAdapter(Context context, List<Definition> definitions) {
        this.context = context;
        this.definitions = definitions;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.definitions_list_items, parent, false);
        return new DefinitionAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView_definition.setText("Definition " + definitions.get(position).getDefinition());
        holder.textview_example.setText("Definition List" + definitions.get(position).getExample());
        StringBuilder synonyms = new StringBuilder();
        StringBuilder antonyms = new StringBuilder();
        synonyms.append(definitions.get(position).getSynonyms());
        antonyms.append(definitions.get(position).getAntonyms());
        holder.textView_synonyms.setText(synonyms);
        holder.textView_antonyms.setText(antonyms);
        holder.textView_synonyms.setSelected(true);
        holder.textView_antonyms.setSelected(true);
    }

    @Override
    public int getItemCount() {
        return definitions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView_definition, textview_example, textView_synonyms, textView_antonyms;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_definition = itemView.findViewById(R.id.textView_definition);
            textview_example = itemView.findViewById(R.id.textview_example);
            textView_synonyms = itemView.findViewById(R.id.textView_synonyms);
            textView_antonyms = itemView.findViewById(R.id.textView_antonyms);
        }
    }
}
