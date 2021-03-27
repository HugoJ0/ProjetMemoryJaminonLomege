package fr.iut.projetmemoryjaminonlomege;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.sql.Date;

public class SQLiteHelper extends SQLiteOpenHelper {

    public static String BDD_NAME= "ColorMemoryBDD", TABLE_NAME ="Joueur",ID="id";
    public static String PSEUDO = "pseudo",MAIL = "mail", PASSWORD="password",FIRSTNAME = "prenom", LASTNAME = "nom", GENRE = "genre", DATE_NAISSANCE = "dateNais", SCORE = "score";


    public SQLiteHelper(@Nullable Context context) {
        super(context, BDD_NAME,null,1 );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql =
                    "CREATE TABLE " + TABLE_NAME + " (" +
                            ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                            PSEUDO + " TEXT," +
                            MAIL + " TEXT," +
                            PASSWORD + " TEXT," +
                            FIRSTNAME + " TEXT," +
                            LASTNAME + " TEXT," +
                            GENRE + " TEXT," +
                            DATE_NAISSANCE + " DATE," +
                            SCORE + " DOUBLE)";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String sql= "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);
    }


    boolean verifMail(String mail){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql="SELECT * FROM Joueur WHERE mail='"+mail+"'";
        Cursor cursor = db.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            return false;
        }
        else
            return true;
    }

    boolean addJoueur(String pseudo,String mail, String password, String prenom, String nom, String genre, String dateNais, double score){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues data = new ContentValues();
        data.put(PSEUDO,pseudo);
        data.put(MAIL,mail);
        data.put(PASSWORD,password);
        data.put(FIRSTNAME,prenom);
        data.put(LASTNAME,nom);
        data.put(GENRE,genre);
        data.put(DATE_NAISSANCE, dateNais);
        data.put(SCORE,score);

        long result = db.insert(TABLE_NAME,null,data);
        if (result != -1){
            return true;
        }
        else
            return false;
    }

    boolean connection(String mail, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql="SELECT * FROM Joueur WHERE mail='"+mail+"' AND password='"+password+"'";
        Cursor cursor = db.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            return true;
        }
        else
            return false;
    }

    double affScore(String mail){
        double score;
        SQLiteDatabase db=this.getReadableDatabase();
        String sql="SELECT score FROM Joueur WHERE mail='"+mail+"'";
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToLast();
        if(!cursor.isAfterLast()){
            score = cursor.getDouble(0);
            return score;
        }
        else
            return 0;
    }

    String affNom(String mail){
        String nom;
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT nom FROM Joueur WHERE mail='"+mail+"'";
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToLast();
        nom = cursor.getString(0);
        return nom;
    }

    String affPrenom(String mail){
        String prenom;
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT prenom FROM Joueur WHERE mail='"+mail+"'";
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToLast();
        prenom = cursor.getString(0);
        return prenom;
    }

    /* Fonction "inutile" puisqu'on utilise un bundle pour
    String affMail(String mail){
        String email;
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT mail FROM Joueur WHERE mail='"+mail+"'";
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToLast();
        email = cursor.getString(0);
        return email;
    }

     */

    String affPseudo(String mail){
        String pseudo;
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT pseudo FROM Joueur WHERE mail='"+mail+"'";
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToLast();
        pseudo = cursor.getString(0);
        return pseudo;
    }

    String affDateNais(String mail){
        String dateNais;
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT dateNais FROM Joueur WHERE mail='"+mail+"'";
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToLast();
        dateNais = cursor.getString(0);
        return dateNais;
    }
    String affGenre (String mail){
        String genre;
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT genre FROM Joueur WHERE mail='"+mail+"'";
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToLast();
        genre = cursor.getString(0);
        return genre;
    }


    String[][] affClassement(){
        String[][] tab = new String[10][2];
        String pseudo;
        int indice = 0;
        double score;
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT pseudo,score FROM joueur ORDER BY score DESC LIMIT 10 ";
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            pseudo = cursor.getString(cursor.getColumnIndex("pseudo"));
            tab [indice][0] = pseudo;
            score = cursor.getDouble(cursor.getColumnIndex("score"));
            tab [indice][1] = "" + score;
            cursor.moveToNext();
        }
        return tab;
    }

    void UpdateScore(String mail, double scoreDuNiveau){
        double ancienScore= affScore(mail);
        double nouveauScore=ancienScore+scoreDuNiveau;
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SCORE,nouveauScore);
        db.update(TABLE_NAME, values, MAIL + " = ?", new String[]{mail});
    }

}
