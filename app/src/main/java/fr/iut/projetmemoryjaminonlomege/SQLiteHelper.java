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

    // Fonction permettant la création de notre table joueur et de ses champs.
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


    /* La fonction verifMail( prend en paramètre un mail, elle permet de vérifier si le mail est déjà présent dans la base de données.
       Elle retourne un booléen.
     */
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

    /* La fonction addJoueur() prend en paramètres les différents champs qui seront entrés par l'utilisateur lors de son inscription
       Ele va ensuite associer les paramètres aux champs de la table pour insérer le nouveau joueur dans la base de données.
       La fonction retourne un booléen.

     */
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

    /*
        La fonction connexion() prend en paramètres l'email et le mot de passe rentrés par l'utilisateur pour essayer de les faire correspondre
        avec les identifiants déjà présents dans la base de données.
        Elle retourne un booléen.

     */
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

    /*
        La fonction affScore() prend en paramètre un mail, elle va retourner le meilleur score réalisé par le joueur.
     */
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
    /*
        La fonction affNom() prend en paramètre un mail, elle va retourner le nom du joueur.
     */
    String affNom(String mail){
        String nom;
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT nom FROM Joueur WHERE mail='"+mail+"'";
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToLast();
        nom = cursor.getString(0);
        return nom;
    }

    /*
        La fonction affPrenom() prend en paramètre un mail, elle va retourner le prenom du joueur.
     */

    String affPrenom(String mail){
        String prenom;
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT prenom FROM Joueur WHERE mail='"+mail+"'";
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToLast();
        prenom = cursor.getString(0);
        return prenom;
    }

    /*
        La fonction affPseudo() prend en paramètre un mail, elle va retourner le pseudo du joueur.
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

    /*
        La fonction affDateNais() prend en paramètre un mail, elle va retourner la date de naissance du joueur.
     */

    String affDateNais(String mail){
        String dateNais;
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT dateNais FROM Joueur WHERE mail='"+mail+"'";
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToLast();
        dateNais = cursor.getString(0);
        return dateNais;
    }

    /*
    La fonction affGenre() prend en paramètre un mail, elle va retourner le genre du joueur.
    */
    String affGenre (String mail){
        String genre;
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT genre FROM Joueur WHERE mail='"+mail+"'";
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToLast();
        genre = cursor.getString(0);
        return genre;
    }

    /*
        La fonction affClassement() consiste à récupérer les 10 meilleurs joueur dans la base de données en fonction de leur score.
        elle retourne un tableau à deux dimensions.
     */
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

    /*
    La fonction UpdateScore() prend en paramètre un mail et un score (celui effectué lors du niveau), elle va mettre à jour le score dans la base de données .
    */
    void UpdateScore(String mail, double scoreDuNiveau){
        double ancienScore= affScore(mail);
        double nouveauScore=ancienScore+scoreDuNiveau;
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SCORE,nouveauScore);
        db.update(TABLE_NAME, values, MAIL + " = ?", new String[]{mail});
    }

}
