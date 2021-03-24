package fr.iut.projetmemoryjaminonlomege;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Classement extends AppCompatActivity {

    Button retour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classement);

        retour=findViewById(R.id.id_bouton_retour);
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Classement.this, Menu.class);
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