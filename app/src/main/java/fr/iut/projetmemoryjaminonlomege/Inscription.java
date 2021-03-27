package fr.iut.projetmemoryjaminonlomege;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.Calendar;

public class Inscription extends AppCompatActivity {

    Button retour,inscription;
    SQLiteHelper db;
    EditText pseudo,FirstName,LastName,mail,password,dateNais;
    RadioButton genre;




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
        dateNais = (EditText)findViewById(R.id.id_input_datenaissance);
        dateNais.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Calendar mcurrentDate=Calendar.getInstance();
                int annee = mcurrentDate.get(Calendar.YEAR);
                int mois = mcurrentDate.get(Calendar.MONTH);
                int jour = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker=new DatePickerDialog(Inscription.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectAnnee, int selectMois, int selectJour) {
                        Log.e("Date Selected", " Jour: " + selectJour  + "Mois: " + selectMois+ " Année: " + selectAnnee);
                        dateNais.setText(selectJour + "/" + selectMois + "/" + selectAnnee);
                    }
                },annee, mois, jour);
                mDatePicker.setTitle("Sélectionner une date");
                mDatePicker.show();
            }
        });


        inscription = findViewById(R.id.id_bouton_inscription);

        db = new SQLiteHelper(getApplicationContext());

        inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (db.verifMail(mail.getText().toString())) {
                    if (db.addJoueur(pseudo.getText().toString(), mail.getText().toString(), password.getText().toString(), FirstName.getText().toString(), LastName.getText().toString(), genre.getText().toString(), dateNais.getText().toString(), 0)) {
                        Intent intent = new Intent(Inscription.this, Menu.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("mail", mail.getText().toString());
                        intent.putExtras(bundle);
                        startActivity(intent);
                    } else {
                        Toast.makeText(Inscription.this, "Erreur lors de l'inscription !", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(Inscription.this,"Adresse mail déjà utilisée.",Toast.LENGTH_SHORT).show();
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


}