/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.modele;

import java.util.Date;

/**
 *
 * @author p2018172
 */
public class Case {
    protected int index;
    protected Date date;
    protected boolean etatRemplissage;
    protected boolean retardAccepte;
    
    /**
     * constructeur
     * 
     * @param index
     * @param d 
     */
    public Case(int index, Date d){
        this.date=d;
        this.index = index;
        this.etatRemplissage = false;
        this.retardAccepte = true;
    }

    /**
     * accesseur de Date
     * @return date
     */
    public Date getDate() {
        return date;
    }
    
    /**
     * accesseur d'index
     * @return index
     */
    public int getIndex(){
        return this.index;
    }
    
    /**
     * accesseur d'etatRemplissage
     * @return etatRemplissage
     */
    public boolean getEtatRemplissage(){
        return this.etatRemplissage;
    }
    
    /**
     * accesseur de retardAccepte
     * @return retardAccepte
     */
    public boolean getRetardAccepte(){
        return this.retardAccepte;
    }
    
    /**
     * mutateur d'index
     * @param index 
     */
    public void setIndex(int index){
        this.index = index;
    }
    
    /**
     * mutateur d'etatRemplissage
     * @param etatRemplissage 
     */
    public void setEtatRemplissage(boolean etatRemplissage){
        this.etatRemplissage = etatRemplissage;
    }
    
    /**
     * mutateur de retardAccepte
     * @param retardAccepte 
     */
    public void setRetardAccepte(boolean retardAccepte){
        this.retardAccepte = retardAccepte;
    }
    
    /**
     * mutateur de Date
     * @param date 
     */
    public void setDate(Date date) {
        this.date = date;
    }
}
