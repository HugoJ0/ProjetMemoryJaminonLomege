package fr.iut.projetmemoryjaminonlomege;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class JoueurAdapter extends RecyclerView.Adapter<JoueurHolder>{

    private List<Joueur> listeJoueurs;

    public JoueurAdapter(List<Joueur> listeJoueurs){
        this.listeJoueurs=listeJoueurs;
    }

    @NonNull
    @Override
    public JoueurHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater monLayoutInflater=LayoutInflater.from(parent.getContext());
        View maView=monLayoutInflater.inflate(R.layout.layout_ligne_joueur,parent,false);

        return new JoueurHolder(maView);
    }

    @Override
    public void onBindViewHolder(@NonNull JoueurHolder holder, int position) {
        holder.visualiserJoueur(listeJoueurs.get(position));
    }

    @Override
    public int getItemCount() {
        return this.listeJoueurs.size();
    }
}
