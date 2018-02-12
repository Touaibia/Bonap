package com.bonapp.ujm.myapplication.Model;

import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bonapp.ujm.myapplication.R;

import java.util.Arrays;
import java.util.List;

/**
 * Created by maham on 28/01/2018.
 */

public class PublicationAdapter extends RecyclerView.Adapter<PublicationAdapter.MyViewHolder> {

    private List<Pair<String,String>> lesPub = Arrays.asList(
            Pair.create("Lyra Belacqua", "Brave, curious, and crafty, she has been prophesied by the witches to help the balance of life"),
            Pair.create("Pantalaimon", "Lyra's daemon, nicknamed Pan."),
            Pair.create("Roger Parslow", "Lyra's friends"),
            Pair.create("Lord Asriel", "Lyra's uncle"),
            Pair.create("Marisa Coulter", "Intelligent and beautiful, but extremely ruthless and callous."),
            Pair.create("Iorek Byrnison", "Armoured bear, Rightful king of the panserbjørne. Reduced to a slave of the human village Trollesund."),
            Pair.create("Serafina Pekkala", "Witch who closely follows Lyra on her travels."),
            Pair.create("Lee Scoresby", "Texan aeronaut who transports Lyra in his balloon. Good friend with Iorek Byrnison."),
            Pair.create("Ma Costa", "Gyptian woman whose son, Billy Costa is abducted by the \"Gobblers\"."),
            Pair.create("John Faa", "The King of all gyptian people.")
    );

    @Override
    public int getItemCount() {
        return lesPub.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_publication, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Pair<String, String> pair = lesPub.get(position);
        holder.display(pair);
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView typePub;
        private TextView desc;
        private Pair<String,String> maPub;

        public MyViewHolder(View itemView){
            super(itemView);

            typePub = (TextView) itemView.findViewById(R.id.typePub);
            desc = (TextView) itemView.findViewById(R.id.description);

        }

        public void display(Pair<String, String> pair) {
            maPub = pair;
            typePub.setText(pair.first);
            desc.setText(pair.second);
        }

    }
}
