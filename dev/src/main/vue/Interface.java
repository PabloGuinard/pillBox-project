/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.Border;
import main.modele.Notification;
import main.modele.Pilulier;

/**
 * classe qui contient tout l'affichage
 *
 * @author Pablo
 */
public class Interface extends JFrame implements ActionListener, FocusListener {

    private JLabel heureAffiche = new JLabel(), infoAdresse = new JLabel(), infoTel = new JLabel(), infoMail = new JLabel(), infoFonction = new JLabel(), infoPrenom = new JLabel(), infoNom = new JLabel(), caseRemplissage = new JLabel(), caseMois = new JLabel(), caseJour = new JLabel(), caseHeure = new JLabel(), caseMinute = new JLabel(), retardAccepte = new JLabel(), remplissageOui = new JLabel(), labelCode = new JLabel();
    private JTextArea infosMenu = new JTextArea(), case1 = new JTextArea(), case2 = new JTextArea(), case3 = new JTextArea(), case4 = new JTextArea(), case5 = new JTextArea(), case6 = new JTextArea(), case7 = new JTextArea();
    private JTextField nomEcriture = new JTextField(), prenomEcriture = new JTextField(), fonctionEcriture = new JTextField(), adresseEcriture = new JTextField(), mailEcriture = new JTextField(), telEcriture = new JTextField();
    private JButton calendrier = new JButton(), informations = new JButton(), menuSU = new JButton(), panicButton = new JButton(), boutonAlerte = new JButton(), boutonMenuSU0 = new JButton(), boutonMenuSU1 = new JButton(), boutonMenuSU2 = new JButton(), boutonRetour = new JButton(), flecheGauche = new JButton(), flecheDroite = new JButton(), validerInfos = new JButton(), validerCase = new JButton(), boutonParametre = new JButton(), boutonSnake = new JButton(), boutonFondAlea = new JButton(), boutonFondNoir = new JButton(), boutonFondBlanc = new JButton(), boutonFondVert = new JButton(), boutonTexteBlanc = new JButton(), boutonTexteNoir = new JButton(), boutonCalibrage = new JButton(), bouton1 = new JButton(), bouton2 = new JButton(), bouton3 = new JButton(), bouton4 = new JButton(), bouton5 = new JButton(), bouton6 = new JButton(), bouton7 = new JButton(), bouton8 = new JButton(), bouton9 = new JButton(), bouton0 = new JButton(), boutonEffacer = new JButton(), boutonValiderCode = new JButton(), boutonCalibrage2 = new JButton(), boutonMenuSU3 = new JButton();
    private LedMarche ledMarche = new LedMarche();
    private JComboBox boxMois = new JComboBox(), boxJour = new JComboBox(), boxHeure = new JComboBox(), boxMinute = new JComboBox();
    private JCheckBox checkRetard = new JCheckBox(), checkRemplissage = new JCheckBox();

    private Pilulier pilulier;
    private Notification notif;
    private boolean boutonPressed = false, retardPilule = false;
    private int timerAlarme = 0;
    private int indexInfoLecture = 0, indexInfoEcriture = 0, indexHistorique = 0, indexCase = 0, nbCasesRestantes = 0, indexCaseOuvrir = 1, dureeTimer = 10000;
    private String tempsRestant = "00 jours, 00 heures 00 minutes", code = "", bonCode = " 7 6 7 9";
    private Timer timer = createTimer(dureeTimer);
    private boolean couleurTexte = false;

    private EnumEtat etat;
    private EnumTimer etatTimer;

    private JPanel pano = new JPanel();
    private GridBagConstraints cont = new GridBagConstraints();
    private String newLine = System.getProperty("line.separator");
    private Color blanc = Color.white;
    private Color vertFond = new Color(0, 128, 128, 255);

    //boolean qui dit si il faure retourner au menu principal ou menu SU
    private boolean tmp = true;

    private ImageIcon panicImage = setImage("images/panicImage.png", 120, 120);
    private ImageIcon calendrierImage = setImage("images/calendrierImage.png", 120, 120);
    private ImageIcon informationsImage = setImage("images/informationImage.png", 120, 120);
    private ImageIcon menuSUImage = setImage("images/menuSUImage.png", 120, 120);
    private ImageIcon flecheGaucheImage = setImage("images/flecheImageGauche.png", 50, 300);
    private ImageIcon flecheDroiteImage = setImage("images/flecheImageDroite.png", 50, 300);
    private ImageIcon parametreImage = setImage("images/parametreImage.png", 120, 120);

    /**
     * constructeur de Interface
     *
     * @param p pilulier
     * @param n notification
     * @throws InterruptedException
     */
    public Interface(Pilulier p, Notification n) throws InterruptedException {
        pilulier = p;
        notif = n;
        this.setUndecorated(true);
        //this.setTitle("fenetre");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initialisation();
        this.setSize(800, 480);
        calendrier.addActionListener(this);
        informations.addActionListener(this);
        menuSU.addActionListener(this);
        panicButton.addActionListener(this);
        boutonAlerte.addActionListener(this);
        boutonRetour.addActionListener(this);
        boutonMenuSU0.addActionListener(this);
        boutonMenuSU1.addActionListener(this);
        boutonMenuSU2.addActionListener(this);
        boutonMenuSU3.addActionListener(this);
        boutonCalibrage.addActionListener(this);
        boutonCalibrage2.addActionListener(this);
        flecheDroite.addActionListener(this);
        flecheGauche.addActionListener(this);
        nomEcriture.addFocusListener(this);
        prenomEcriture.addFocusListener(this);
        adresseEcriture.addFocusListener(this);
        telEcriture.addFocusListener(this);
        mailEcriture.addFocusListener(this);
        validerInfos.addActionListener(this);
        validerCase.addActionListener(this);
        boutonParametre.addActionListener(this);
        boutonSnake.addActionListener(this);
        boutonFondAlea.addActionListener(this);
        boutonFondNoir.addActionListener(this);
        boutonFondBlanc.addActionListener(this);
        boutonFondVert.addActionListener(this);
        boutonTexteBlanc.addActionListener(this);
        boutonTexteNoir.addActionListener(this);
        bouton1.addActionListener(this);
        bouton2.addActionListener(this);
        bouton3.addActionListener(this);
        bouton4.addActionListener(this);
        bouton5.addActionListener(this);
        bouton6.addActionListener(this);
        bouton7.addActionListener(this);
        bouton8.addActionListener(this);
        bouton9.addActionListener(this);
        bouton0.addActionListener(this);
        boutonEffacer.addActionListener(this);
        boutonValiderCode.addActionListener(this);
    }

    /**
     * initialisation de la fenêtre
     */
    public void initialisation() {
        pano.setLayout(new GridBagLayout());
        GridBagConstraints cont = new GridBagConstraints();
        Date heure = new Date();
        Border bordure = BorderFactory.createLineBorder(blanc);

        //création des composants
        //checkBox remplissage
        //remplissage des ComboBox (calendrier remplissage)
        boxMois.addItem("Janvier");
        boxMois.addItem("Février");
        boxMois.addItem("Mars");
        boxMois.addItem("Avril");
        boxMois.addItem("Mai");
        boxMois.addItem("Juin");
        boxMois.addItem("Juillet");
        boxMois.addItem("Aout");
        boxMois.addItem("Septembre");
        boxMois.addItem("Novembre");
        boxMois.addItem("Décembre");
        for (int i = 1; i < 32; i++) {
            boxJour.addItem(i);
        }
        for (int i = 0; i < 24; i++) {
            boxHeure.addItem(i);
        }
        for (int i = 0; i < 60; i++) {
            boxMinute.addItem(i);
        }

        composantsCreation();

        //affichage de tous les composants
        composantsAffiche();

        this.setContentPane(pano);
        this.pack();

        //rend les éléments invisibles au lancement
        numCaseVisible(false);
        checkRetardVisible(false);
        boxCalendrierVisible(false);
        casesCalendrierVisible(false);
        infosLabelsVisible(false);
        boutonsValiderInfosVisible(false);
        infosEcritureVisible(false, false);
        flechesVisible(false);
        boutonMenuSUVisible(false);
        boutonRetourVisible(false);
        boutonAlerteVisible(false, "");
        boutonsParametreVisible(false);
        interfaceCodeVisible(false);

        //désactive la flèche gauche
        flecheGauche.setEnabled(false);

        etat = EnumEtat.MENU;
    }

    /**
     * mise à jour de l'heure
     */
    public void setHeureAffiche() {
        Date heure = new Date();
        if (heure.getHours() < 10 && heure.getMinutes() < 10) {
            heureAffiche.setText(("0" + heure.getHours() + " : 0" + heure.getMinutes()));
        } else if (heure.getHours() < 10 && heure.getMinutes() > 9) {
            heureAffiche.setText(("0" + heure.getHours() + " : " + heure.getMinutes()));
        } else if (heure.getHours() > 9 && heure.getMinutes() < 10) {
            heureAffiche.setText((heure.getHours() + " : 0" + heure.getMinutes()));
        } else {
            heureAffiche.setText((heure.getHours() + " : " + heure.getMinutes()));
        }
        if (infosMenu.isVisible()) {
            updateTempsRestant();
            updateCasesRestantes();
            if (ledMarche.getCouleurLed() != Color.orange) {
                if (nbCasesRestantes == 0) {
                    ledMarche.setCouleurLed(Color.red);
                } else {
                    ledMarche.setCouleurLed(Color.green);
                }
            }
            String tmp = "0 jours, 0 heures, 0 minutes";
            if (nbCasesRestantes > 1) {
                infosMenu.setText("Prochaine case : " + tempsRestant + newLine + newLine + nbCasesRestantes + " cases restantes");
            } else {
                infosMenu.setText("Prochaine case : " + tempsRestant + newLine + newLine + nbCasesRestantes + " case restante");
            }
        }
    }

