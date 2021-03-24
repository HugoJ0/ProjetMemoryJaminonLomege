package fr.iut.projetmemoryjaminonlomege;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Niveau7 extends AppCompatActivity {

    Button rouge,vert,bleu,jaune,orange,pink,cyan,brown,darkgreen,black;
    Button start;
    Button retour;
    TextView textview_vies, textview_etape;
    List<Integer> ordi;
    List<Button> boutons;
    int vies,viesRestantes, nbDepartBloc, nbFinBloc,etape;
    int position = 0;
    boolean vivant,recommenceEtape;
    double poids,score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_niveau7);

        boutons=new ArrayList<Button>();
        recupererBoutons();
        activerBoutons(false);

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        vies=bundle.getInt("vies");
        viesRestantes=vies;
        vivant=true;
        nbDepartBloc=bundle.getInt("nbDepartBloc");
        nbFinBloc=bundle.getInt("nbFinBloc");
        poids=bundle.getDouble("poids");
        etape=nbDepartBloc;
        ordi= new ArrayList<Integer>();
        recommenceEtape=false;

        textview_vies=findViewById(R.id.id_textview_vies);
        textview_etape=findViewById(R.id.id_textview_etape);
        textview_etape.setText(etape+" / "+nbFinBloc);
        textview_vies.setText(viesRestantes+" / "+vies);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start.setClickable(false);
                start.setAlpha(0.5f);
                etapeOrdi();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        activerBoutons(true);
                        int i=1;
                        for (final Button bouton : boutons) {
                            final int indiceBouton=i;
                            bouton.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View view) {
                                    verifieBouton(indiceBouton);
                                }

                            });
                            i++;
                        }
                    }
                },1000*etape+1000);
            }
        });

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Niveau7.this, Menu.class);
                startActivity(intent);
            }
        });
    }

    private void recupererBoutons() {
        rouge = findViewById(R.id.id_bouton_couleur_1);
        vert = findViewById(R.id.id_bouton_couleur_2);
        bleu = findViewById(R.id.id_bouton_couleur_3);
        jaune = findViewById(R.id.id_bouton_couleur_4);
        orange=findViewById(R.id.id_bouton_couleur_5);
        pink=findViewById(R.id.id_bouton_couleur_6);
        cyan=findViewById(R.id.id_bouton_couleur_7);
        brown=findViewById(R.id.id_bouton_couleur_8);
        darkgreen=findViewById(R.id.id_bouton_couleur_9);
        black=findViewById(R.id.id_bouton_couleur_10);
        start = findViewById(R.id.id_bouton_start);
        retour=findViewById(R.id.id_bouton_retour);
        boutons.add(rouge);
        boutons.add(vert);
        boutons.add(bleu);
        boutons.add(jaune);
        boutons.add(orange);
        boutons.add(pink);
        boutons.add(cyan);
        boutons.add(brown);
        boutons.add(darkgreen);
        boutons.add(black);

    }

    public void etapeOrdi() {
        if(!recommenceEtape)
            rand();
        recommenceEtape=false;
        int temps=1;
        Handler handler = new Handler();
        for(int choix : ordi) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    switch (choix) {
                        case 1:
                            colorHighlight(rouge);
                            break;
                        case 2:
                            colorHighlight(vert);
                            break;
                        case 3:
                            colorHighlight(bleu);
                            break;
                        case 4:
                            colorHighlight(jaune);
                            break;
                        case 5:
                            colorHighlight(orange);
                            break;
                        case 6:
                            colorHighlight(pink);
                            break;
                        case 7:
                            colorHighlight(cyan);
                            break;
                        case 8:
                            colorHighlight(brown);
                            break;
                        case 9:
                            colorHighlight(darkgreen);
                            break;
                        case 10:
                            colorHighlight(black);
                            break;
                    }
                }
            },1000*temps);
            temps++;
        }
    }

    private void rand(){
        Random choixOrdi = new Random();
        int choix;
        if(etape==nbDepartBloc){
            for(int i=0;i<nbDepartBloc;i++){
                choix = choixOrdi.nextInt(9);
                choix++;
                ordi.add(choix);
            }
        }else{
            choix = choixOrdi.nextInt(9);
            choix++;
            ordi.add(choix);
        }
        etape++;
    }

    private void colorHighlight(Button button) {

        final Button b = button;

        b.postDelayed(new Runnable() {
            @Override
            public void run() {
                b.setAlpha(0.5f);
            }

        }, 0);
        b.postDelayed(new Runnable() {
            @Override
            public void run() {
                b.setAlpha(0.75f);
            }
        }, 50);
        b.postDelayed(new Runnable() {
            @Override
            public void run() {
                b.setAlpha(1f);
            }
        }, 100);
        b.postDelayed(new Runnable() {
            @Override
            public void run() {
                b.setAlpha(0.75f);
            }
        }, 250);
        b.postDelayed(new Runnable() {
            @Override
            public void run() {
                b.setAlpha(0.5f);
            }
        }, 300);
    }

    private void activerBoutons(boolean bool){
        for (final Button bouton : boutons) {
            bouton.setClickable(bool);
        }
        if(bool){
            for (final Button bouton : boutons) {
                bouton.setAlpha(1f);
            }
        }else{
            for (final Button bouton : boutons) {
                bouton.setAlpha(0.5f);
            }
        }
    }

    private void verifieBouton(int indiceBouton) {
        if (indiceBouton == ordi.get(position)) {
            position++;
        } else {
            viesRestantes--;
            recommenceEtape=true;
            if (viesRestantes <= 0)
                vivant = false;
        }
        if(etape-1==position||!vivant||recommenceEtape){
            nouvelleEtape();
        }
    }

    private void nouvelleEtape(){
        if(vivant){
            if(etape>nbFinBloc){
                score=1*poids;
                //ajouter score a la bdd
                Toast.makeText(Niveau7.this,"Bravo! tu as réussi tous les niveaux, tu as gagné "+score+" points", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Niveau7.this,Menu.class);
                startActivity(intent);
            }else{
                position=0;
                start.setClickable(true);
                start.setAlpha(1f);
                activerBoutons(false);
                textview_etape.setText(etape+" / "+nbFinBloc);
                textview_vies.setText(viesRestantes+" / "+vies);
            }
        }else{
            Toast.makeText(Niveau7.this,"Perdu, vous recommencez le niveau!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Niveau7.this,Niveau7.class);

            Bundle bundle=new Bundle();
            bundle.putInt("nbDepartBloc",nbDepartBloc);
            bundle.putInt("nbFinBloc",nbFinBloc);
            bundle.putInt("vies",vies);
            bundle.putDouble("poids",poids);
            intent.putExtras(bundle);

            startActivity(intent);
        }
    }

}