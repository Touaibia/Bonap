package com.bonapp.ujm.myapplication.Controller;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.bonapp.ujm.myapplication.Model.RepoReservation;
import com.bonapp.ujm.myapplication.Model.Reservation;
import com.bonapp.ujm.myapplication.R;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static java.lang.Integer.parseInt;

/**
 * Created by Nianfo on 13/03/2018.
 */

public class fait_la_reservation extends AppCompatActivity implements View.OnClickListener {

    private TextView textViewdate;
    private TextView textViewtime;
    int nbp=0;
    DatePickerDialog.OnDateSetListener dateSetListener;
    TimePickerDialog.OnTimeSetListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fait_la_reservation);
        TextView nom = (TextView) findViewById(R.id.ReserveRestauNom);
        Intent intent = getIntent();
        nom.setText(intent.getStringExtra("restau"));
       // Toast.makeText(this,""+intent.getLongExtra("id",-1),Toast.LENGTH_LONG).show();

    }


    @Override
    public void onClick(View view) {
    switch (view.getId()) {
        case R.id.ReservDate:

            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialog = new DatePickerDialog(
                    this,
                    android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                    dateSetListener,
                    year, month, day
            );
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

            dateSetListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                    month = month + 1;
                    String datee = day + "/" + month + "/" + year;
                    textViewdate = (TextView) findViewById(R.id.ReservDate);
                    textViewdate.setText(datee);
                }
            };

            break;
        case R.id.ReservtimeDate:
            Calendar cale = Calendar.getInstance();
            int heure = cale.get(Calendar.HOUR_OF_DAY);
            int min = cale.get(Calendar.MINUTE);

            TimePickerDialog dialogtime = new TimePickerDialog(
                    this, listener,
                    heure, min, true
            );
            dialogtime.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialogtime.show();

            listener = new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int i, int i1) {
                    String time = i + ":" + i1+":"+00;
                    textViewtime = (TextView) findViewById(R.id.ReservtimeDate);
                    textViewtime.setText(time);

                }

            };

            break;

        case R.id.pluspersonne:
            TextView nbpersonne = (TextView) findViewById(R.id.ReserveNbpersonne);
            nbp += 1;
            String s = "Nombre de personnes :" + nbp;
            nbpersonne.setText(s);
            break;

        case R.id.moinspersonne:
            TextView nbpersonne1 = (TextView) findViewById(R.id.ReserveNbpersonne);
            if(nbp>0) nbp -= 1;
            String s1 = "Nombre de personnes :" + nbp;
            nbpersonne1.setText(s1);
            break;

        case R.id.ReserveValide:
            TextView nbp = (TextView) findViewById(R.id.ReserveNbpersonne);
            TextView date = (TextView) findViewById(R.id.ReservDate);
            TextView time = (TextView) findViewById(R.id.ReservtimeDate);
            CheckBox midi = (CheckBox) findViewById(R.id.ServiceMidi);
            CheckBox soire = (CheckBox) findViewById(R.id.ServiceSoire);

            if(midi.isChecked()){

            }
            String nbptext = nbp.getText().toString();
            String[] p = nbptext.split(":");

            String datetext = date.getText().toString();

            String timetext = time.getText().toString();
            Intent intent = getIntent();
            long id = intent.getLongExtra("id",-1);
            Reservation reservation = new Reservation();

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            java.util.Date date1 = null;
            try {
                date1 = sdf.parse(datetext);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            reservation.setDate(date1);
            Intent in = getIntent();
            long idr = in.getLongExtra("idrestau",-1);
            long idc = in.getLongExtra("idclient",-1);

            if(idr!=-1 && idc!=-1){
                reservation.setId_restau((int)idr);
                reservation.setId_client((int)idc);
                reservation.setHeure(timetext);
                Toast.makeText(this," test "+parseInt(p[1]),Toast.LENGTH_LONG).show();
                reservation.setNb_personnes(parseInt(p[1]));
                if(midi.isChecked()){
                    reservation.setService(0);
                }
                if(soire.isChecked()){
                    reservation.setService(1);
                }

                RepoReservation repo = new RepoReservation(this);
                repo.open();
              long id1 = repo.ajouter(reservation, datetext);
                repo.close();
            }
            else {
                Toast.makeText(this,"Erreur d'enregistrement merci de reprendre",Toast.LENGTH_LONG).show();
            }




            break;
    }

    }
}
