package com.amarpreetsinghprojects.pokemon;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by kulvi on 07/06/17.
 */

public class FragmentImage extends Fragment {


    public static FragmentImage newInstance(String avatar) {

        Bundle args = new Bundle();
        args.putString("url",avatar);

        FragmentImage fragment = new FragmentImage();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_poke_image,container,false);
        ImageView imageView = (ImageView) v.findViewById(R.id.avatarimageView);
        Picasso.with(container.getContext()).load(savedInstanceState.getString("url")).resize(1000,1000).into(imageView);
        return v;
    }
}