    /**
     * fonction qui gère toutes les actions faites dans la fenêtre
     *
     * @param e ActionEvent de toutes les actions de la fenêtre
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == panicButton) {
            etat = EnumEtat.PANICBUTTON;
            if (notif != null) {
                notif.send("SITUATION D'URGENCE Veuillez vous rendre au domicile du patient");
            }
            ledMarcheVisible(false);
            infosMenuVisible(false);
            boutonsMenuVisible(false);
            boutonAlerteVisible(true, "Urgence");
            boutonRetourVisible(true);
        } else if (e.getSource() == boutonCalibrage) {
            if (pilulier.getMotor() != null) {
                pilulier.getMotor().calibrageG();
            }
        } else if (e.getSource() == boutonCalibrage2) {
            if (pilulier.getMotor() != null) {
                pilulier.getMotor().calibrageD();
            }
        } else if (e.getSource() == boutonParametre) {
            etat = EnumEtat.PARAMETRE;
            ledMarcheVisible(false);
            infosMenuVisible(false);
            boutonsMenuVisible(false);
            heureVisible(false);
            boutonRetourVisible(true);
            boutonsParametreVisible(true);
        } else if (e.getSource() == boutonSnake) {
            System.out.println("c'est pas encore codé");
        } else if (e.getSource() == boutonFondAlea) {
            double clr1, clr2, clr3, clr4;
            clr1 = Math.random() * 1000 % 255;
            clr2 = Math.random() * 1000 % 255;
            clr3 = Math.random() * 1000 % 255;
            vertFond = new Color((int) clr1, (int) clr2, (int) clr3, 255);
            pano.setBackground(vertFond);
            composantsCreation();
        } else if (e.getSource() == boutonFondNoir) {
            vertFond = Color.black;
            pano.setBackground(vertFond);
            composantsCreation();
        } else if (e.getSource() == boutonFondBlanc) {
            vertFond = Color.white;
            pano.setBackground(vertFond);
            composantsCreation();
        } else if (e.getSource() == boutonFondVert) {
            vertFond = new Color(0, 128, 128, 255);
            pano.setBackground(vertFond);
            composantsCreation();
        } else if (e.getSource() == boutonTexteBlanc) {
            blanc = Color.white;
            ledMarche.setCouleurTour(blanc);
            couleurTexte = false;
            composantsCreation();
        } else if (e.getSource() == boutonTexteNoir) {
            blanc = Color.black;
            couleurTexte = true;
            ledMarche.setCouleurTour(blanc);
            composantsCreation();
        } else if (e.getSource() == informations) {
            etat = EnumEtat.INFOLECTURE;
            ledMarcheVisible(false);
            infosMenuVisible(false);
            boutonsMenuVisible(false);
            chargerReferent(indexInfoLecture);
            flechesVisible(true);
            boutonRetourVisible(true);
            infosEcritureVisible(true, false);
            infosLabelsVisible(true);
            boutonRetourVisible(true);
        } else if (e.getSource() == calendrier) {
            etat = EnumEtat.CALENDRIERLECTURE;
            chargerCasesLecture();
            ledMarcheVisible(false);
            infosMenuVisible(false);
            boutonsMenuVisible(false);
            boutonRetourVisible(true);
            casesCalendrierVisible(true);

        } else if (e.getSource() == menuSU) {
            tmp = true;
            etat = EnumEtat.MENUSU;
            ledMarcheVisible(false);
            boutonAlerteVisible(false, "");
            boutonsMenuVisible(false);
            infosMenuVisible(false);
            interfaceCodeVisible(true);
            boutonRetourVisible(true);
        } else if (e.getSource() == boutonAlerte) {
            switch (boutonAlerte.getText()) {
                case "Urgence": {
                    try {
                        pilulier.addHistorique("Appui sur le panic button", new Date());
                    } catch (IOException ex) {
                        Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                boutonAlerteVisible(false, "");
                boutonRetourVisible(false);
                interfaceCodeVisible(true);
                break;

                case "Heure traitement":
                    timer.stop();
                    pilulier.getCase(indexCaseOuvrir - 2).setEtatRemplissage(false);
                    System.out.println("fin de la sonnerie");
                    if (pilulier.getBuzzer() != null) {
                        pilulier.getBuzzer().stop();
                    }
                     {
                        try {
                            pilulier.addHistorique("Pilule prise à l'heure", new Date());
                        } catch (IOException ex) {
                            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    if (pilulier.getMotor() != null) {
                        pilulier.getMotor().setAngle(-(indexCaseOuvrir - 1));
                        pilulier.getMotor().start();
                    }
                    boutonAlerte.setText("Refermer");
                    timer = createTimer(dureeTimer);
                    timer.start();
//                    updateCasesRestantes();
                    etatTimer = EnumTimer.CLOSE;
                    break;

                case "Refermer":
                    System.out.println("fin sonnerie");
                    updateCasesRestantes();
                    if (nbCasesRestantes == 0) {
                        if (notif != null) {
                            notif.send("PILULIER VIDE Veuillez vous rendre au domicile du patient");
                        }
                    }
                    if (pilulier.getBuzzer() != null) {
                        pilulier.getBuzzer().stop();
                    }
                    timer.stop();
                    if (pilulier.getMotor() != null) {
                        pilulier.getMotor().setAngle(indexCaseOuvrir - 1);
                        pilulier.getMotor().start();
                    }
                    boutonAlerteVisible(false, "");
                    infosMenuVisible(true);
                    boutonsMenuVisible(true);
                    ledMarcheVisible(true);

            }
        } else if (e.getSource() == boutonRetour) {
            switch (etat) {
                case PARAMETRE:
                    boutonsParametreVisible(false);
                    heureVisible(true);
                    break;
                case MENUSU:
                    boutonRetourVisible(false);
                    boutonMenuSUVisible(false);
                    interfaceCodeVisible(false);
                    break;
                case BADGE:
                    //faire le badge
                    break;
                case INFOLECTURE:
                    indexInfoLecture = 0;
                    flechesVisible(false);
                    infosEcritureVisible(false, false);
                    boutonsValiderInfosVisible(false);
                    infosLabelsVisible(false);
                    flecheGauche.setEnabled(false);
                    flecheDroite.setEnabled(true);
                    break;
                case INFOECRITURE:
                    indexInfoEcriture = 0;
                    flechesVisible(false);
                    infosEcritureVisible(false, true);
                    boutonsValiderInfosVisible(false);
                    infosLabelsVisible(false);
                    flecheGauche.setEnabled(false);
                    flecheDroite.setEnabled(true);
                    break;
                case CALENDRIERLECTURE:
                    casesCalendrierVisible(false);
                    break;
                case CALENDRIERECRITURE:
                    if (pilulier.getMotor() != null) {
                        pilulier.getMotor().setAngle(indexCase + 1);
                        pilulier.getMotor().start();
                    }
                    indexCase = 1;
                    boxCalendrierVisible(false);
                    flechesVisible(false);
                    numCaseVisible(false);
                    checkRetardVisible(false);
                    flecheGauche.setEnabled(false);
                    flecheDroite.setEnabled(true);
                    break;
                case HISTORIQUE:
                    indexHistorique = 0;
                    infosEcritureVisible(false, false);
                    flechesVisible(false);
                    flecheGauche.setEnabled(false);
                    flecheDroite.setEnabled(true);
                    break;
                case ANCIEN:
                    indexHistorique = 0;
                    infosEcritureVisible(false, false);
                    flechesVisible(false);
                    flecheGauche.setEnabled(false);
                    flecheDroite.setEnabled(true);
                    break;
                case PANICBUTTON:
                    boutonAlerteAffiche("");
                    boutonAlerteVisible(false, "");
                    interfaceCodeVisible(false);

            }
            if (tmp == true) {
                ledMarcheVisible(true);
                boutonRetourVisible(false);
                boutonMenuSUVisible(false);
                boutonAlerteVisible(false, "");
                boutonsMenuVisible(true);
                infosMenuVisible(true);
                etat = EnumEtat.MENU;
            } else {
                boutonMenuSUVisible(true);
                etat = EnumEtat.MENUSU;
            }
            tmp = true;
        } else if (e.getSource() == boutonMenuSU0) {
            etat = EnumEtat.INFOECRITURE;
            tmp = false;
            chargerReferent(indexInfoEcriture);
            ledMarcheVisible(false);
            boutonMenuSUVisible(false);
            flechesVisible(true);
            boutonRetourVisible(true);
            infosEcritureVisible(true, true);
            boutonsValiderInfosVisible(true);
            infosLabelsVisible(true);
        } else if (e.getSource() == boutonMenuSU1) {
            etat = EnumEtat.CALENDRIERECRITURE;
            tmp = false;
            boutonMenuSUVisible(false);
            boxCalendrierVisible(true);
            flechesVisible(true);
            numCaseVisible(true);
            checkRetardVisible(true);
            if (pilulier.getMotor() != null) {
                pilulier.getMotor().setAngle(-1);
                pilulier.getMotor().start();
            }
            indexCase = 0;
            chargerCaseRemplissage(0);

        } else if (e.getSource() == boutonMenuSU2) {
            tmp = false;
            etat = EnumEtat.HISTORIQUE;
            boutonMenuSUVisible(false);
            flechesVisible(true);
            infosEcritureVisible(true, false);
            chargerHistorique(indexHistorique);
        } else if (e.getSource() == boutonMenuSU3) {
            tmp = false;
            etat = EnumEtat.ANCIEN;
            boutonMenuSUVisible(false);
            flechesVisible(true);
            infosEcritureVisible(true, false);
            try {
                chargerLogHistorique(indexHistorique);
            } catch (IOException ex) {
                Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource() == validerInfos) {
            infosEcriture(indexInfoEcriture);
            if (indexInfoEcriture == 0) {
                try {
                    pilulier.addHistorique("Patient modifié", new Date());
                } catch (IOException ex) {
                    Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    pilulier.addHistorique("Référent " + (indexInfoEcriture) + " modifié", new Date());
                } catch (IOException ex) {
                    Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } else if (e.getSource() == validerCase) {
            caseEcriture(indexCase);

            try {
                pilulier.addHistorique("Horaire de la case " + (indexCase + 1) + " modifié", new Date());
            } catch (IOException ex) {
                Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource() == flecheGauche) {
            switch (etat) {
                case CALENDRIERECRITURE:
                    if (pilulier.getMotor() != null) {
                        pilulier.getMotor().setAngle(1);
                        pilulier.getMotor().start();
                    }
                    indexCase--;
                    chargerCaseRemplissage(indexCase);
                    flecheDroite.setEnabled(true);
                    if (indexCase == 0) {
                        flecheGauche.setEnabled(false);
                    }
                    break;
                case INFOLECTURE:
                    indexInfoLecture--;
                    chargerReferent(indexInfoLecture);
                    flecheDroite.setEnabled(true);
                    if (indexInfoLecture == 0) {
                        flecheGauche.setEnabled(false);
                    }
                    break;
                case INFOECRITURE:
                    indexInfoEcriture--;
                    chargerReferent(indexInfoEcriture);
                    flecheDroite.setEnabled(true);
                    if (indexInfoEcriture == 0) {
                        flecheGauche.setEnabled(false);
                    }
                    break;
                case HISTORIQUE:
                    indexHistorique--;
                    chargerHistorique(indexHistorique);
                    flecheDroite.setEnabled(true);
                    if (indexHistorique == 0) {
                        flecheGauche.setEnabled(false);
                    }
                    break;
                case ANCIEN:
            try {
                    indexHistorique--;
                    chargerLogHistorique(indexHistorique);
                } catch (IOException ex) {
                    Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
                }
                flecheDroite.setEnabled(true);
                if (indexHistorique == 0) {
                    flecheGauche.setEnabled(false);
                }
                break;
            }
        } else if (e.getSource() == flecheDroite) {
            switch (etat) {
                case CALENDRIERECRITURE:
                    if (pilulier.getMotor() != null) {
                        pilulier.getMotor().setAngle(-1);
                        pilulier.getMotor().start();
                    }
                    indexCase++;
                    chargerCaseRemplissage(indexCase);
                    boutonRetour.requestFocus();
                    flecheGauche.setEnabled(true);
                    if (indexCase == 6) {
                        flecheDroite.setEnabled(false);
                    }
                    break;
                case INFOLECTURE:
                    indexInfoLecture++;
                    chargerReferent(indexInfoLecture);
                    flecheGauche.setEnabled(true);
                    if (indexInfoLecture == 3) {
                        flecheDroite.setEnabled(false);
                    }
                    break;
                case INFOECRITURE:
                    indexInfoEcriture++;
                    chargerReferent(indexInfoEcriture);
                    flecheGauche.setEnabled(true);
                    boutonRetour.requestFocus();
                    if (indexInfoEcriture == 3) {
                        flecheDroite.setEnabled(false);
                    }
                    break;
                case HISTORIQUE:
                    indexHistorique++;
                    chargerHistorique(indexHistorique);
                    flecheGauche.setEnabled(true);
                    boutonRetour.requestFocus();
                    if (indexHistorique >= pilulier.getSizeHistorique() / 6) {
                        flecheDroite.setEnabled(false);
                    }
                    break;
                case ANCIEN:
            try {
                    indexHistorique++;
                    chargerLogHistorique(indexHistorique);
                } catch (IOException ex) {
                    Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
                }
                flecheGauche.setEnabled(true);
                boutonRetour.requestFocus();
                if (indexHistorique >= pilulier.getSizeHistorique() / 6) {
                    flecheDroite.setEnabled(false);
                }
                break;
            }
        } else if (e.getSource() == bouton1) {
            if (code.length() < bonCode.length()) {
                code += " 1";
                labelCode.setText(code);
            }
        } else if (e.getSource() == bouton2) {
            if (code.length() < bonCode.length()) {
                code += " 2";
                labelCode.setText(code);
            }
        } else if (e.getSource() == bouton3) {
            if (code.length() < bonCode.length()) {
                code += " 3";
                labelCode.setText(code);
            }
        } else if (e.getSource() == bouton4) {
            if (code.length() < bonCode.length()) {
                code += " 4";
                labelCode.setText(code);
            }
        } else if (e.getSource() == bouton5) {
            if (code.length() < bonCode.length()) {
                code += " 5";
                labelCode.setText(code);
            }
        } else if (e.getSource() == bouton6) {
            if (code.length() < bonCode.length()) {
                code += " 6";
                labelCode.setText(code);
            }
        } else if (e.getSource() == bouton7) {
            if (code.length() < bonCode.length()) {
                code += " 7";
                labelCode.setText(code);
            }
        } else if (e.getSource() == bouton8) {
            if (code.length() < bonCode.length()) {
                code += " 8";
                labelCode.setText(code);
            }
        } else if (e.getSource() == bouton9) {
            if (code.length() < bonCode.length()) {
                code += " 9";
                labelCode.setText(code);
            }
        } else if (e.getSource() == bouton0) {
            if (code.length() < bonCode.length()) {
                code += " 0";
                labelCode.setText(code);
            }
        } else if (e.getSource() == boutonEffacer) {
            if (code.length() > 1) {
                code = code.substring(0, code.length() - 2);
            }
            labelCode.setText(code);
        } else if (e.getSource() == boutonValiderCode) {
            if (code.equals(bonCode)) {
                if (etat == EnumEtat.MENUSU) {
                    try {
                        pilulier.addHistorique("Référent 1 a accédé au menu SU", new Date());
                    } catch (IOException ex) {
                        Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    ledMarche.setCouleurLed(Color.green);
                    interfaceCodeVisible(false);
                    boutonMenuSUVisible(true);
                } else {
                    try {
                        if (notif != null) {
                            notif.send("SITUATION D'URGENCE Referent arrive sur place");
                        }
                        pilulier.addHistorique("Référent arrivé", new Date());
                    } catch (IOException ex) {
                        Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    interfaceCodeVisible(false);
                    boutonsMenuVisible(true);
                    infosMenuVisible(true);
                    ledMarcheVisible(true);
                }
            } else {
                code = "";
                labelCode.setText(code);
            }
        }

    }

    /**
     * placement de l'interface du code secret
     */
    public void interfaceCodeAffiche() {
        cont.gridx = 0;
        cont.gridy = 1;
        pano.add(bouton1, cont);
        cont.gridy = 2;
        pano.add(bouton4, cont);
        cont.gridy = 3;
        pano.add(bouton7, cont);
        cont.gridx = 1;
        cont.gridy = 1;
        pano.add(bouton2, cont);
        cont.gridy = 2;
        pano.add(bouton5, cont);
        cont.gridy = 3;
        pano.add(bouton8, cont);
        cont.gridx = 2;
        cont.gridy = 1;
        pano.add(bouton3, cont);
        cont.gridy = 2;
        pano.add(bouton6, cont);
        cont.gridy = 3;
        pano.add(bouton9, cont);
        cont.gridx = 3;
        cont.gridy = 1;
        cont.gridwidth = 2;
        pano.add(labelCode, cont);
        cont.gridy = 3;
        pano.add(bouton0, cont);
        cont.gridwidth = 1;
        cont.gridy = 2;
        pano.add(boutonEffacer, cont);
        cont.gridx = 4;
        pano.add(boutonValiderCode, cont);
    }

