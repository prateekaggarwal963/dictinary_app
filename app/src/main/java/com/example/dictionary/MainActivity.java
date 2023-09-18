package com.example.dictionary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dictionary.Adapter.MeaningAdapter;
import com.example.dictionary.Adapter.PhoneticAdapter;
import com.example.dictionary.Models.APIResponse;

public class MainActivity extends AppCompatActivity {
SearchView searchView;
TextView textView_word;
RecyclerView recycler_phonetics,recycler_meanings;
ProgressDialog progressDialog;
PhoneticAdapter phoneticAdapter;
MeaningAdapter meaningAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchView=findViewById(R.id.search_view);
        textView_word=findViewById(R.id.textView_word);
        recycler_phonetics=findViewById(R.id.recycler_phonetics);
        recycler_meanings=findViewById(R.id.recycler_meanings);
        progressDialog=new ProgressDialog(this);

        //when user enter for first time
        progressDialog.setTitle("Loading...");
        progressDialog.show();
        //calling an api
        RequestManager requestManager= new RequestManager(MainActivity.this);
        requestManager.getWordMeaning(listener,"hello");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                progressDialog.setTitle("Fetching response for ..."+query);
                progressDialog.show();
                //calling an api
                RequestManager requestManager= new RequestManager(MainActivity.this);
                requestManager.getWordMeaning(listener,query);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
    private final OnFetchDataListener listener = new OnFetchDataListener() {
        @Override
        public void onFetchData(APIResponse apiResponse, String message) {
            progressDialog.dismiss();
            if (apiResponse==null)
            {
                Toast.makeText(MainActivity.this, "No data found", Toast.LENGTH_SHORT).show();
                return;
            }
            showData(apiResponse);
        }


        @Override
        public void onError(String message) {
            progressDialog.dismiss();
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

    private void showData(APIResponse apiResponse) {
        textView_word.setText("Word: "+apiResponse.getWord());
        recycler_phonetics.setHasFixedSize(true);
        recycler_phonetics.setLayoutManager(new GridLayoutManager(this,1));
        phoneticAdapter=new PhoneticAdapter(this,apiResponse.getPhonetics());
        recycler_phonetics.setAdapter(phoneticAdapter);
        //for meanings
        recycler_meanings.setHasFixedSize(true);
        recycler_meanings.setLayoutManager(new GridLayoutManager(this,1));
        meaningAdapter=new MeaningAdapter(this,apiResponse.getMeanings());
        recycler_meanings.setAdapter(meaningAdapter);
    }
}