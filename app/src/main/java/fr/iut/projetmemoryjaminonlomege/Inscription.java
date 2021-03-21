package fr.iut.projetmemoryjaminonlomege;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class Inscription extends AppCompatActivity {

    Button retour;
    Button inscription;
    SQLiteHelper db;
    EditText pseudo;
    EditText FirstName;
    EditText LastName;
    EditText mail;
    EditText password;
    RadioButton genre;
    EditText dateNais;



    public static String pse;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";

    private static String ps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        pseudo = findViewById(R.id.id_input_pseudo);
        mail = findViewById(R.id.id_input_mail_inscription);
        password = findViewById(R.id.id_input_mdp_inscription);
        FirstName = findViewById(R.id.id_input_nom);
        LastName = findViewById(R.id.id_input_prenom);
        genre = findViewById(R.id.id_radiobouton_genre);
        dateNais = findViewById(R.id.id_input_datenaissance);
        inscription = findViewById(R.id.id_bouton_inscription);

        db = new SQLiteHelper(getApplicationContext());

        inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (db.addJoueur(pseudo.getText().toString(),mail.getText().toString(),password.getText().toString(),FirstName.getText().toString(),LastName.getText().toString(),genre.getText().toString(),dateNais.getText().toString(),0)){
                    saveData();
                    Intent intent = new Intent (Inscription.this,Menu.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(Inscription.this,"Erreur lors de l'inscription !", Toast.LENGTH_SHORT).show();
                }
            }
        });



        retour=findViewById(R.id.inscription_id_bouton_retour);
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Inscription.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    protected void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(TEXT,pseudo.getText().toString());
        editor.apply();
    }

    protected void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        ps = sharedPreferences.getString(TEXT,"");
        this.pse = ps;
    }

    public static String getPse() {
        return pse;
    }

}