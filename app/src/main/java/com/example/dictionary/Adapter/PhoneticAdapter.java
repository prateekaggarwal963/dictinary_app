package com.example.dictionary.Adapter;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.ContentInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionary.Models.Phonetic;
import com.example.dictionary.R;

import java.util.List;

public class PhoneticAdapter extends RecyclerView.Adapter<PhoneticAdapter.ViewHolder> {

    private Context context;
    private List<Phonetic> phoneticsList;

    public PhoneticAdapter(Context context, List<Phonetic> phoneticsList) {
        this.context = context;
        this.phoneticsList = phoneticsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.phonetic_list_items,parent,false);
        return new PhoneticAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView_phonetic.setText(phoneticsList.get(position).getText());
        holder.imageButton_audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer mediaPlayer = new MediaPlayer();
                try{
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.setDataSource(phoneticsList.get(position).getAudio());
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                }catch (Exception e)
                {
                    e.printStackTrace();
                    Toast.makeText(context,e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return phoneticsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView_phonetic;
        public ImageButton imageButton_audio;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_phonetic=itemView.findViewById(R.id.textView_phonetic);
            imageButton_audio=itemView.findViewById(R.id.imageButton_audio);
        }
    }
}
