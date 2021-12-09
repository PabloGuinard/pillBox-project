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
public class Referent extends Personne {
    
    protected String mail;
    protected String tel;
    /**
     * Constructeur de referent
     * @param n nom
     * @param p prenom
     * @param ag fonction (referent ou patient)
     * @param ad adresse
     * @param m mail
     * @param t tel
     */
    public Referent(String n,String p,String ag,String ad,String m, String t){
        super(n,p,ag,ad);
        mail=m;
        tel=t;
    }
    
    /**
     * retourne le mail
     * @return mail
     */
    public String getMail() {
        return mail;
    }
    /**
     * retourne le telephone
     * @return tel
     */
    public String getTel() {
        return tel;
    }
    /**
     * Set le mail
     * @param mail email
     */
    public void setMail(String mail) {
        this.mail = mail;
    }
    /** 
     * set le tel
     * @param tel tel
     */
    public void setTel(String tel) {
        this.tel = tel;
    }
    /**
     * retourne un String avec les info d'un referent 
     * @return 
     */
    public String getInfo(){
        
        String newLine=System.getProperty("line.separator");
        String res="";
        res+="Nom : "+nom+newLine+"Prenom : "+prenom+newLine+"Mail : "+mail+newLine+"Tel : "+tel+newLine;
        
        return res;
    }
    
    @Override
    /**
     * surcharge de la fonction ToString
     */
    public String toString(){
        return this.getPrenom()+" "+this.getNom()+" "+this.getFonction()+" "+this.getAdresse()+" "+mail+" "+tel+" ";
    }
}