    /**
     * placement des boutons du menu Paramètre
     */
    public void boutonsParametreAffiche() {
        cont.gridx = 0;
        cont.gridy = 1;
        pano.add(boutonFondAlea, cont);
        cont.gridy = 2;
        pano.add(boutonFondNoir, cont);
        cont.gridy = 3;
        pano.add(boutonFondBlanc, cont);
        cont.gridx = 1;
        cont.gridy = 1;
        pano.add(boutonFondVert, cont);
        cont.gridy = 2;
        pano.add(boutonTexteNoir, cont);
        cont.gridy = 3;
        pano.add(boutonTexteBlanc, cont);
        cont.gridy = 4;
        cont.gridx = 0;
        cont.gridwidth = 2;
        pano.add(boutonSnake, cont);
        cont.gridwidth = 1;
    }

    /**
     * placement du bouton paramètre du menu principal
     */
    public void boutonParametreAffiche() {
        cont.gridx = 4;
        cont.gridy = 3;
        pano.add(boutonParametre, cont);
    }

    /**
     * placement du bouton Valider de l'interface Remplissage
     */
    public void boutonValiderCaseAffiche() {
        cont.gridx = 3;
        cont.gridy = 4;
        cont.gridwidth = 2;
        cont.gridheight = 2;
        pano.add(validerCase, cont);
        cont.gridwidth = 1;
        cont.gridwidth = 1;
    }

