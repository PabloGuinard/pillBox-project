/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.modele;

import java.util.Date;

/**
 *
 * @author p2008965
 */
public class ActionHistorique {
    protected String texte;
    protected Date date;
/**
 * constructeur de la classe ActionHistorique 
 * @param texte action
 * @param date date
 */
    public ActionHistorique(String texte, Date date) {
        this.texte = texte;
        this.date = date;
    }
/**
 * retourne la date de l'action
 * @return date
 */
    public Date getDate() {
        return date;
    }
/**
 * retourne l'action
 * @return action
 * 
 */
    public String getTexte() {
        return texte;
    }
/**
 * set la date
 * @param date date actuelle
 */
    public void setDate(Date date) {
        this.date = date;
    }
/** 
 * set l'action
 * @param texte Action a mettre dans l'historique
 */
    public void setTexte(String texte) {
        this.texte = texte;
    }
    /**
     * retourne une ActionHistorique sous forme de String
     * @return ActionHistorique dous forme de string
     */
    public String ToString(){
        String res="";
        if(date.getDate()<=9){
            res+="0"+date.getDate();
        }else{
            res+=date.getDate();
        }
        res+="/";
        if((date.getMonth()+1)<=9){
            res+="0"+(date.getMonth()+1);
        }else{
            res+=(date.getMonth()+1);
        }
        res+=" à ";
        if((date.getHours()+1)<=9){
            res+="0"+(date.getHours()+1);
        }else{
            res+=(date.getHours()+1);
        }
        res+="h";
        if(date.getMinutes()<=9){
            res+="0"+date.getMinutes();
        }else{
            res+=date.getMinutes();
        }
        res+=" : "+texte;
        return res;
    }
}
