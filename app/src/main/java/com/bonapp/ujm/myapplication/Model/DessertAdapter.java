package com.bonapp.ujm.myapplication.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bonapp.ujm.myapplication.R;

import java.util.List;

/**
 * Created by Nianfo on 06/02/2018.
 */

public class DessertAdapter extends BaseAdapter {

    private List<Plat> plat;
    private Context context;
    private LayoutInflater inflater;
    DessertAdapter(Context context, List Listplats){
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
        img.setImageBitmap(plat.get(i).image.getBitmap());
        return view;
    }
}