    /**
     * placement du Label Numéro de case de l'interface Remplissage
     */
    public void numCaseAffiche() {
        cont.gridx = 1;
        cont.gridy = 1;
        cont.gridwidth = 4;
        pano.add(caseRemplissage, cont);
        cont.gridwidth = 1;
    }

    /**
     * placement des CheckBox/Labels de retard et de remplissage de l'interface
     * Remplissage
     */
    public void checkRetardAffiche() {
        cont.gridx = 3;
        cont.gridy = 2;
        pano.add(retardAccepte, cont);
        cont.gridx = 4;
        pano.add(checkRetard, cont);
        cont.gridx = 3;
        cont.gridy = 3;
        pano.add(remplissageOui, cont);
        cont.gridx = 4;
        pano.add(checkRemplissage, cont);
    }

    /**
     * placement de toutes les ComboBox de l'interface Remplissage
     */
    public void boxCalendrierAffiche() {
        cont.gridx = 1;
        cont.gridy = 2;
        pano.add(caseMois, cont);
        cont.gridx = 2;
        pano.add(caseJour, cont);
        cont.gridx = 1;
        cont.gridy = 3;
        pano.add(boxMois, cont);
        cont.gridx = 2;
        pano.add(boxJour, cont);
        cont.gridy = 4;
        cont.gridx = 1;
        pano.add(caseHeure, cont);
        cont.gridx = 2;
        pano.add(caseMinute, cont);
        cont.gridx = 1;
        cont.gridy = 5;
        pano.add(boxHeure, cont);
        cont.gridx = 2;
        pano.add(boxMinute, cont);
    }

    /**
     * placement de tous les TextFields de l'interface Calendrier
     */
    public void casesCalendrierAffiche() {
        cont.insets = new Insets(10, 10, 10, 10);
        cont.gridx = 0;
        cont.gridy = 1;
        pano.add(case1, cont);
        cont.gridx = 1;
        pano.add(case2, cont);
        cont.gridx = 2;
        pano.add(case3, cont);
        cont.gridx = 3;
        pano.add(case4, cont);
        cont.gridy = 2;
        cont.gridx = 0;
        pano.add(case5, cont);
        cont.gridx = 1;
        pano.add(case6, cont);
        cont.gridx = 2;
        pano.add(case7, cont);
    }

    /**
     * placement des labels des interfaces Infos Lecture/Ecriture
     */
    public void infosLabelsAffiche() {
        cont.weightx = 1 / 2;
        cont.gridx = 1;
        cont.gridy = 1;
        pano.add(infoFonction, cont);
        cont.gridy = 2;
        pano.add(infoPrenom, cont);
        cont.gridy = 3;
        pano.add(infoNom, cont);
        cont.gridy = 4;
        pano.add(infoAdresse, cont);
        cont.gridy = 5;
        pano.add(infoTel, cont);
        cont.gridy = 6;
        pano.add(infoMail, cont);
        cont.gridwidth = 1;
        cont.weightx = 1;
    }

    /**
     * placement des TextFields des interfaces Historique, Ancien et Infos
     * Lecture/Ecriture
     */
    public void infosEcritureAffiche() {
        cont.gridwidth = 4;
        cont.weighty = 2 / 1;
        cont.gridx = 2;
        cont.gridy = 1;
        pano.add(fonctionEcriture, cont);
        cont.gridy = 2;
        pano.add(prenomEcriture, cont);
        cont.gridy = 3;
        pano.add(nomEcriture, cont);
        cont.gridy = 4;
        pano.add(adresseEcriture, cont);
        cont.gridy = 5;
        pano.add(telEcriture, cont);
        cont.gridy = 6;
        pano.add(mailEcriture, cont);
        cont.gridwidth = 1;
        cont.weighty = 1;
    }

    /**
     * plcement du bouton Valider de l'interface Infos Lecture
     */
    public void boutonValiderInfosAffiche() {
        cont.gridwidth = 2;
        cont.gridheight = 1;
        cont.gridx = 1;
        cont.gridy = 7;
        pano.add(validerInfos, cont);
        cont.gridwidth = 1;
    }

    /**
     * placement des fleches des interfaces Historique, Ancien, Infos
     * Lecture/Ecriture et Remplissage
     */
    public void flechesAffiche() {
        cont.gridwidth = 1;
        cont.weightx = 1 / 100;
        cont.gridheight = 7;
        cont.gridx = 0;
        cont.gridy = 1;
        pano.add(flecheGauche, cont);
        cont.gridx = 7;
        pano.add(flecheDroite, cont);
        cont.gridheight = 1;
        cont.weightx = 1;
    }

    /**
     * placement des boutons du menu principal
     */
    public void boutonsMenuAffiche() {
        cont.weightx = 1;
        cont.insets = new Insets(20, 5, 20, 5);
        cont.insets = new Insets(10, 10, 10, 10);
        cont.gridy = 3;
        cont.gridx = 0;
        pano.add(calendrier, cont);
        cont.gridx = 1;
        pano.add(informations, cont);
        cont.gridx = 2;
        pano.add(menuSU, cont);
        cont.gridx = 3;
        pano.add(panicButton, cont);
    }

    /**
     * placement du bouton Alerte de l'interface Panic Button
     */
    public void boutonAlerteAffiche(String msg) {
        boutonAlerte.setText(msg);
        cont.fill = GridBagConstraints.BOTH;
        cont.anchor = GridBagConstraints.CENTER;
        cont.insets = new Insets(90, 5, 180, 5);
        cont.gridx = 0;
        cont.gridheight = 2;
        cont.gridwidth = 4;
        cont.gridy = 1;
        pano.add(boutonAlerte, cont);
        cont.gridheight = 1;
        cont.gridwidth = 1;
        cont.insets = new Insets(10, 10, 10, 10);
    }

    /**
     * placement des boutons du menu SU
     */
    public void boutonMenuSUAffiche() {
        cont.fill = GridBagConstraints.BOTH;
        cont.gridx = 0;
        cont.gridy = 1;
        cont.gridwidth = 4;
        pano.add(boutonMenuSU0, cont);
        cont.gridy = 2;
        pano.add(boutonMenuSU1, cont);
        cont.gridy = 3;
        pano.add(boutonMenuSU2, cont);
        cont.gridx = 0;
        cont.gridy = 4;
        cont.gridwidth = 2;
        pano.add(boutonCalibrage, cont);
        cont.gridx = 2;
        pano.add(boutonCalibrage2, cont);
        cont.gridwidth = 1;
    }

    /**
     * placement du bouton Retour de tous les menus/interfaces
     */
    public void boutonRetourAffiche() {
        cont.fill = GridBagConstraints.BOTH;
        cont.gridy = 8;
        cont.gridx = 0;
        cont.gridwidth = 10;
        cont.gridheight = 1;
        pano.add(boutonRetour, cont);
    }

    /**
     * placement de l'heure de tous les menus
     */
    public void heureAffiche() {
        cont.insets = new Insets(10, 10, 10, 10);
        cont.fill = GridBagConstraints.BOTH;
        cont.anchor = GridBagConstraints.NORTHWEST;
        cont.gridwidth = 3;
        cont.gridx = 0;
        cont.gridy = 0;
        pano.add(heureAffiche, cont);
        cont.gridwidth = 1;
    }

    /**
     * placement de la led du menu principal
     */
    public void ledMarcheAffiche() {
        cont.gridy = 0;
        cont.gridx = 4;
        pano.add(ledMarche, cont);
    }

    /**
     * placement des infos du menu principal
     */
    public void infosMenuAffiche() {
        cont.fill = GridBagConstraints.BOTH;
        cont.gridheight = 2;
        cont.gridwidth = 4;
        cont.gridy = 1;
        cont.gridx = 0;
        cont.fill = GridBagConstraints.BOTH;
        cont.insets = new Insets(52, 5, 50, 5);
        pano.add(infosMenu, cont);
        cont.gridheight = 1;
        cont.gridwidth = 1;
        cont.insets = new Insets(10, 10, 10, 10);
    }

    /**
     * gère la visibilité des boutons du menu Paramètre
     *
     * @param b true = visible / false = invisible
     */
    public void boutonsParametreVisible(boolean b) {
        boutonSnake.setVisible(false);
        boutonFondAlea.setVisible(b);
        boutonFondNoir.setVisible(b);
        boutonFondBlanc.setVisible(b);
        boutonFondVert.setVisible(b);
        boutonTexteBlanc.setVisible(b);
        boutonTexteNoir.setVisible(b);
    }

