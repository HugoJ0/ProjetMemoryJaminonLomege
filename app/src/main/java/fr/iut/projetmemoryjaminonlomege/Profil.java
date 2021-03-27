package fr.iut.projetmemoryjaminonlomege;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Profil extends AppCompatActivity {

    Button retour;
    SQLiteHelper db;
    String mail;
    TextView pseudo;
    TextView nom;
    TextView prenom;
    TextView genre;
    TextView email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        Intent intent2=getIntent();
        Bundle bundle2=intent2.getExtras();
        mail = bundle2.getString("mail");
        db = new SQLiteHelper(getApplicationContext());

        nom = (TextView)findViewById(R.id.id_textview_nom);
        nom.setText(db.affNom(mail));

        prenom = (TextView)findViewById(R.id.id_textview_prenom);
        prenom.setText(db.affPrenom(mail));

        pseudo = (TextView)findViewById(R.id.id_textview_pseudo);
        pseudo.setText(db.affPseudo(mail));

        email =(TextView)findViewById(R.id.id_textview_email);
        email.setText(mail);

        retour=findViewById(R.id.id_bouton_retour);

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