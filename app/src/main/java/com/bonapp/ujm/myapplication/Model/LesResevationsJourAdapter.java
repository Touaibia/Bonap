package com.bonapp.ujm.myapplication.Model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * Created by maham on 05/03/2018.
 */

public class LesResevationsJourAdapter extends RecyclerView.Adapter<LesResevationsJourAdapter.MyViewHolder> {

    ArrayList<Reservation> lesReserv = new ArrayList<>();

    public LesResevationsJourAdapter(ArrayList<Reservation> lesReserv) {
        this.lesReserv = lesReserv;
    }

    @Override
    public LesResevationsJourAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(LesResevationsJourAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
