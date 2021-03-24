package fr.iut.projetmemoryjaminonlomege;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
                            SCORE + " INTEGER)";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String sql= "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);
    }

    boolean addJoueur(String pseudo,String mail, String password, String prenom, String nom, String genre, String dateNais, int score){
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

    int affScore(String mail){
        int score;
        SQLiteDatabase db=this.getReadableDatabase();
        String sql="SELECT score FROM Joueur WHERE mail='"+mail+"'";
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToLast();
        if(!cursor.isAfterLast()){
            score = cursor.getInt(0);
            return score;
        }
        else
            return 0;


    }
}
