/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.modele;

import com.pi4j.io.gpio.RaspiBcmPin;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import main.modele.Case;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * F@author p2008965
 */
public class Pilulier {

    protected Moteur moteur;
    protected HautParleur buzzer;
    protected Patient patient;
    protected Historique historique;
    protected ArrayList<Referent> referents;
    protected ArrayList<Case> calendrier;
    protected FileWriter histoLog;
    protected FileWriter refLog;
    protected ArrayList<String> historiqueLog;

    /**
     * constructeur par défaut
     */
    public Pilulier() {
        calendrier = new ArrayList<>();
        historique = new Historique();
        for (int i = 0; i < 8; i++) {
            calendrier.add(new Case(i + 1, new Date(2020, i, 10 + i, 30 + 2 * i, 0)));
        }
        referents = new ArrayList<>();
    }

    /**
     * constructeur
     *
     * @param r Liste de referent
     * @param c Liste de Case
     * @param b Haut parleur
     * @param m Moteur
     * @throws IOException
     */
    public Pilulier(ArrayList<Referent> r, ArrayList<Case> c, HautParleur b, Moteur m) throws IOException {
        historique = new Historique();
        referents = r;
        calendrier = c;
        buzzer = b;
        moteur = m;
//        histoLog = new FileWriter("HistoriqueLogs", true);
//        refLog = new FileWriter("ReferentLogs");
//        historiqueLog = new ArrayList<>();
    }

    /**
     * Retourne le patient
     *
     * @return patient
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * Retourne la case du calendrier à l'index passer en parametre
     *
     * @param index index 
     * @return Case
     */
    public Case getCase(int index) {
        return calendrier.get(index);
    }

    /**
     * Retourne le calendrier
     *
     * @return calendrier
     */
    public ArrayList<Case> getCalendrier() {
        return calendrier;
    }

    /**
     * Retourne la liste des referents
     *
     * @return referents
     */
    public ArrayList<Referent> getReferents() {
        return referents;
    }

    /**
     * Retourne le moteur
     *
     * @return moteur
     */
    public Moteur getMotor() {
        return moteur;
    }

    /**
     * Retourne le haut parleur
     *
     * @return buzzer
     */
    public HautParleur getBuzzer() {
        return buzzer;
    }

    /**
     * Set le haut parleur
     *
     * @param buzzer buzzer
     */
    public void setBuzzer(HautParleur buzzer) {
        this.buzzer = buzzer;
    }

    /**
     * Set le moteur
     *
     * @param moteur moteur
     */
    public void setMoteur(Moteur moteur) {
        this.moteur = moteur;
    }

    /**
     * Set le patient
     *
     * @param patient patient
     */
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    /**
     * Set la list des referents
     *
     * @param referents referents
     */
    public void setReferents(ArrayList<Referent> referents) {
        this.referents = referents;
    }

    /**
     * Set le calendrier
     *
     * @param calendrier calendrier
     */
    public void setCalendrier(ArrayList<Case> calendrier) {
        this.calendrier = calendrier;
    }

    /**
     * ajoute un referent a la liste
     *
     * @param r referent
     * @throws IOException
     */
    public void addReferent(Referent r) throws IOException {
        referents.add(r);

    }
//    public void addReferentLog(int index) throws IOException{
//        refLog.write(referents.get(index).getPrenom()+"\n");
//        refLog.write(referents.get(index).getNom()+"\n");
//        refLog.write(referents.get(index).getAdresse()+"\n");
//        refLog.write(referents.get(index).getTel()+"\n");
//        refLog.write(referents.get(index).getMail()+"\n");
//        refLog.flush();
//    } 
//    public void chargerInfo(ArrayList<String> info) throws IOException{
//        if (info.size()>=5){
//        for(int i = 0; i<(info.size()/5);i++ ){
//        refLog.write(info.get(0+(i*5))+"\n");
//        refLog.write(info.get(1+(i*5))+"\n");
//        refLog.write(info.get(2+(i*5))+"\n");
//        refLog.write(info.get(3+(i*5))+"\n");
//        refLog.write(info.get(4+(i*5))+"\n");
//        Referent ref=new Referent(info.get(1+(i*5)),info.get(0+(i*5)),"Patient",info.get(2+(i*5)),info.get(3+(i*5)),info.get(4+(i*5)));
//        referents.set(i,ref);
//        refLog.flush();
//        }
//        referents.get(1).setAge("Referent 1");
//        referents.get(2).setAge("Referent 2");
//        referents.get(3).setAge("Referent 3");
//        }
//        
//    }
//    public void loadHistorique() throws FileNotFoundException, IOException{
//        BufferedReader buffered = null;
//        File histolog=new File("HistoriqueLogs");
//        FileReader fileReader = new FileReader(histolog);
//        buffered = new BufferedReader(fileReader);
//        String line;
//        ArrayList<String> listHisto = new ArrayList<>();
//        while ((line = buffered.readLine()) != null) {
//            historiqueLog.add(line);
//        }
//        
//    }

