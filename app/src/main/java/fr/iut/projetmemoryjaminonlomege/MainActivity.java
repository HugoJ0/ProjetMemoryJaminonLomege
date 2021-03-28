package fr.iut.projetmemoryjaminonlomege;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    Button connexion,inscription, regles;
    Switch sw;
    MediaPlayer music;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connexion=findViewById(R.id.main_id_bouton_connexion);
        inscription=findViewById(R.id.main_id_bouton_inscription);
        regles=findViewById(R.id.main_id_bouton_regles);
        sw = findViewById(R.id.id_music_switch);

        music=MediaPlayer.create(this, R.raw.haydem);
        son(sw);


        regles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                music.stop();
                music.release();
                Intent intent = new Intent(MainActivity.this, Regles.class);
                startActivity(intent);
            }
        });
        connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                music.stop();
                music.release();
                Intent intent = new Intent(MainActivity.this, Connexion.class);
                startActivity(intent);
            }
        });
        inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                music.stop();
                music.release();
                Intent intent = new Intent(MainActivity.this, Inscription.class);
                startActivity(intent);
            }
        });

    }

    public void son(Switch swi) {
        music.start();
        music.setLooping(true);
        swi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked == false) {
                    music.pause();
                } else if (isChecked == true) {
                    music.start();
                }

            }
        });
    }

}
