/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.modele;

/**
 *
 * @author p2007867
 */
public class Personne {
    protected String nom;
    protected String prenom;
    protected String fonction;
    protected String adresse;
    
    /**
     * constructeur de Personne
     * @param n nom
     * @param p prenom
     * @param ag fonction (referent ou patient)
     * @param ad adresse
     */
    public Personne(String n,String p,String ag,String ad){
        nom=n;
        prenom=p;
        fonction=ag;
        adresse=ad;
    }
    /**
     * retourne l'adresse
     * @return adresse
     */
    public String getAdresse() {
        return adresse;
    }
    /**
     * retourne la fonction
     * @return fonction
     */
    public String getFonction() {
        return fonction;
    }
    /**
     * retourne le nom
     * @return nom
     */
    public String getNom() {
        return nom;
    }
    /**
     * retourne le prenom
     * @return prenom
     */
    public String getPrenom() {
        return prenom;
    }
    /**
     * set la fonction
     * @param fonction fonction 
     */
    public void setAge(String fonction) {
        this.fonction = fonction;
    }
    /**
     * set l'adresse
     * @param adresse adresse
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    /**
     * set le nom
     * @param nom nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
    /**
     * set le prenom
     * @param prenom prenom
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Override
    /**
     * surcharge de la fonction ToString
     */
    public String toString() {
        
        return nom+" "+prenom+" "+fonction+" "+adresse;
    }
    
}
