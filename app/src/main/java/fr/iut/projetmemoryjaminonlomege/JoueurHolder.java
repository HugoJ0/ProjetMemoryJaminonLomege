package fr.iut.projetmemoryjaminonlomege;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

public class JoueurHolder extends RecyclerView.ViewHolder {

    private TextView rangTextView;
    private TextView pseudoTextView;
    private TextView scoreTextView;

    public JoueurHolder(@NonNull View itemView) {
        super(itemView);

        rangTextView=itemView.findViewById(R.id.id_textview_rang);
        pseudoTextView=itemView.findViewById(R.id.id_textview_pseudo);
        scoreTextView=itemView.findViewById(R.id.id_textview_score);
    }

    public void visualiserJoueur(Joueur unJoueur){
        rangTextView.setText(unJoueur.getRang());
        pseudoTextView.setText(unJoueur.getPseudo());
        scoreTextView.setText(unJoueur.getScore());
    }

}
