package com.bonapp.ujm.myapplication.Model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bonapp.ujm.myapplication.Controller.GestionEvent;
import com.bonapp.ujm.myapplication.R;

import org.w3c.dom.Text;

import java.util.ArrayList;


/**
 * Created by maham on 05/03/2018.
 */

public class LesResevationsJourAdapter extends RecyclerView.Adapter<LesResevationsJourAdapter.MyViewHolder> {

    ArrayList<Reservation> lesReserv;
    Context context;

    public LesResevationsJourAdapter(Context context, ArrayList<Reservation> lesReserv) {
        this.lesReserv = lesReserv;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.reservation_du_jour, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Reservation res = lesReserv.get(position);
        //Log.d("Heure Arrivée ", res.getHeure());
        holder.display(res);
    }

    @Override
    public int getItemCount() {
        return lesReserv.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nomCl;
        TextView nbPers;
        TextView heure;

        public MyViewHolder(View itemView) {
            super(itemView);
            nomCl = (TextView) itemView.findViewById(R.id.id_client);
            nbPers = (TextView) itemView.findViewById(R.id.nb_pers);
            heure = (TextView) itemView.findViewById(R.id.heure_arv);
        }

        public void display(Reservation res) {
            nomCl.setText(res.getClient().getUsername());
            nbPers.setText(res.getNb_personnes()+" Personnes");
            heure.setText(res.getHeure());
        }
    }
}
