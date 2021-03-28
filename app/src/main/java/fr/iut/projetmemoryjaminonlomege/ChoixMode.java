package fr.iut.projetmemoryjaminonlomege;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ChoixMode extends AppCompatActivity {

    Button facile,difficile,expert,chrono,retour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_mode);

        facile=findViewById(R.id.id_bouton_facile);
        difficile=findViewById(R.id.id_bouton_difficile);
        expert=findViewById(R.id.id_bouton_expert);
        chrono=findViewById(R.id.id_bouton_chrono);
        retour=findViewById(R.id.id_bouton_retour);

        //Gestion du bouton facile, renvoie vers le niveau 1 en mettant les bonnes variables.
        facile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChoixMode.this,Niveau1.class);

                Bundle bundle=new Bundle();
                bundle.putInt("nbDepartBloc",1);
                bundle.putInt("nbFinBloc",10);
                bundle.putInt("vies",2);
                bundle.putDouble("poids",1);
                Intent intent2=getIntent();
                Bundle bundle2=intent2.getExtras();
                bundle.putString("mail", bundle2.getString("mail"));
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });

        //Gestion du bouton difficile, renvoie vers le niveau 1 en mettant les bonnes variables.
        difficile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChoixMode.this,Niveau1.class);

                Bundle bundle=new Bundle();
                bundle.putInt("nbDepartBloc",3);
                bundle.putInt("nbFinBloc",15);
                bundle.putInt("vies",2);
                bundle.putDouble("poids",1.5);
                Intent intent2=getIntent();
                Bundle bundle2=intent2.getExtras();
                bundle.putString("mail", bundle2.getString("mail"));
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });

        //Gestion du bouton expert, renvoie vers le niveau 1 en mettant les bonnes variables.
        expert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChoixMode.this,Niveau1.class);

                Bundle bundle=new Bundle();
                bundle.putInt("nbDepartBloc",5);
                bundle.putInt("nbFinBloc",20);
                bundle.putInt("vies",3);
                bundle.putDouble("poids",3);
                Intent intent2=getIntent();
                Bundle bundle2=intent2.getExtras();
                bundle.putString("mail", bundle2.getString("mail"));
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });

        //Gestion du bouton chrono, ce mode n'est pas configuré.
        chrono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ChoixMode.this, "Indisponible pour le moment", Toast.LENGTH_SHORT).show();
            }
        });

        //Gestion du bouton retour, renvoi vers le Menu.
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChoixMode.this,Menu.class);
                Bundle bundle=new Bundle();
                Intent intent2=getIntent();
                Bundle bundle2=intent2.getExtras();
                bundle.putString("mail", bundle2.getString("mail"));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }
}