    /**
     * gère la visibilité du Label numéro de case de l'interface Remplissage
     *
     * @param b true = visible / false = invisible
     */
    public void numCaseVisible(boolean b) {
        caseRemplissage.setVisible(b);
    }

    /**
     * gère la visibilité des ComboBox de l'interface Remplissage
     *
     * @param b true = visible / false = invisible
     */
    public void boxCalendrierVisible(boolean b) {
        boxMois.setVisible(b);
        boxJour.setVisible(b);
        boxHeure.setVisible(b);
        boxMinute.setVisible(b);
        caseMinute.setVisible(b);
        caseMois.setVisible(b);
        caseJour.setVisible(b);
        caseHeure.setVisible(b);
        caseMinute.setVisible(b);
        validerCase.setVisible(b);
    }

    /**
     * gère la visibilité des checkBox/Labels de retard/remplissage de
     * l'interface Remplissage
     *
     * @param b true = visible / false = invisible
     */
    public void checkRetardVisible(boolean b) {
        retardAccepte.setVisible(b);
        remplissageOui.setVisible(b);
        checkRetard.setVisible(b);
        checkRemplissage.setVisible(b);
    }

    /**
     * gère la visibilité des TextFields de l'interface Celendrier
     *
     * @param b true = visible / false = invisible
     */
    public void casesCalendrierVisible(boolean b) {
        case1.setVisible(b);
        case2.setVisible(b);
        case3.setVisible(b);
        case4.setVisible(b);
        case5.setVisible(b);
        case6.setVisible(b);
        case7.setVisible(b);
    }

    /**
     * gère la visibilité des labels infos des interfaces Infos Lecture/Ecriture
     *
     * @param b true = visible / false = invisible
     */
    public void infosLabelsVisible(boolean b) {
        infoNom.setVisible(b);
        infoPrenom.setVisible(b);
        infoMail.setVisible(b);
        infoFonction.setVisible(b);
        infoTel.setVisible(b);
        infoAdresse.setVisible(b);
    }

    /**
     * gère la visibilité des boutons du menu principal
     *
     * @param b true = visible / false = invisible
     */
    public void boutonsMenuVisible(boolean b) {
        calendrier.setVisible(b);
        informations.setVisible(b);
        menuSU.setVisible(b);
        panicButton.setVisible(b);
    }

    /**
     * gère la visibilité du bouton Alerte de toutes les interfaces
     *
     * @param b true = visible / false = invisible
     */
    public void boutonAlerteVisible(boolean b, String txt) {
        boutonAlerte.setText(txt);
        if ("Urgence".equals(txt) | "Scanner votre  badge".equals(txt)) {
            cont.fill = GridBagConstraints.BOTH;
            cont.anchor = GridBagConstraints.CENTER;
            cont.insets = new Insets(70, 5, 130, 5);
            cont.gridx = 0;
            cont.gridheight = 2;
            cont.gridwidth = 20;
            cont.gridy = 1;
            pano.add(boutonAlerte, cont);
            cont.gridheight = 1;
            cont.gridwidth = 1;
            cont.insets = new Insets(5, 5, 5, 5);
        }
        boutonAlerte.setVisible(b);

    }

    /**
     * gère la visibilité du bouton retour de toutes les interfaces
     *
     * @param b true = visible / false = invisible
     */
    public void boutonRetourVisible(boolean b) {
        boutonRetour.setVisible(b);
    }

    /**
     * gère la visibilité des infos du menu principal
     *
     * @param b true = visible / false = invisible
     */
    public void infosMenuVisible(boolean b) {
        infosMenu.setVisible(b);
        updateCasesRestantes();
    }

    /**
     * gère la visibilité de la led du menu principal
     *
     * @param b true = visible / false = invisible
     */
    public void ledMarcheVisible(boolean b) {
        ledMarche.setVisible(b);
        boutonParametre.setVisible(b);
    }

    /**
     * gère la visibilité de l'heure de toutes les interfaces
     *
     * @param b true = visible / false = invisible
     */
    public void heureVisible(boolean b) {
        heureAffiche.setVisible(b);
    }

    /**
     * gère la visibilité des boutons du menu SU
     *
     * @param b true = visible / false = invisible
     */
    public void boutonMenuSUVisible(boolean b) {
        boutonMenuSU0.setVisible(b);
        boutonMenuSU1.setVisible(b);
        boutonMenuSU2.setVisible(b);
        boutonMenuSU3.setVisible(b);
        boutonCalibrage.setVisible(b);
        boutonCalibrage2.setVisible(b);
    }

    /**
     * gère la visibilité des fleches des interfaces Remplissage, Historique,
     * Ancien, Infos Lecture/Ecriture
     *
     * @param b true = visible / false = invisible
     */
    public void flechesVisible(boolean b) {
        flecheGauche.setVisible(b);
        flecheDroite.setVisible(b);
    }

    /**
     * gère la visibilité des TextFields des interfaces Remplissage, Historique,
     * Ancien, Infos Lecture/Ecriture
     *
     * @param b true = visible / false = invisible
     */
    public void infosEcritureVisible(boolean b, boolean c) {
        nomEcriture.setVisible(b);
        prenomEcriture.setVisible(b);
        fonctionEcriture.setVisible(b);
        mailEcriture.setVisible(b);
        telEcriture.setVisible(b);
        adresseEcriture.setVisible(b);
        nomEcriture.setEditable(c);
        prenomEcriture.setEditable(c);
        telEcriture.setEditable(c);
        mailEcriture.setEditable(c);
        adresseEcriture.setEditable(c);
    }

    /**
     * gère la visibilité du bouton Valider de l'interface Infos Ecriture
     *
     * @param b true = visible / false = invisible
     */
    public void boutonsValiderInfosVisible(boolean b) {
        validerInfos.setVisible(b);
    }

    /**
     * gère la visibilité des boutons/Labels de l'interface Code secret
     *
     * @param b true = visible / false = invisible
     */
    public void interfaceCodeVisible(boolean b) {
        code = "";
        labelCode.setText(code);
        bouton1.setVisible(b);
        bouton2.setVisible(b);
        bouton3.setVisible(b);
        bouton4.setVisible(b);
        bouton5.setVisible(b);
        bouton6.setVisible(b);
        bouton7.setVisible(b);
        bouton8.setVisible(b);
        bouton9.setVisible(b);
        bouton0.setVisible(b);
        boutonEffacer.setVisible(b);
        boutonValiderCode.setVisible(b);
        labelCode.setVisible(b);
    }

    /**
     * set les CheckBox
     *
     * @param bx CheckBox à set
     */
    public void setCheckBox(JCheckBox bx) {
        bx.setBackground(vertFond);

    }

    /**
     * set les ComboBox
     *
     * @param bx ComboBox à set
     */
    public void setComboBox(JComboBox bx) {
        Border bordure = BorderFactory.createLineBorder(blanc);
        bx.setBackground(vertFond);
        bx.setBorder(bordure);
        bx.setForeground(blanc);
        bx.setFont(new Font("Arial", Font.BOLD, 20));
    }

    /**
     * set les boutons de menu avec une icone
     *
     * @param bt bouton à set
     * @param img icone à set sur le bouton
     */
    public void setBoutonMenu(JButton bt, ImageIcon img) {
        bt.setIcon(img);
        bt.setOpaque(false);
        bt.setBorderPainted(false);
        bt.setBackground(vertFond);
    }

    /**
     * set les boutons Flèche
     *
     * @param bt bouton à set
     * @param img image à set sur le bouton
     */
    public void setFleche(JButton bt, ImageIcon img) {
        Border bordure = BorderFactory.createLineBorder(blanc);
        bt.setIcon(img);
        bt.setBorder(bordure);
        bt.setBackground(vertFond);
        bt.setForeground(blanc);
    }

    /**
     * set les boutons avec du texte
     *
     * @param bt bouton à set
     * @param txt texte à set sur le bouton
     * @param sze taille de la police du texte
     * @param clr couleur du background du bouton
     */
    public void setBoutonTexte(JButton bt, String txt, int sze, Color clr) {
        Border bordure = BorderFactory.createLineBorder(clr);
        bt.setText(txt);
        bt.setBackground(clr);
        bt.setForeground(vertFond);
        if (clr == Color.red) {
            if (couleurTexte) {
                bt.setForeground(Color.black);
            } else {
                bt.setForeground(Color.white);
            }
        }
        bt.setFont(new Font("Arial", Font.BOLD, sze));
    }

    /**
     * set un label
     *
     * @param lbl label à set
     * @param sze taille de la police du texte
     * @param clr couleur de la bordure du label
     * @param b centre le texte du bouton
     * @param txt text à set sur le label
     */
    public void setLabel(JLabel lbl, int sze, Color clr, boolean b, String txt) {
        Border bordure = BorderFactory.createLineBorder(clr);
        if (b) {
            lbl.setHorizontalAlignment(SwingConstants.CENTER);
        }
        lbl.setBorder(bordure);
        lbl.setText(txt);
        lbl.setFont(new Font("Arial", Font.BOLD, sze));
        lbl.setForeground(blanc);
    }

