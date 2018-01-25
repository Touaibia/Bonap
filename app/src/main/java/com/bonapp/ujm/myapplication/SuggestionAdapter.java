package com.bonapp.ujm.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Nianfo on 21/01/2018.
 */

public class SuggestionAdapter extends BaseAdapter {
    private List<Restaurant> restauts;
    private Context context;
    private LayoutInflater inflater;
    SuggestionAdapter(Context context, List ListRestaus){
        this.context =context;
        this.restauts = ListRestaus;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return restauts.size();
    }

    @Override
    public Object getItem(int i) {
        return restauts.get(i);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view == null){
            view =  inflater.inflate(R.layout.restaurants,viewGroup,false);
        }

        TextView Nom = (TextView) view.findViewById(R.id.NomRest);
        ImageView img = (ImageView) view.findViewById(R.id.imageRestau);

        Nom.setText(restauts.get(i).nom);
        img.setImageResource(restauts.get(i).image);
        return view;
    }
}
