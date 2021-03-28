package fr.iut.projetmemoryjaminonlomege;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
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

    SQLiteHelper db;
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

        //Initialisation et récupération des données
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

        //Démarre une étape lorsque l'on appuie sur le bouton start
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start.setClickable(false);
                start.setAlpha(0.5f);
                etapeOrdi();

                //Permet à l'utilisateur de cliquer et vérifie si c'est la bonne couleur
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

        //Gestion du bouton retour pour revenir au choix des modes.
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Niveau7.this, Menu.class);
                Bundle bundle=new Bundle();
                Intent intent2=getIntent();
                Bundle bundle2=intent2.getExtras();
                bundle.putString("mail", bundle2.getString("mail"));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    //Fonction permettant de récupérer les boutons grâce à leurs ids et de les ajouters à une liste de boutons.
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

    //Fonction permettant de faire une étape coté ordinateur (Choix d'un boutons puis affichage et note de musique)
    public void etapeOrdi() {

        final MediaPlayer yw = MediaPlayer.create(this,R.raw.yellow);
        final MediaPlayer bl = MediaPlayer.create(this,R.raw.blue);
        final MediaPlayer rd = MediaPlayer.create(this,R.raw.red);
        final MediaPlayer grn = MediaPlayer.create(this,R.raw.green);
        final MediaPlayer org = MediaPlayer.create(this,R.raw.orang);
        final MediaPlayer pk = MediaPlayer.create(this,R.raw.rose);
        final MediaPlayer cy = MediaPlayer.create(this,R.raw.cyan);
        final MediaPlayer brw = MediaPlayer.create(this,R.raw.marron);
        final MediaPlayer drkgrn = MediaPlayer.create(this,R.raw.vertfonce);
        final MediaPlayer blk = MediaPlayer.create(this,R.raw.black);

        if(!recommenceEtape)
            rand();
        etape++;
        recommenceEtape=false;
        int temps=1;
        Handler handler = new Handler();
        for(int choix : ordi) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    switch (choix) {
                        case 1:
                            AllumageBouton(rouge);
                            rd.start();
                            break;
                        case 2:
                            AllumageBouton(vert);
                            grn.start();
                            break;
                        case 3:
                            AllumageBouton(bleu);
                            bl.start();
                            break;
                        case 4:
                            AllumageBouton(jaune);
                            yw.start();
                            break;
                        case 5:
                            AllumageBouton(orange);
                            org.start();
                            break;
                        case 6:
                            AllumageBouton(pink);
                            pk.start();
                            break;
                        case 7:
                            AllumageBouton(cyan);
                            cy.start();
                            break;
                        case 8:
                            AllumageBouton(brown);
                            brw.start();
                            break;
                        case 9:
                            AllumageBouton(darkgreen);
                            drkgrn.start();
                            break;
                        case 10:
                            AllumageBouton(black);
                            blk.start();
                            break;
                    }
                }
            },1000*temps);
            temps++;
        }
    }

    //Fonction permettant de choisir un bouton grâce à son indice, elle ajoute ce choix à une liste
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
    }

    //Fonction permettant l'allumage des boutons, progressivement elle modifie l'alpha de chaque bouton pour donner une impression de clignotement.
    private void AllumageBouton(Button button) {

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

    //Fonction permettant l'activation ou la désactivation des boutons en fonction du bouléen que l'on met en paramètre.
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

    //Fonction permettant de vérifier si l'indice du bouton envoyer est bien le même que celui choisit précédemment par l'ordinateur.
    //Si c'est le cas alors on augmente la position pour vérifier les blocs suivants, sinon on pert une vie et recommence l'étape.
    //Si la position correspond au nombre de bloc dans l'étape alors on passe a l'étape suivante.
    private void verifieBouton(int indiceBouton) {
        if (indiceBouton == ordi.get(position)) {
            position++;
        } else {
            viesRestantes--;
            recommenceEtape=true;
            etape--;
            if (viesRestantes <= 0)
                vivant = false;
        }
        if(etape-1==position||!vivant||recommenceEtape){
            nouvelleEtape();
        }
    }

    //Fonction qui gère la fin d'une étape et le début d'une nouvelle.
    //Si l'etape était la dernière alors on calcul le score puis on passe au niveau suivant.
    // Sinon on passe a l'étape suivante.
    //Si l'on a plus de vie alors on recommence le niveau.
    private void nouvelleEtape(){
        if(vivant){
            if(etape>nbFinBloc){
                score=7*poids;
                Intent intent2=getIntent();
                Bundle bundle2=intent2.getExtras();
                db = new SQLiteHelper(getApplicationContext());
                db.UpdateScore(bundle2.getString("mail"),score);
                Toast.makeText(Niveau7.this,"Bravo! tu as réussi tous les niveaux, tu as gagné "+score+" points", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Niveau7.this,Menu.class);
                Bundle bundle=new Bundle();
                bundle.putString("mail", bundle2.getString("mail"));
                intent.putExtras(bundle);
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
            Intent intent2=getIntent();
            Bundle bundle2=intent2.getExtras();
            bundle.putString("mail", bundle2.getString("mail"));
            intent.putExtras(bundle);

            startActivity(intent);
        }
    }

}