    /**
     * set une TextArea
     *
     * @param area TextArea à set
     * @param txt texte à set sur la TextArea
     */
    public void setTextArea(JTextArea area, String txt) {
        Border bordure = BorderFactory.createLineBorder(blanc);
        area.setBorder(bordure);
        area.setText(txt);
        area.setName("textArea");
        area.setFont(new Font("Arial", Font.BOLD, 38));
        area.setForeground(blanc);
        area.setBackground(vertFond);
        area.setEditable(false);
    }

    /**
     * set un TextField
     *
     * @param txtf TextField à set
     * @param txt texte à set sur le TextField
     */
    public void setTextFieldInfo(JTextField txtf, String txt) {
        Border bordure = BorderFactory.createLineBorder(blanc);
        txtf.setBackground(vertFond);
        txtf.setForeground(blanc);
        txtf.setBorder(bordure);
        txtf.setFont(new Font("Arial", Font.BOLD, 25));
        txtf.setText(txt);
    }

    /**
     * set une icone
     *
     * @param chemin localisation de l'imag à set
     * @param x largeur de l'image
     * @param y hauteur de l'image
     * @return l'image qui a été set
     */
    public ImageIcon setImage(String chemin, int x, int y) {
        ImageIcon imageBase = new ImageIcon(getClass().getResource(chemin));
        Image image = imageBase.getImage();
        Image newimg = image.getScaledInstance(x, y, java.awt.Image.SCALE_SMOOTH);
        return imageBase;
    }

    /**
     * charge une page d'historique dans les TextFields prévus
     *
     * @param page page de l'hitorique à charger
     */
    public void chargerHistorique(int page) {
        fonctionEcriture.setText("");
        prenomEcriture.setText("");
        nomEcriture.setText("");
        adresseEcriture.setText("");
        telEcriture.setText("");
        mailEcriture.setText("");
        if (pilulier.getSizeHistorique() > 0 + (page * 6)) {
            fonctionEcriture.setText(pilulier.getHistorique(0 + (page * 6)));
        }
        if (pilulier.getSizeHistorique() > 1 + (page * 6)) {
            prenomEcriture.setText(pilulier.getHistorique(1 + (page * 6)));
        }
        if (pilulier.getSizeHistorique() > 2 + (page * 6)) {
            nomEcriture.setText(pilulier.getHistorique(2 + (page * 6)));
        }
        if (pilulier.getSizeHistorique() > 3 + (page * 6)) {
            adresseEcriture.setText(pilulier.getHistorique(3 + (page * 6)));
        }
        if (pilulier.getSizeHistorique() > 4 + (page * 6)) {
            telEcriture.setText(pilulier.getHistorique(4 + (page * 6)));
        }
        if (pilulier.getSizeHistorique() > 5 + (page * 6)) {
            mailEcriture.setText(pilulier.getHistorique(5 + (page * 6)));
        }
    }

    /**
     * charge les cases de l'interface Calendrier dans les TextFields prévus
     */
    public void chargerCasesLecture() {
        String tmp;
        tmp = "  Case 1" + newLine;
        if (pilulier.getCase(0).getDate().getDate() <= 9) {
            tmp += "  0" + pilulier.getCase(0).getDate().getDate();
        } else {
            tmp += "  " + pilulier.getCase(0).getDate().getDate();
        }
        if (pilulier.getCase(0).getDate().getMonth() + 1 <= 9) {
            tmp += " / 0" + (pilulier.getCase(0).getDate().getMonth() + 1);
        } else {
            tmp += " / " + (pilulier.getCase(0).getDate().getMonth() + 1);
        }
        if (pilulier.getCase(0).getDate().getHours() <= 9) {
            tmp += newLine + "  0" + pilulier.getCase(0).getDate().getHours();
        } else {
            tmp += newLine + "  " + pilulier.getCase(0).getDate().getHours();
        }
        if (pilulier.getCase(0).getDate().getMinutes() <= 9) {
            tmp += " : 0" + pilulier.getCase(0).getDate().getMinutes();
        } else {
            tmp += " : " + pilulier.getCase(0).getDate().getMinutes();
        }
        case1.setText(tmp);

        tmp = "  Case 2" + newLine;
        if (pilulier.getCase(1).getDate().getDate() <= 9) {
            tmp += "  0" + pilulier.getCase(1).getDate().getDate();
        } else {
            tmp += "  " + pilulier.getCase(1).getDate().getDate();
        }
        if (pilulier.getCase(1).getDate().getMonth() + 1 <= 9) {
            tmp += " / 0" + (pilulier.getCase(1).getDate().getMonth() + 1);
        } else {
            tmp += " / " + (pilulier.getCase(1).getDate().getMonth() + 1);
        }
        if (pilulier.getCase(1).getDate().getHours() <= 9) {
            tmp += newLine + "  0" + pilulier.getCase(1).getDate().getHours();
        } else {
            tmp += newLine + "  " + pilulier.getCase(1).getDate().getHours();
        }
        if (pilulier.getCase(1).getDate().getMinutes() <= 9) {
            tmp += " : 0" + pilulier.getCase(1).getDate().getMinutes();
        } else {
            tmp += " : " + pilulier.getCase(1).getDate().getMinutes();
        }
        case2.setText(tmp);

        tmp = "  Case 3" + newLine;
        if (pilulier.getCase(2).getDate().getDate() <= 9) {
            tmp += "  0" + pilulier.getCase(2).getDate().getDate();
        } else {
            tmp += "  " + pilulier.getCase(2).getDate().getDate();
        }
        if (pilulier.getCase(2).getDate().getMonth() + 1 <= 9) {
            tmp += " / 0" + (pilulier.getCase(2).getDate().getMonth() + 1);
        } else {
            tmp += " / " + (pilulier.getCase(2).getDate().getMonth() + 1);
        }
        if (pilulier.getCase(2).getDate().getHours() <= 9) {
            tmp += newLine + "  0" + pilulier.getCase(2).getDate().getHours();
        } else {
            tmp += newLine + "  " + pilulier.getCase(2).getDate().getHours();
        }
        if (pilulier.getCase(2).getDate().getMinutes() <= 9) {
            tmp += " : 0" + pilulier.getCase(2).getDate().getMinutes();
        } else {
            tmp += " : " + pilulier.getCase(2).getDate().getMinutes();
        }
        case3.setText(tmp);

        tmp = "  Case 4" + newLine;
        if (pilulier.getCase(3).getDate().getDate() <= 9) {
            tmp += "  0" + pilulier.getCase(3).getDate().getDate();
        } else {
            tmp += "  " + pilulier.getCase(3).getDate().getDate();
        }
        if (pilulier.getCase(3).getDate().getMonth() + 1 <= 9) {
            tmp += " / 0" + (pilulier.getCase(3).getDate().getMonth() + 1);
        } else {
            tmp += " / " + (pilulier.getCase(3).getDate().getMonth() + 1);
        }
        if (pilulier.getCase(3).getDate().getHours() <= 9) {
            tmp += newLine + "  0" + pilulier.getCase(3).getDate().getHours();
        } else {
            tmp += newLine + "  " + pilulier.getCase(3).getDate().getHours();
        }
        if (pilulier.getCase(3).getDate().getMinutes() <= 9) {
            tmp += " : 0" + pilulier.getCase(3).getDate().getMinutes();
        } else {
            tmp += " : " + pilulier.getCase(3).getDate().getMinutes();
        }
        case4.setText(tmp);

        tmp = "  Case 5" + newLine;
        if (pilulier.getCase(4).getDate().getDate() <= 9) {
            tmp += "  0" + pilulier.getCase(4).getDate().getDate();
        } else {
            tmp += "  " + pilulier.getCase(4).getDate().getDate();
        }
        if (pilulier.getCase(4).getDate().getMonth() + 1 <= 9) {
            tmp += " / 0" + (pilulier.getCase(4).getDate().getMonth() + 1);
        } else {
            tmp += " / " + (pilulier.getCase(4).getDate().getMonth() + 1);
        }
        if (pilulier.getCase(4).getDate().getHours() <= 9) {
            tmp += newLine + "  0" + pilulier.getCase(4).getDate().getHours();
        } else {
            tmp += newLine + "  " + pilulier.getCase(4).getDate().getHours();
        }
        if (pilulier.getCase(4).getDate().getMinutes() <= 9) {
            tmp += " : 0" + pilulier.getCase(4).getDate().getMinutes();
        } else {
            tmp += " : " + pilulier.getCase(4).getDate().getMinutes();
        }
        case5.setText(tmp);

        tmp = "  Case 6" + newLine;
        if (pilulier.getCase(5).getDate().getDate() <= 9) {
            tmp += "  0" + pilulier.getCase(5).getDate().getDate();
        } else {
            tmp += "  " + pilulier.getCase(5).getDate().getDate();
        }
        if (pilulier.getCase(5).getDate().getMonth() + 1 <= 9) {
            tmp += " / 0" + (pilulier.getCase(5).getDate().getMonth() + 1);
        } else {
            tmp += " / " + (pilulier.getCase(5).getDate().getMonth() + 1);
        }
        if (pilulier.getCase(5).getDate().getHours() <= 9) {
            tmp += newLine + "  0" + pilulier.getCase(5).getDate().getHours();
        } else {
            tmp += newLine + "  " + pilulier.getCase(5).getDate().getHours();
        }
        if (pilulier.getCase(5).getDate().getMinutes() <= 9) {
            tmp += " : 0" + pilulier.getCase(5).getDate().getMinutes();
        } else {
            tmp += " : " + pilulier.getCase(5).getDate().getMinutes();
        }
        case6.setText(tmp);

        tmp = "  Case 7" + newLine;
        if (pilulier.getCase(6).getDate().getDate() <= 9) {
            tmp += "  0" + pilulier.getCase(6).getDate().getDate();
        } else {
            tmp += "  " + pilulier.getCase(6).getDate().getDate();
        }
        if (pilulier.getCase(6).getDate().getMonth() + 1 <= 9) {
            tmp += " / 0" + (pilulier.getCase(6).getDate().getMonth() + 1);
        } else {
            tmp += " / " + (pilulier.getCase(6).getDate().getMonth() + 1);
        }
        if (pilulier.getCase(6).getDate().getHours() <= 9) {
            tmp += newLine + "  0" + pilulier.getCase(6).getDate().getHours();
        } else {
            tmp += newLine + "  " + pilulier.getCase(6).getDate().getHours();
        }
        if (pilulier.getCase(6).getDate().getMinutes() <= 9) {
            tmp += " : 0" + pilulier.getCase(6).getDate().getMinutes();
        } else {
            tmp += " : " + pilulier.getCase(6).getDate().getMinutes();
        }
        case7.setText(tmp);
    }

