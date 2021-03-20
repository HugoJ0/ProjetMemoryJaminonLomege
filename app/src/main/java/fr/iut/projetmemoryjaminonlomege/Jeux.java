package fr.iut.projetmemoryjaminonlomege;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Random;

public class Jeux extends AppCompatActivity {
    int departNbBloc,finNbBloc,vies;
    boolean partieEnCours;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(Jeux.this, ChoixMode.class);
        startActivityForResult(intent,2000);
        partieEnCours=true;
        int nbBlocs=4;
        while(partieEnCours||nbBlocs>10){
            changeView(nbBlocs);
            niveau(nbBlocs);
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
    public void niveau(int nbBlocs){
        int [] possible=new int[nbBlocs];
        for(int i=1;i<=nbBlocs;i++){
            Arrays.fill(possible,i);
        }
        int [] allumes = new int[finNbBloc-departNbBloc+1];
    }

    public int[] etape(int[] allumes){

        return allumes;
    }
}