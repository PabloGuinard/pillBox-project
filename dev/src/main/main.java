/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.pi4j.io.gpio.RaspiBcmPin;
import main.modele.Moteur;
import com.pi4j.device.pibrella.PibrellaBuzzer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import main.modele.Case;
import main.modele.HautParleur;
import main.modele.Moteur;
import main.modele.NFC;
import main.modele.Notification;
import main.modele.Patient;
import main.modele.Pilulier;
import main.modele.Referent;
import main.vue.Interface;
import ss2_rpi_2021.DigitaBCMGpio;

/**
 *
 * @author p2008965
 */
public class main {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws InterruptedException, FileNotFoundException, UnsupportedEncodingException, IOException {;
        
        ArrayList<Case> calendrier = new ArrayList<>();
        ArrayList<Referent> referents = new ArrayList<>();
        Referent patient = new Referent("...", "...", "Patient", "...", "...", "...");
        Referent referent1 = new Referent("...", "...", "Référent 1", "...", "...", "...");
        Referent referent2 = new Referent("...", "...", "Référent 2", "...", "...", "...");
        Referent referent3 = new Referent("...", "...", "Référent 3", "...", "...", "...");
        referents.add(patient);
        referents.add(referent1);
        referents.add(referent2);
        referents.add(referent3);
        calendrier.add(new Case(1,new Date()));
        calendrier.add(new Case(2,new Date()));
        calendrier.add(new Case(3,new Date()));
        calendrier.add(new Case(4,new Date()));
        calendrier.add(new Case(5,new Date()));
        calendrier.add(new Case(6,new Date()));
        calendrier.add(new Case(7,new Date()));
        
//        File rl = new File("ReferentLogs");
//        BufferedReader buffered = null;
//        
//        FileReader fileReader = new FileReader(rl);
//        buffered = new BufferedReader(fileReader);
//        String line;
//        ArrayList<String> info = new ArrayList<>();
//        while ((line = buffered.readLine()) != null) {
//            info.add(line);
//        }
//        System.out.println(info);
        

        Moteur motor = null; //si moteur non raccordé à la raspberry
//        Moteur motor = new Moteur(0, RaspiBcmPin.GPIO_22, RaspiBcmPin.GPIO_23, RaspiBcmPin.GPIO_24, RaspiBcmPin.GPIO_25); //si moteur raccordé à la raspberry
        HautParleur buzzer = null; //si hp non raccordé à la raspberry
//        HautParleur buzzer = new HautParleur(0, RaspiBcmPin.GPIO_26); //si hp raccordé à la raspberry
        
        Pilulier pilulier = new Pilulier(referents, calendrier, buzzer, motor);
//        if(info!=null){
//        pilulier.chargerInfo(info);
//        }

        Notification notif = null;
//        ArrayList<String>list=new ArrayList<>();
//        pilulier.loadHistorique();
        Interface fenetre = new Interface(pilulier, notif);
        fenetre.setVisible(true);
        
        int i = 0;
        int time = 0;
        while (i == 0) {
            fenetre.setHeureAffiche();
            time = pilulier.itsTime();
            
            if (time != 0) {
                fenetre.itsTime(time);
            }
            Thread.sleep(1000);
        }
//        pilulier.closeLog(true);
    }
}
