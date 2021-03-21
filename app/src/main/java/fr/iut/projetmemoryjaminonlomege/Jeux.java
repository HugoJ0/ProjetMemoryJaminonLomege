package fr.iut.projetmemoryjaminonlomege;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Random;

public class Jeux extends AppCompatActivity {
    int departNbBloc, finNbBloc, vies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(Jeux.this, ChoixMode.class);
        startActivityForResult(intent,2000);
        setContentView(R.layout.activity_niveau1);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==2000){
            if(resultCode == 1){
                departNbBloc=1;
                finNbBloc=10;
                vies=2;
            }
            if(resultCode == 2){
                departNbBloc=3;
                finNbBloc=15;
                vies=2;
            }
            if(resultCode == 3){
                departNbBloc=5;
                finNbBloc=20;
                vies=3;
            }
            if(resultCode == 4){
                departNbBloc=1;
                vies=3;
            }
            if(resultCode == 5){
                Intent intent = new Intent(Jeux.this, Menu.class);
                startActivity(intent);
            }
        }
    }

}









    /*int departNbBloc,finNbBloc,vies,indice;
    boolean partieEnCours,erreur,gagner;
    Button[] tabBoutons = new Button[10];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(Jeux.this, ChoixMode.class);
        startActivityForResult(intent,2000);
        int nbBlocs=4;
        while(nbBlocs<=10){
            changeView(nbBlocs);
            findButtons();
            desactiverBoutons(nbBlocs);
            nbBlocs=niveau(nbBlocs);
            nbBlocs++;
        }




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==2000){
            if(resultCode == 1){
                departNbBloc=1;
                finNbBloc=10;
                vies=2;
            }
            if(resultCode == 2){
                departNbBloc=3;
                finNbBloc=15;
                vies=2;
            }
            if(resultCode == 3){
                departNbBloc=5;
                finNbBloc=20;
                vies=3;
            }
            if(resultCode == 4){
                departNbBloc=1;
                vies=3;
            }
            if(resultCode == 5){
                Intent intent = new Intent(Jeux.this, Menu.class);
                startActivity(intent);
            }
        }
    }

    public void changeView(int nbBlocs){
        switch(nbBlocs){
            case 4:
                setContentView(R.layout.activity_niveau1);
                break;
            case 5:
                setContentView(R.layout.activity_niveau2);
                break;
            case 6:
                setContentView(R.layout.activity_niveau3);
                break;
            case 7:
                setContentView(R.layout.activity_niveau4);
                break;
            case 8:
                setContentView(R.layout.activity_niveau5);
                break;
            case 9:
                setContentView(R.layout.activity_niveau6);
                break;
            case 10:
                setContentView(R.layout.activity_niveau7);
                break;
        }

    }
    public void findButtons(){
        tabBoutons[0]=findViewById(R.id.id_bouton_couleur_1);
        tabBoutons[1]=findViewById(R.id.id_bouton_couleur_2);
        tabBoutons[2]=findViewById(R.id.id_bouton_couleur_3);
        tabBoutons[3]=findViewById(R.id.id_bouton_couleur_4);
        tabBoutons[4]=findViewById(R.id.id_bouton_couleur_5);
        tabBoutons[5]=findViewById(R.id.id_bouton_couleur_6);
        tabBoutons[6]=findViewById(R.id.id_bouton_couleur_7);
        tabBoutons[7]=findViewById(R.id.id_bouton_couleur_8);
        tabBoutons[8]=findViewById(R.id.id_bouton_couleur_9);
        tabBoutons[9]=findViewById(R.id.id_bouton_couleur_10);
    }

    public int niveau(int nbBlocs){
        int [] possible=new int[nbBlocs];
        int nbViesRestantes = vies;
        for(int i=1;i<=nbBlocs;i++){
            Arrays.fill(possible,i);
        }
        int [] allumes = new int[finNbBloc-departNbBloc+1];
        int i=departNbBloc;
        while(i<=finNbBloc && nbViesRestantes>0){
            allumes=etapeOrdi(allumes,possible);
            if(!etapeJoueur(allumes,nbBlocs)){
                nbViesRestantes--;
            }
            i++;
        }
        if(nbViesRestantes==0){
            return nbBlocs--;
        }else{
            return nbBlocs++;
        }

    }

    public int[] etapeOrdi(int[] allumes, int[] possible){
        Random rand = new Random();
        int ind = rand.nextInt(possible.length);
        Arrays.fill(allumes,possible[ind]);
        return allumes;
    }
    public boolean etapeJoueur(int[] allumes,int nbBlocs){
        int[] boutonsCliques = new int[finNbBloc-departNbBloc+1];
        erreur=false;
        gagner=false;
        indice=0;
        activerBoutons(nbBlocs);
        while(!erreur||gagner) {
            tabBoutons[0].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boutonsCliques[indice]=1;
                    gagner=verifieSiGagner(allumes);
                    if(!gagner) {
                        erreur = verifieSiErreur(allumes, boutonsCliques);
                        Toast.makeText(Jeux.this, "Email ou mot de passe incorrect !", Toast.LENGTH_SHORT).show();
                    }
                    indice++;
                }
            });
            tabBoutons[1].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boutonsCliques[indice]=2;
                    gagner=verifieSiGagner(allumes);
                    if(!gagner) {
                        erreur = verifieSiErreur(allumes, boutonsCliques);
                    }
                    indice++;
                }
            });
            tabBoutons[2].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boutonsCliques[indice]=3;
                    gagner=verifieSiGagner(allumes);
                    if(!gagner) {
                        erreur = verifieSiErreur(allumes, boutonsCliques);
                    }
                    indice++;
                }
            });
            tabBoutons[3].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boutonsCliques[indice]=4;
                    gagner=verifieSiGagner(allumes);
                    if(!gagner) {
                        erreur = verifieSiErreur(allumes, boutonsCliques);
                    }
                    indice++;
                }
            });
            tabBoutons[4].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boutonsCliques[indice]=5;
                    gagner=verifieSiGagner(allumes);
                    if(!gagner) {
                        erreur = verifieSiErreur(allumes, boutonsCliques);
                    }
                    indice++;
                }
            });
            tabBoutons[5].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boutonsCliques[indice]=6;
                    gagner=verifieSiGagner(allumes);
                    if(!gagner) {
                        erreur = verifieSiErreur(allumes, boutonsCliques);
                    }
                    indice++;
                }
            });
            tabBoutons[6].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boutonsCliques[indice]=7;
                    gagner=verifieSiGagner(allumes);
                    if(!gagner) {
                        erreur = verifieSiErreur(allumes, boutonsCliques);
                    }
                    indice++;
                }
            });
            tabBoutons[7].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boutonsCliques[indice]=8;
                    gagner=verifieSiGagner(allumes);
                    if(!gagner) {
                        erreur = verifieSiErreur(allumes, boutonsCliques);
                    }
                    indice++;
                }
            });
            tabBoutons[8].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boutonsCliques[indice]=9;
                    gagner=verifieSiGagner(allumes);
                    if(!gagner) {
                        erreur = verifieSiErreur(allumes, boutonsCliques);
                    }
                    indice++;
                }
            });
            tabBoutons[9].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boutonsCliques[indice]=10;
                    gagner=verifieSiGagner(allumes);
                    if(!gagner) {
                        erreur = verifieSiErreur(allumes, boutonsCliques);
                    }
                    indice++;
                }
            });
        }
        if(gagner){
            desactiverBoutons(nbBlocs);
            return true;
        }else{
            desactiverBoutons(nbBlocs);
            return false;
        }
    }
    public boolean verifieSiErreur(int[] allumes, int[] boutonCliques){
        if(allumes[indice]==boutonCliques[indice]){
            return false;
        }else{
            return true;
        }
    }
    public boolean verifieSiGagner(int[] allumes){
        if(indice<=finNbBloc && allumes[indice]!=0){
            return false;
        }else{
            return true;
        }
    }
    public void desactiverBoutons(int nbBlocs){
        for(int i=0;i<nbBlocs;i++) {
            tabBoutons[i].setClickable(false);
        }
    }
    public void activerBoutons(int nbBlocs){
        for(int i=0;i<nbBlocs;i++) {
            tabBoutons[i].setClickable(true);
        }
    }
}*/