    /**
     * Retourne une action à l'index i
     *
     * @param i index
     * @return Action dans l'historique
     */
    public String getLogHistorique(int i) {
        return historiqueLog.get(i);
    }

    /**
     * Retourne la taille de la liste des Logs de l'historique
     *
     * @return taille de la liste
     */
    public int getSizeLogHistorique() {
        return historiqueLog.size();
    }

    /**
     * Retourne la taille du calendrier
     *
     * @return taille du calendrier
     */
    public int getCalendrierSize() {
        return calendrier.size();
    }

    /**
     * Transforme une date en string pour le calendrier
     *
     * @param d la date
     * @param ind l'indice de la case
     * @return la date de la case
     */
    public String getDateString(Date d, int ind) {
        String res = "";

        res = "<html>Case " + ind + "<br/>" + (d.getDate()) + " / " + (d.getMonth() + 1) + "<br/>" + (d.getHours() + 1) + " : " + (d.getMinutes() + 1) + "</html>";

        return res;
    }

    /**
     * retourne la date du calendrier à l'indice i
     *
     * @param i index
     * @return date
     */
    public Date getdate(int i) {
        return calendrier.get(i).getDate();
    }

    /**
     * ajoute une case au calendrier
     *
     * @param c case
     */
    public void addCase(Case c) {
        c.setEtatRemplissage(true);
        calendrier.add(c);

    }

    /**
     * retourne un String contenant toutes les infi des referents et du patient
     *
     * @return information sur les referents et patient
     */
    public String getInfoAll() {
        String res = "";
        res += "réferents : \n";
        for (int i = 0; i < referents.size(); i++) {
            res += referents.get(i).getNom() + "  " + referents.get(i).getPrenom() + "  " + referents.get(i).getFonction() + "  " + referents.get(i).getMail() + "  " + referents.get(i).getTel() + "\n";
        }

        return res;
    }

    /**
     * retourne la date d'une case
     *
     * @param c case
     * @return date
     */
    public Date getCaseCalendrier(Case c) {

        return c.getDate();

    }

    /**
     * parcours le calendrier et return l'indice de la case qui doit etre prise
     * ou 0 si aucune case ne doit etre prise
     *
     * @return indice de la case ou 0
     */
    public int itsTime() {
        Date d = new Date();
        for (int i = 0; i < calendrier.size(); i++) {
            if (calendrier.get(i).getDate().getMonth() == d.getMonth() && calendrier.get(i).getDate().getDate() == d.getDate() && calendrier.get(i).getDate().getHours() == d.getHours() && calendrier.get(i).getDate().getMinutes() == d.getMinutes() && calendrier.get(i).getDate().getSeconds() == d.getSeconds() && calendrier.get(i).getEtatRemplissage()) {
                return i + 1;
            }
        }
        return 0;
    }

    /**
     * transforme une ActionHistorique en String et la retourne
     *
     * @param i indice
     * @return string de l'action
     */
    public String getHistorique(int i) {
        return historique.toString(i);
    }

    /**
     * retourne la taille de l'historique
     *
     * @return taille de l'historique
     */
    public int getSizeHistorique() {
        return historique.getListeHistorique().size();
    }

    /**
     * ajoute une action a l'historique
     *
     * @param txt action
     * @param ajrd date
     * @throws IOException
     */
    public void addHistorique(String txt, Date ajrd) throws IOException {
        ActionHistorique ah = new ActionHistorique(txt, ajrd);
        historique.addActionHistorique(ah);

//        histoLog.write(ah.ToString() + "\n");
//
//        histoLog.flush();
    }

//    public void closeLog(boolean x) throws IOException {
//
//        if (x) {
//            histoLog.close();
//            refLog.close();
//        }
//    }
}
