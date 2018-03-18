package com.bonapp.ujm.myapplication.Model;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bonapp.ujm.myapplication.Controller.PageRestaurant;
import com.bonapp.ujm.myapplication.R;

import java.text.SimpleDateFormat;
import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by Nianfo on 08/02/2018.
 */

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ReservationViewHolder> {

    List<Reservation> list;
    Context context;


    public ReservationAdapter(List<Reservation> list, Context context){
        this.list = list;
        this.context = context;
    }
    @Override
    public ReservationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.reservation,parent,false);
        return new ReservationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReservationViewHolder holder, int position) {
        Reservation reserv = this.list.get(position);
        holder.display(reserv);
    }



    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ReservationViewHolder extends RecyclerView.ViewHolder {
        public TextView nom;
        public TextView date;
        public  TextView nbper;
        public  TextView nbpertext;
        public TextView jour;
        public Reservation current;

        public ReservationViewHolder (final View itemView) {
            super(itemView);
            nom = (TextView) itemView.findViewById(R.id.reservationRestoNom);
            date= (TextView) itemView.findViewById(R.id.reservationDate);
            nbper = (TextView) itemView.findViewById(R.id.ReservNbper);
            //jour = (TextView) itemView.findViewById(R.id.jour);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), PageRestaurant.class);
                    startActivity(view.getContext(),intent, null);

                }
            });
        }

        //@SuppressLint("ResourceType")
        public void display(Reservation rest){
            current = rest;


            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            RepoRestaurant rep = new RepoRestaurant(context);
            rep.open();
            String nomm = rep.selectionnerAccueil(rest.getId_restau()).getNom();
            String datee = dateFormat.format(rest.getDate());
            nom.setText(nomm);
            date.setText(datee+" à "+rest.getHeure());
            nbper.setText(""+rest.getNb_personnes());
            //jour.setText(rest.heure);
        }
    }
}
