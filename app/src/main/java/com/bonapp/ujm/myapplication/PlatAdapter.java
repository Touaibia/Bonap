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

public class PlatAdapter extends BaseAdapter {
    private List<Restaurant.Plats> plat;
    private Context context;
    private LayoutInflater inflater;
    PlatAdapter(Context context, List Listplats){
        this.context =context;
        this.plat = Listplats;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return plat.size();
    }

    @Override
    public Object getItem(int i) {
        return plat.get(i);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view == null){
            view =  inflater.inflate(R.layout.un_plat,viewGroup,false);
        }

        TextView Nom = (TextView) view.findViewById(R.id.nomPlat);
        ImageView img = (ImageView) view.findViewById(R.id.imagePlat);

        Nom.setText(plat.get(i).nom);
        img.setImageResource(plat.get(i).image);
        return view;
    }
}
