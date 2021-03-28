package fr.iut.projetmemoryjaminonlomege;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Profil extends AppCompatActivity {

    Button retour;
    SQLiteHelper db;
    String mail;
    TextView pseudo;
    TextView nom;
    TextView prenom;
    TextView genre;
    TextView email;
    TextView dateNais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        Intent intent2=getIntent();
        Bundle bundle2=intent2.getExtras();
        mail = bundle2.getString("mail");
        db = new SQLiteHelper(getApplicationContext());
        /*
            On récupère l'id du TextView du pseudo, pour y afficher le résultat de la fonction affPseudo() (voir class SQLiteHelper).
         */
        pseudo = (TextView)findViewById(R.id.id_textview_pseudo);
        pseudo.setText(db.affPseudo(mail));
        /*
            On récupère l'id du TextView du nom, pour y afficher le résultat de la fonction affNom() (voir class SQLiteHelper).
         */
        nom = (TextView)findViewById(R.id.id_textview_nom);
        nom.setText(db.affNom(mail));
        /*
            On récupère l'id du TextView du prénom, pour y afficher le résultat de la fonction affPrenom() (voir class SQLiteHelper).
         */
        prenom = (TextView)findViewById(R.id.id_textview_prenom);
        prenom.setText(db.affPrenom(mail));
        /*
            On récupère l'id du TextView du mail, pour y afficher le mail du joueur (voir Bundle).
         */
        email =(TextView)findViewById(R.id.id_textview_email);
        email.setText(mail);
        /*
            On récupère l'id du TextView du genre, pour y afficher le résultat de la fonction affGenre() (voir class SQLiteHelper).
         */
        genre = (TextView)findViewById(R.id.id_textview_sexe);
        genre.setText(db.affGenre(mail));
        /*
            On récupère l'id du TextView de la date de naissance, pour y afficher le résultat de la fonction affDatenais() (voir class SQLiteHelper).
         */
        dateNais = (TextView)findViewById(R.id.id_textview_datenaissance);
        dateNais.setText(db.affDateNais(mail));


        retour=findViewById(R.id.id_bouton_retour);

        /*
            Au clic sur le bouton retour, on renvoie l'utilisateur sur l'accueil du jeu.
         */
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profil.this, Menu.class);
                Bundle bundle=new Bundle();
                bundle.putString("mail", bundle2.getString("mail"));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}