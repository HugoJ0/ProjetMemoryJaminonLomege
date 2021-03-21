package fr.iut.projetmemoryjaminonlomege;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Random;

public class Niveau1 extends AppCompatActivity {
    int vies, nbDepartBloc, nbFinBloc;
    Button couleur1,couleur2,couleur3,couleur4,retour;
    int [] tabIndice;
    int tabVerif[], tabJoueur[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_niveau1);

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        vies=bundle.getInt("vies");
        nbDepartBloc=bundle.getInt("nbDepartBloc");
        nbFinBloc=bundle.getInt("nbFinBloc");
        couleur1=findViewById(R.id.id_bouton_couleur_1);
        couleur2=findViewById(R.id.id_bouton_couleur_2);
        couleur3=findViewById(R.id.id_bouton_couleur_3);
        couleur4=findViewById(R.id.id_bouton_couleur_4);
        retour=findViewById(R.id.id_bouton_retour);
        tabIndice=new int[]{1,2,3,4};
        tabVerif=new int[nbFinBloc-nbDepartBloc+1];
        desactiverBoutons();
        etapeOrdiDepart();

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Niveau1.this, ChoixMode.class);
                startActivity(intent);
            }
        });
    }

    protected void activerBoutons(){
        couleur1.setClickable(true);
        couleur2.setClickable(true);
        couleur3.setClickable(true);
        couleur4.setClickable(true);
    }
    protected void desactiverBoutons(){
        couleur1.setClickable(false);
        couleur2.setClickable(false);
        couleur3.setClickable(false);
        couleur4.setClickable(false);
    }

    protected void etapeOrdi(){
        Random rand = new Random();
        int ind = rand.nextInt(tabIndice.length);
        for(int i=0; i < tabVerif.length; i++)
            if(tabVerif[i] == 0) {
                tabVerif[i] = tabIndice[ind];
                break;
            }
        Handler handler = new Handler();
        int temps=1;
        for(int i : tabVerif){
            Toast.makeText(Niveau1.this, "test "+i + "temps "+temps, Toast.LENGTH_SHORT).show();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(i==1){
                        couleur1.setText("ICI");
                    }
                    if(i==2){
                        couleur2.setText("ICI");
                    }
                    if(i==3){
                        couleur3.setText("ICI");
                    }
                    if(i==4){
                        couleur4.setText("ICI");
                    }
                }
            }, 1000*temps);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    couleur1.setText("");
                    couleur2.setText("");
                    couleur3.setText("");
                    couleur4.setText("");
                }
            }, 1000*temps+500);
            temps++;
            if(i==0){
                break;
            }
        }
    }

    protected void etapeOrdiDepart(){
        for(int j=0;j<nbDepartBloc;j++) {
            Random rand = new Random();
            int ind = rand.nextInt(tabIndice.length);
            for(int i=0; i < tabVerif.length; i++)
                if(tabVerif[i] == 0) {
                    tabVerif[i] = tabIndice[ind];
                    break;
                }
        }
        Handler handler = new Handler();
        int temps=1;
        for(int i : tabVerif){
            Toast.makeText(Niveau1.this, "test "+i + "temps "+temps, Toast.LENGTH_SHORT).show();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(i==1){
                        couleur1.setText("ICI");
                    }
                    if(i==2){
                        couleur2.setText("ICI");
                    }
                    if(i==3){
                        couleur3.setText("ICI");
                    }
                    if(i==4){
                        couleur4.setText("ICI");
                    }
                }
            }, 1000*temps);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    couleur1.setText("");
                    couleur2.setText("");
                    couleur3.setText("");
                    couleur4.setText("");
                }
            }, 1000*temps+500);
            temps++;
            if(i==0){
                break;
            }
        }
    }


}