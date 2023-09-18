package com.example.dictionary.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionary.Models.Meaning;
import com.example.dictionary.R;

import java.util.List;

public class MeaningAdapter extends RecyclerView.Adapter<MeaningAdapter.ViewHolder> {
    private Context context;
    private List<Meaning> meaningList;

    public MeaningAdapter(Context context, List<Meaning> meaningList) {
        this.context = context;
        this.meaningList = meaningList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.meaning_list_items, parent, false);
        return new MeaningAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView_partsOfSpeech.setText("Parts of Speech : " + meaningList.get(position).getPartOfSpeech());
        holder.recycler_definitions.setHasFixedSize(true);
        holder.recycler_definitions.setLayoutManager(new GridLayoutManager(context,1));
        DefinitionAdapter definitionAdapter = new DefinitionAdapter(context,meaningList.get(position).getDefinitions());
        holder.recycler_definitions.setAdapter(definitionAdapter);
    }

    @Override
    public int getItemCount() {
        return meaningList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView_partsOfSpeech;
        RecyclerView recycler_definitions;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_partsOfSpeech = itemView.findViewById(R.id.textView_partsOfSpeech);
            recycler_definitions = itemView.findViewById(R.id.recycler_definitions);
        }
    }
}
