/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.modele;

import java.util.ArrayList;

/**
 *
 * @author p2008965
 */
public class Historique {

    protected ArrayList<ActionHistorique> listeHistorique;
    /**
     * constructeur par defaut
     */
    public Historique() {
        listeHistorique = new ArrayList<>();
    }
    /**
     * retourne la liste d'Action historique
     * @return liste d'action historique
     */
    public ArrayList<ActionHistorique> getListeHistorique() {
        return listeHistorique;
    }
    
    /**
     * set la liste d'action historique
     * @param listeHistorique list de l'historique
     */
    public void setListeHistorique(ArrayList<ActionHistorique> listeHistorique) {
        this.listeHistorique = listeHistorique;
    }

    /**
     * ajoute une action à l’historique, return false si problème, true sinon
     *
     * @param action actionHistorique à ajouter
     */
    public void addActionHistorique(ActionHistorique action) {
        listeHistorique.add(action);
    }
    /**
     * retourne sous forme de string une action historique a l'indice passer en parametre
     * @param i index
     * @return ActionHistorique sous forma de string
     */
    public String toString(int i) {
        return listeHistorique.get(i).getDate().getDate() + "/" + listeHistorique.get(i).getDate().getMonth() + " à " + listeHistorique.get(i).getDate().getHours() + "h" + listeHistorique.get(i).getDate().getMinutes() + "  : " + listeHistorique.get(i).getTexte();
    }
}
