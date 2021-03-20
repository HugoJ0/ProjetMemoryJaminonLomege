package fr.iut.projetmemoryjaminonlomege;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Connection extends AppCompatActivity {

    Button retour;
    Button connexion;
    EditText mail;
    EditText password;
    SQLiteHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);

        mail = findViewById(R.id.id_input_email_connexion);
        password = findViewById(R.id.id_input_password);
        connexion = findViewById(R.id.id_bouton_connexion);
        db = new SQLiteHelper(getApplicationContext());

        connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (db.connection(mail.getText().toString(),password.getText().toString())){
                    Intent intent = new Intent(Connection.this,MainActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(Connection.this, "Email ou mot de passe incorrect !", Toast.LENGTH_SHORT).show();
                }
            }
        });

        retour=findViewById(R.id.connection_id_bouton_retour);
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Connection.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}