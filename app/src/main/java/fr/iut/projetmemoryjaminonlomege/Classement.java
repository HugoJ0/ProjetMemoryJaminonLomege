package fr.iut.projetmemoryjaminonlomege;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Classement extends AppCompatActivity {

    Button retour;
    SQLiteHelper db;
    String[][] liste;
    RecyclerView classementRecyclerView;
    List<Joueur> listeJoueurs;
    JoueurAdapter monJoueurAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classement);

        //Initialisation des différentes variables.
        db = new SQLiteHelper(getApplicationContext());

        liste = db.affClassement();
        initListe();
        classementRecyclerView=findViewById(R.id.id_recyclerview_listeclassement);
        monJoueurAdapter=new JoueurAdapter(listeJoueurs);

        classementRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        classementRecyclerView.setAdapter(monJoueurAdapter);


        //Gestion du bouton retour, renvoi vers le menu
        retour=findViewById(R.id.id_bouton_retour);
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Classement.this, Menu.class);
                Bundle bundle=new Bundle();
                Intent intent2=getIntent();
                Bundle bundle2=intent2.getExtras();
                bundle.putString("mail", bundle2.getString("mail"));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    //Fonction qui ajoute à une liste de Joueur les resultats de la base de donnée.
    public void initListe(){
        listeJoueurs=new ArrayList<Joueur>();
        Joueur joueurParDefault = new Joueur();
        listeJoueurs.add(joueurParDefault);
        for(int i=1; i<=liste.length;i++){
            if(liste[i-1][0]!=null){
                Joueur unJoueur = new Joueur(liste[i-1][0], liste[i-1][1], ""+i);
                listeJoueurs.add(unJoueur);
            }
        }
    }
}