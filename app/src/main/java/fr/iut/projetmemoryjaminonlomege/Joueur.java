package fr.iut.projetmemoryjaminonlomege;

//Classe Joueur, possédant un pseudo, un score et un rang, utilisé pour le classement.
public class Joueur {

    private String pseudo;
    private String score;
    private String rang;

    public Joueur(String pseudo, String score, String rang){
        this.pseudo=pseudo;
        this.score=score;
        this.rang=rang;
    }

    public Joueur(){
        this("Pseudo","Score","Rang");
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getRang() {
        return rang;
    }

    public void setRang(String rang) {
        this.rang = rang;
    }

    @Override
    public String toString() {
        return "Joueur{" +
                "pseudo='" + pseudo + '\'' +
                ", score='" + score + '\'' +
                ", rang='" + rang + '\'' +
                '}';
    }
}