    /**
     * charge un référent de l'interface Infos Lecture/Ecriture dans les
     * TextFields prévus
     *
     * @param index
     */
    public void chargerReferent(int index) {
        nomEcriture.setText(pilulier.getReferents().get(index).getNom());
        prenomEcriture.setText(pilulier.getReferents().get(index).getPrenom());
        fonctionEcriture.setText(pilulier.getReferents().get(index).getFonction());
        adresseEcriture.setText(pilulier.getReferents().get(index).getAdresse());
        mailEcriture.setText(pilulier.getReferents().get(index).getMail());
        telEcriture.setText(pilulier.getReferents().get(index).getTel());
    }

    /**
     * sauvegarde dans Pilulier les informations d'un référent qui sont set dans
     * les TextFields prévus
     *
     * @param index inde de la liste de Pilulier à sauvegarder
     */
    public void infosEcriture(int index) {
        pilulier.getReferents().get(index).setPrenom(prenomEcriture.getText());
        pilulier.getReferents().get(index).setNom(nomEcriture.getText());
        pilulier.getReferents().get(index).setAge(fonctionEcriture.getText());
        pilulier.getReferents().get(index).setAdresse(adresseEcriture.getText());
        pilulier.getReferents().get(index).setTel(telEcriture.getText());
        pilulier.getReferents().get(index).setMail(mailEcriture.getText());
    }

    /**
     * charge les informations d'une case dans l'interface Rmeplissage dans les
     * ComboBox/CheckBox prévues
     *
     * @param index index de la liste de Case à charger
     */
    public void chargerCaseRemplissage(int index) {
        caseRemplissage.setText("Case " + (index + 1));
        checkRetard.setSelected(pilulier.getCase(index).getRetardAccepte());
        boxMinute.setSelectedItem(pilulier.getCase(index).getDate().getMinutes());
        boxHeure.setSelectedItem(pilulier.getCase(index).getDate().getHours());
        boxJour.setSelectedItem(pilulier.getCase(index).getDate().getDate());
        boxMois.setSelectedIndex(pilulier.getCase(index).getDate().getMonth());
        checkRemplissage.setSelected(pilulier.getCase(index).getEtatRemplissage());
    }

    /**
     * sauvegarde dans Pilulier les informations d'une case qui sont set dans
     * les ComboBox/CheckBox prévues
     *
     * @param index index de la liste de Case à sauvegarder
     */
    public void caseEcriture(int index) {
        Date date = new Date();
        date.setMonth((int) boxMois.getSelectedIndex());
        date.setDate((int) boxJour.getSelectedItem());
        date.setHours((int) boxHeure.getSelectedItem());
        date.setMinutes((int) boxMinute.getSelectedItem());
        date.setSeconds(0);
        pilulier.getCase(index).setDate(date);
        pilulier.getCase(index).setEtatRemplissage(true);
        pilulier.getCase(index).setRetardAccepte(checkRetard.isSelected());
        pilulier.getCase(index).setEtatRemplissage(checkRemplissage.isSelected());

    }

    /**
     * met à jour la variable qui indique le nombre de cases remplies restantes
     */
    public void updateCasesRestantes() {
        int tmp = 0;
        for (int i = 0; i < pilulier.getCalendrierSize(); i++) {
            if (pilulier.getCase(i).getEtatRemplissage()) {
                tmp++;
            }
        }
        nbCasesRestantes = tmp;
    }

    /**
     * met à jour la variable qui indique le temps restant avant la prochaine
     * alerte
     */
    public void updateTempsRestant() {
        int tmp = 0;
        Date date = new Date();
        Date pro = new Date(2030, 01, 01, 01, 01);
        for (int i = 0; i < pilulier.getCalendrierSize(); i++) {
            if (pro.compareTo(pilulier.getCase(i).getDate()) > 0 && pilulier.getCase(i).getDate().getTime() > date.getTime() && pilulier.getCase(i).getEtatRemplissage()) {
                tmp = i;
                pro = pilulier.getCase(i).getDate();
            }
        }
        long diff = pro.getTime() - date.getTime();
        long max = 2630974545L * 12;
        long jours = (diff / (1000 * 60 * 60 * 24)), heures = (diff / (1000 * 60 * 60)) % 24, minutes = (diff / (1000 * 60)) % 60;
        if (diff < 0 | pilulier.getCase(tmp).getDate().getTime() < date.getTime() | diff >= max) {
            tempsRestant = "non définie";
        } else if (jours == 0 && heures == 0 && minutes == 0) {
            tempsRestant = "moins d'une minute";
        } else if (jours == 0 && heures == 0) {
            tempsRestant = minutes + " minutes";
        } else if (jours == 0) {
            tempsRestant = heures + " heures et " + minutes + " minutes";
        } else {
            tempsRestant = jours + " jours, " + heures + " heures et " + minutes + " minutes";
        }
    }

    /**
     * procotocole d'ouverture/fermeture d'une case
     *
     * @param index index de la case à ouvrir
     * @return true si la fonction s'est bien déroulée
     */
    public boolean itsTime(int index) {
        //efface tous les composants
        numCaseVisible(false);
        ledMarcheVisible(false);
        checkRetardVisible(false);
        boxCalendrierVisible(false);
        casesCalendrierVisible(false);
        infosLabelsVisible(false);
        boutonsValiderInfosVisible(false);
        infosEcritureVisible(false, false);
        flechesVisible(false);
        boutonMenuSUVisible(false);
        boutonRetourVisible(false);
        boutonAlerteVisible(false, "");
        infosMenuVisible(false);
        boutonsMenuVisible(false);
        //affiche bouton alerte
        System.out.println("début sonnerie");
        indexCaseOuvrir = index + 1;
        boutonAlerteVisible(true, "Heure traitement");
        if (pilulier.getBuzzer() != null) {
            pilulier.getBuzzer().start();
        }
        timer = createTimer(dureeTimer);
        timer.start();
        etatTimer = EnumTimer.ITSTIME;
        return true;
    }

    /**
     * vide un TextField quand on clique dessus
     *
     * @param e FocusEvent de toute l'interface
     */
    @Override
    public void focusGained(FocusEvent e) {
        if (etat == EnumEtat.INFOECRITURE) {
            if (e.getSource() == nomEcriture) {
                nomEcriture.setText("");
            } else if (e.getSource() == telEcriture) {
                telEcriture.setText("");
            } else if (e.getSource() == mailEcriture) {
                mailEcriture.setText("");
            } else if (e.getSource() == prenomEcriture) {
                prenomEcriture.setText("");
            } else if (e.getSource() == adresseEcriture) {
                adresseEcriture.setText("");
            }
        }
    }

    /**
     * inutilisé
     *
     * @param e FocusEvent de toute l'interface
     */
    @Override
    public void focusLost(FocusEvent e) {
        ;
    }

    /**
     * crée un Timer et gère les actions liées
     *
     * @param duree durée du timer(millisecondes)
     * @return le Timer créé
     */
    private Timer createTimer(int duree) {
        ActionListener action;
        action = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                switch (etatTimer) {
                    case ITSTIME:
                        if (notif != null) {
                            notif.send("TRAITEMENT EN RETARD Veuillez vous rendre au domicile du patient");
                        }
                        try {
                            pilulier.addHistorique("pilule non prise à l'heure", new Date());

                        } catch (IOException ex) {
                            Logger.getLogger(Interface.class
                                    .getName()).log(Level.SEVERE, null, ex);
                        }
                        System.out.println("fin de la sonnerie");
                        ledMarche.setCouleurLed(Color.orange);
                        if (pilulier.getBuzzer() != null) {
                            pilulier.getBuzzer().stop();
                        }
                        if (!pilulier.getCase(indexCaseOuvrir - 2).getRetardAccepte()) {
                            pilulier.getCase(indexCaseOuvrir - 2).setEtatRemplissage(false);
                            boutonAlerteVisible(false, "");
                            infosMenuVisible(true);
                            boutonsMenuVisible(true);
                            ledMarcheVisible(true);
                        }
                        break;
                    case CLOSE:
                        try {
                        pilulier.addHistorique("pilulier non refermé à l'heure", new Date());

                    } catch (IOException ex) {
                        Logger.getLogger(Interface.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println("début sonnerie");
                    if (pilulier.getBuzzer() != null) {
                        pilulier.getBuzzer().start();
                    }
                    break;
                }
                timer.stop();
            }
        };
        return new Timer(duree, action);
    }
    
