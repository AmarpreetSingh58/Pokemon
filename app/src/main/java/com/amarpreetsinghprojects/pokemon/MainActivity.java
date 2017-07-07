package com.amarpreetsinghprojects.pokemon;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
    TextView name,weight,height;
    PokeList pokeList;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        final EditText searchETV = (EditText)findViewById(R.id.searcheditText);
        Button searchbutton = (Button)findViewById(R.id.searchButton);
        name = (TextView)findViewById(R.id.nameTV);
        weight = (TextView)findViewById(R.id.weightTV);
        height = (TextView)findViewById(R.id.heightTV);
        viewPager = (ViewPager)findViewById(R.id.ImageViewPager);

        LinearLayout f = (LinearLayout) getLayoutInflater().inflate(R.layout.dialog_layout,null);
        final EditText searchedit = (EditText)f.findViewById(R.id.searchETV);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Pokemon").setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,searchedit.getText(),Toast.LENGTH_SHORT).show();
                    }
                })
                .setView(f)
                .create();


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
                        //Toast.makeText(MainActivity.this,"Connection Failure",Toast.LENGTH_LONG);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String result = response.body().string();
                        Gson gson = new Gson();
                         pokeList = gson.fromJson(result,PokeList.class);

                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                setContents(pokeList);
                            }
                        });

                        }
                    }
                );

            }
        });

    }

    public void setContents(PokeList pokeList){

        name.setText(pokeList.getName());
        weight.setText(pokeList.getName());
        height.setText(pokeList.getHeight());
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);

    }

    public class ViewPagerAdapter extends FragmentPagerAdapter {
        int count=0;
        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
            if (pokeList.getSprites().back_default != "null"){
                count++;
            }
            if (pokeList.getSprites().front_default != "null"){
                count++;
            }
            if (pokeList.getSprites().back_shiny != "null"){
                count++;
            }
            if (pokeList.getSprites().front_shiny != "null"){
                count++;
            }
            if (pokeList.getSprites().back_female != "null"){
                count++;
            }
            if (pokeList.getSprites().front_female != "null"){
                count++;
            }
            if (pokeList.getSprites().front_shiny_female != "null"){
                count++;
            }
            if (pokeList.getSprites().back_shiny_female != "null"){
                count++;
            }
        }

        @Override
        public Fragment getItem(int position) {



            Fragment fragment;
            switch (position){
                case 0: fragment= new FragmentImage().newInstance(pokeList.getSprites().front_default);
                    break;
                case 1: fragment = new FragmentImage().newInstance(pokeList.getSprites().back_default);
                    break;
                case 2: fragment = new FragmentImage().newInstance(pokeList.getSprites().back_shiny);
                    break;
                case 3: fragment = new FragmentImage().newInstance(pokeList.getSprites().front_shiny);
                    break;
                case 4: fragment = new FragmentImage().newInstance(pokeList.getSprites().back_female);
                    break;
                case 5: fragment = new FragmentImage().newInstance(pokeList.getSprites().front_female);
                    break;
                case 6: fragment = new FragmentImage().newInstance(pokeList.getSprites().back_shiny_female);
                    break;
                case 7: fragment = new FragmentImage().newInstance(pokeList.getSprites().front_shiny_female);
                    break;
                default: fragment = null;

            }
            return fragment;
        }

        @Override
        public int getCount() {
            return count;
        }
    }
}
