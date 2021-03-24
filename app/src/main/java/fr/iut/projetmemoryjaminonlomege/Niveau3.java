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

public class Niveau3 extends AppCompatActivity {

    Button rouge,vert,bleu,jaune,orange,pink;
    Button start;
    Button retour;
    TextView textview_vies, textview_etape;
    List<Integer> ordi;
    List<Button> boutons;
    int vies,viesRestantes, nbDepartBloc, nbFinBloc,etape;
    int position = 0;
    boolean vivant,recommenceEtape;
    double score,poids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_niveau3);

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
                Intent intent = new Intent(Niveau3.this, Menu.class);
                Bundle bundle=new Bundle();
                Intent intent2=getIntent();
                Bundle bundle2=intent2.getExtras();
                bundle.putString("mail", bundle2.getString("mail"));
                intent.putExtras(bundle);
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
        start = findViewById(R.id.id_bouton_start);
        retour=findViewById(R.id.id_bouton_retour);
        boutons.add(rouge);
        boutons.add(vert);
        boutons.add(bleu);
        boutons.add(jaune);
        boutons.add(orange);
        boutons.add(pink);

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
                choix = choixOrdi.nextInt(5);
                choix++;
                ordi.add(choix);
            }
        }else{
            choix = choixOrdi.nextInt(5);
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
                Intent intent2=getIntent();
                Bundle bundle2=intent2.getExtras();
                //ajouter score a la bdd
                Toast.makeText(Niveau3.this,"Bravo! Vous avez passer le niveau 3, vous gagnez "+score+" points", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Niveau3.this,Niveau4.class);

                Bundle bundle=new Bundle();
                bundle.putInt("nbDepartBloc",nbDepartBloc);
                bundle.putInt("nbFinBloc",nbFinBloc);
                bundle.putInt("vies",vies);
                bundle.putDouble("poids",poids);
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
            Toast.makeText(Niveau3.this,"Perdu, vous recommencez le niveau!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Niveau3.this,Niveau3.class);

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