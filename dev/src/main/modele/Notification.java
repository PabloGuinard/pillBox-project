/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.modele;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author p2018172
 */
public class Notification {
    private Pilulier pilulier;
    protected String mail;
    protected String server;
    protected String security;
    protected String id;
    protected String password;
    
    /**
     * constructeur
     * 
     * @param mail
     * @param server
     * @param security
     * @param id
     * @param password
     * @param pilulier 
     */
    public Notification(String mail, String server, String security, String id, String password, Pilulier pilulier){
        this.mail = mail;
        this.server = server;
        this.security = security;
        this.id = id;
        this.password = password;
        this.pilulier = pilulier;
    }
    
    /**
     * méthode d'envoie de mail
     * 
     * @param alerte
     * @return always true
     */
    public boolean send(String alerte){
        for(int i = 1; i < pilulier.getReferents().size(); i++){
            if (pilulier.getReferents().get(i).getMail().contains("@")){
                String message = "sendemail -f " + mail + " -t " + pilulier.getReferents().get(i).getMail() + " -u Pilulier de " + pilulier.getReferents().get(0).getPrenom() + " -m " + alerte + " -s " + server + " -o " + security + " -xu " + id + " -xp " + password;
                System.out.println(message);
                try {
                    Runtime.getRuntime().exec(message);
                } catch (IOException ex) {
                    Logger.getLogger(Notification.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return true;
    }    
}
