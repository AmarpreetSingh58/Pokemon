package com.amarpreetsinghprojects.pokemon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<PokeList> pokeListArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText searchETV = (EditText)findViewById(R.id.searcheditText);
        Button searchbutton = (Button)findViewById(R.id.searchButton);
        recyclerView = (RecyclerView)findViewById(R.id.recylerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        final PokeAdapter pokeAdapter = new PokeAdapter(pokeListArrayList);
        recyclerView.setAdapter(pokeAdapter);


        searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://pokeapi.co/api/v2/pokemon/"+searchETV.getText().toString()+"/";
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(url)
                        .build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String result = response.body().string();
                        Gson gson = new Gson();
                        PokeList pokeList = gson.fromJson(result,PokeList.class);
                        pokeListArrayList.add(pokeList);
                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                pokeAdapter.notifyDataSetChanged();
                            }
                        });
                    }
                });

            }
        });

    }
}
