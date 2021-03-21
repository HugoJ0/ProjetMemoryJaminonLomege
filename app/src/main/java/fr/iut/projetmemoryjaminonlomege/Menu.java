package fr.iut.projetmemoryjaminonlomege;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Menu extends AppCompatActivity {

    TextView nom,score;
    Button classement, nouvellePartie, profil, deconnexion, continuer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        nom=findViewById(R.id.id_textview_username);
        score=findViewById(R.id.id_textview_score);
        classement=findViewById(R.id.id_bouton_classement);
        nouvellePartie=findViewById(R.id.id_bouton_nouvellePartie);
        profil=findViewById(R.id.id_bouton_profil);
        deconnexion=findViewById(R.id.id_bouton_deconnection);
        continuer=findViewById(R.id.id_bouton_continuer);

        classement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, Classement.class);
                startActivity(intent);
            }
        });
        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, Profil.class);
                startActivity(intent);
            }
        });
        nouvellePartie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, ChoixMode.class);
                startActivity(intent);
            }
        });

        deconnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent  (Menu.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}