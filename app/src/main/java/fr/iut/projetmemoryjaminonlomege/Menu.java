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
    SQLiteHelper db;

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

        db = new SQLiteHelper(getApplicationContext());

        //score.setText(db.affScore(Inscription.getPs()));


        classement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, Classement.class);
                Bundle bundle=new Bundle();
                Intent intent2=getIntent();
                Bundle bundle2=intent2.getExtras();
                bundle.putString("mail", bundle2.getString("mail"));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, Profil.class);
                Bundle bundle=new Bundle();
                Intent intent2=getIntent();
                Bundle bundle2=intent2.getExtras();
                bundle.putString("mail", bundle2.getString("mail"));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        nouvellePartie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, ChoixMode.class);
                Bundle bundle=new Bundle();
                Intent intent2=getIntent();
                Bundle bundle2=intent2.getExtras();
                bundle.putString("mail", bundle2.getString("mail"));
                intent.putExtras(bundle);
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