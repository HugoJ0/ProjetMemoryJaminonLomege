package fr.iut.projetmemoryjaminonlomege;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Connexion extends AppCompatActivity {

    Button retour,connexion;
    EditText mail,password;
    SQLiteHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        mail = findViewById(R.id.id_input_email_connexion);
        password = findViewById(R.id.id_input_password);
        connexion = findViewById(R.id.id_bouton_connexion);
        db = new SQLiteHelper(getApplicationContext());
        /*
            Au clic sur le bouton connexion, une vérification dans la base de données sera effectuée pour faire correspondre l'email et le mot de passe rentrés par l'utilisateur avec ceux présents dans la BDD.
            S'il n'y a pas d'erreur, le joueur est redirigé vers le menu, sinon un message d'erreur personnalisé sera affiché.
         */
        connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (db.connection(mail.getText().toString(),password.getText().toString())){
                    Intent intent = new Intent(Connexion.this,Menu.class);
                    /*
                        On créé un bundle contenant le contenu de la variable mail qui sera utilisée dans toutes les classes java dans lesquelles le mail est nécessaire.
                    */
                    Bundle bundle=new Bundle();
                    bundle.putString("mail", mail.getText().toString());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(Connexion.this, "Email ou mot de passe incorrect !", Toast.LENGTH_SHORT).show();
                }
            }
        });

        /*
            Au clic sur le bouton retour, on renvoie l'utilisateur sur l'accueil du jeu.
         */

        retour=findViewById(R.id.connexion_id_bouton_retour);
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Connexion.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}