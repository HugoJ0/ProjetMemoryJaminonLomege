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

public class Niveau5 extends AppCompatActivity {

    SQLiteHelper db;
    Button rouge,vert,bleu,jaune,orange,pink,cyan,brown;
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
        setContentView(R.layout.activity_niveau5);

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
                Intent intent = new Intent(Niveau5.this, Menu.class);
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
        cyan=findViewById(R.id.id_bouton_couleur_7);
        brown=findViewById(R.id.id_bouton_couleur_8);
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

    }

    public void etapeOrdi() {
        final MediaPlayer yw = MediaPlayer.create(this,R.raw.yellow);
        final MediaPlayer bl = MediaPlayer.create(this,R.raw.blue);
        final MediaPlayer rd = MediaPlayer.create(this,R.raw.red);
        final MediaPlayer grn = MediaPlayer.create(this,R.raw.green);
        final MediaPlayer org = MediaPlayer.create(this,R.raw.orang);
        final MediaPlayer pk = MediaPlayer.create(this,R.raw.rose);
        final MediaPlayer cy = MediaPlayer.create(this,R.raw.cyan);
        final MediaPlayer brw = MediaPlayer.create(this,R.raw.marron);

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
                choix = choixOrdi.nextInt(7);
                choix++;
                ordi.add(choix);
            }
        }else{
            choix = choixOrdi.nextInt(7);
            choix++;
            ordi.add(choix);
        }
    }

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
            etape--;
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
                score=5*poids;
                Intent intent2=getIntent();
                Bundle bundle2=intent2.getExtras();
                db = new SQLiteHelper(getApplicationContext());
                db.UpdateScore(bundle2.getString("mail"),score);
                Toast.makeText(Niveau5.this,"Bravo! Vous avez passer le niveau 5, vous gagnez "+score+" points", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Niveau5.this,Niveau6.class);

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
            Toast.makeText(Niveau5.this,"Perdu, vous recommencez le niveau!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Niveau5.this,Niveau5.class);

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