    /**
     * affiche tous les composants de la fenêtre
     */
    public void composantsAffiche() {
        pano.setBackground(vertFond);
        heureAffiche();
        infosMenuAffiche();
        boutonsMenuAffiche();
        ledMarcheAffiche();
        boutonMenuSUAffiche();
        boutonRetourAffiche();
        flechesAffiche();
        infosEcritureAffiche();
        boutonValiderInfosAffiche();
        infosLabelsAffiche();
        casesCalendrierAffiche();
        numCaseAffiche();
        boxCalendrierAffiche();
        checkRetardAffiche();
        boutonValiderCaseAffiche();
        boutonAlerteAffiche("");
        boutonParametreAffiche();
        boutonsParametreAffiche();
        interfaceCodeAffiche();
    }

    /**
     * charge toutes les images et set tous les éléments de la fenêtre
     */
    public void composantsCreation() {
        if (couleurTexte) {
            panicImage = setImage("images/panicNoirImage.png", 120, 120);
            calendrierImage = setImage("images/calendrierNoirImage.png", 120, 120);
            informationsImage = setImage("images/informationNoirImage.png", 120, 120);
            menuSUImage = setImage("images/menuSUNoirImage.png", 120, 120);
            flecheGaucheImage = setImage("images/flecheNoirImageGauche.png", 50, 300);
            flecheDroiteImage = setImage("images/flecheNoirImageDroite.png", 50, 300);
            parametreImage = setImage("images/parametreNoirImage.png", 120, 120);
        } else {
            panicImage = setImage("images/panicImage.png", 120, 120);
            calendrierImage = setImage("images/calendrierImage.png", 120, 120);
            informationsImage = setImage("images/informationImage.png", 120, 120);
            menuSUImage = setImage("images/menuSUImage.png", 120, 120);
            flecheGaucheImage = setImage("images/flecheImageGauche.png", 50, 300);
            flecheDroiteImage = setImage("images/flecheImageDroite.png", 50, 300);
            parametreImage = setImage("images/parametreImage.png", 120, 120);
        }
        setHeureAffiche();
        setLabel(heureAffiche, 65, vertFond, false, "");
        setLabel(infoNom, 25, vertFond, false, "Nom :");
        setLabel(infoPrenom, 25, vertFond, false, "Prenom :");
        setLabel(infoAdresse, 25, vertFond, false, "Adresse :");
        setLabel(infoFonction, 25, vertFond, false, "Fonction :");
        setLabel(infoTel, 25, vertFond, false, "Tel :");
        setLabel(infoMail, 25, vertFond, false, "Mail :");
        setCheckBox(checkRetard);
        setCheckBox(checkRemplissage);
        infosMenu.setFont(new Font("Arial", Font.BOLD, 30));
        infosMenu.setForeground(blanc);
        infosMenu.setText("Prochaine case : " + newLine + tempsRestant +newLine + nbCasesRestantes + " cases restantes");
        infosMenu.setBackground(vertFond);
        infosMenu.setEditable(false);
        setLabel(caseRemplissage, 30, blanc, true, "");
        setLabel(caseMois, 20, blanc, true, "Mois");
        setLabel(caseJour, 20, blanc, true, "Jour");
        setLabel(caseHeure, 20, blanc, true, "Heure");
        setLabel(caseMinute, 20, blanc, true, "Minute");
        setLabel(labelCode, 30, blanc, true, code);
        setTextFieldInfo(nomEcriture, "Nom");
        setTextFieldInfo(prenomEcriture, "Prenom");
        setTextFieldInfo(adresseEcriture, "Adresse");
        setTextFieldInfo(fonctionEcriture, "Fonction");
        setTextFieldInfo(mailEcriture, "Mail");
        setTextFieldInfo(telEcriture, "Tel");
        fonctionEcriture.setEditable(false);
        setBoutonMenu(menuSU, menuSUImage);
        setBoutonMenu(calendrier, calendrierImage);
        setBoutonMenu(informations, informationsImage);
        setBoutonMenu(panicButton, panicImage);
        setBoutonMenu(boutonParametre, parametreImage);
        setBoutonTexte(boutonAlerte, "", 70, Color.red);
        setBoutonTexte(boutonMenuSU0, "Informations", 40, blanc);
        setBoutonTexte(boutonMenuSU1, "Remplissage", 40, blanc);
        setBoutonTexte(boutonMenuSU2, "Historique", 40, blanc);
        setBoutonTexte(boutonMenuSU3, "Ancien", 40, blanc);
        setBoutonTexte(boutonCalibrage, "Calibrage G", 40, blanc);
        setBoutonTexte(boutonCalibrage2, "Calibrage D", 40, blanc);
        setBoutonTexte(boutonSnake, "Snake", 50, blanc);
        setBoutonTexte(boutonFondAlea, "Fond aléa", 40, blanc);
        setBoutonTexte(boutonFondNoir, "Fond noir", 40, blanc);
        setBoutonTexte(boutonFondBlanc, "Fond blanc", 40, blanc);
        setBoutonTexte(boutonFondVert, "Fond vert", 40, blanc);
        setBoutonTexte(boutonTexteBlanc, "Texte blanc", 40, blanc);
        setBoutonTexte(boutonTexteNoir, "Texte noir", 40, blanc);
        setBoutonTexte(bouton1, "1", 30, blanc);
        setBoutonTexte(bouton2, "2", 30, blanc);
        setBoutonTexte(bouton3, "3", 30, blanc);
        setBoutonTexte(bouton4, "4", 30, blanc);
        setBoutonTexte(bouton5, "5", 30, blanc);
        setBoutonTexte(bouton6, "6", 30, blanc);
        setBoutonTexte(bouton7, "7", 30, blanc);
        setBoutonTexte(bouton8, "8", 30, blanc);
        setBoutonTexte(bouton9, "9", 30, blanc);
        setBoutonTexte(bouton0, "0", 30, blanc);
        setBoutonTexte(boutonEffacer, "Effacer", 30, blanc);
        setBoutonTexte(boutonValiderCode, "Valider", 30, blanc);
        setBoutonTexte(boutonRetour, "Retour", 30, blanc);
        setFleche(flecheGauche, flecheGaucheImage);
        setFleche(flecheDroite, flecheDroiteImage);
        setBoutonTexte(validerInfos, "Valider", 30, blanc);
        setBoutonTexte(validerCase, "Valider", 30, blanc);
        setTextArea(case1, "");
        setTextArea(case2, "");
        setTextArea(case3, "");
        setTextArea(case4, "");
        setTextArea(case5, "");
        setTextArea(case6, "");
        setTextArea(case7, "");
        setComboBox(boxMois);
        setComboBox(boxJour);
        setComboBox(boxHeure);
        setComboBox(boxMinute);
        setLabel(retardAccepte, 20, blanc, true, "Retard accepté");
        setLabel(remplissageOui, 20, blanc, true, "Case Remplie");
    }

    /**
     * charge l'historique contenu dans le fichier HistoriqueLogs dans les
     * TextFields prévus
     *
     * @param page page de l'historique à chager
     * @throws IOException renvoie si le fonctino n'a pas fonctionné
     * correctement
     */
    public void chargerLogHistorique(int page) throws IOException {
        fonctionEcriture.setText("");
        prenomEcriture.setText("");
        nomEcriture.setText("");
        adresseEcriture.setText("");
        telEcriture.setText("");
        mailEcriture.setText("");

//        pilulier.loadHistorique();
        if (pilulier.getSizeLogHistorique() > 0 + (page * 6)) {
            fonctionEcriture.setText(pilulier.getLogHistorique(0 + (page * 6)));
        }
        if (pilulier.getSizeLogHistorique() > 1 + (page * 6)) {
            prenomEcriture.setText(pilulier.getLogHistorique(1 + (page * 6)));
        }
        if (pilulier.getSizeLogHistorique() > 2 + (page * 6)) {
            nomEcriture.setText(pilulier.getLogHistorique(2 + (page * 6)));
        }
        if (pilulier.getSizeLogHistorique() > 3 + (page * 6)) {
            adresseEcriture.setText(pilulier.getLogHistorique(3 + (page * 6)));
        }
        if (pilulier.getSizeLogHistorique() > 4 + (page * 6)) {
            telEcriture.setText(pilulier.getLogHistorique(4 + (page * 6)));
        }
        if (pilulier.getSizeLogHistorique() > 5 + (page * 6)) {
            mailEcriture.setText(pilulier.getLogHistorique(5 + (page * 6)));
        }
    }
}
