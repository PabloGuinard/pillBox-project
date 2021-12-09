/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;

/**
 *Component Led qui indique l'état du pilulier
 * @author Pablo
 */
public class LedMarche extends JComponent {

    private Color couleurLed = Color.red;
    private Color couleurTour = Color.white;
    
    /**
     * mutateur de la couleur de la led
     * @param couleurLed 
     */
    public void setCouleurLed(Color couleurLed) {
        this.couleurLed = couleurLed;
        repaint();
    }
    
    /**
     * mutateur de la couleur du tour de la led
     * @param couleurLed 
     */
    public void setCouleurTour(Color couleurLed) {
        this.couleurTour = couleurLed;
        repaint();
    }

    /**
     * accesseur de la taille du component
     * @return dimension
     */
    @Override
    public Dimension getPreferredSize() {
        Dimension d = new Dimension(120, 40);
        return d;
    }
    
    /**
     * accesseur de la couleur de la led
     * @return couleurLed
     */
    public Color getCouleurLed(){
        return couleurLed;
    }

    /**
     * méthode de changement de la couleur
     * @param couleur 
     */
    public void couleurLedChange(Color couleur) {
        this.setCouleurLed(couleur);
        repaint();
    }

    
    /**
     * méthode d'affichage du composant     * @param gra 
     */
    @Override
    protected void paintComponent(Graphics gra) {
        gra.setColor(couleurTour);
        gra.fillOval(90, 10, 40, 40);
        gra.setColor(couleurLed);
        gra.fillOval(96, 16, 28, 28);
    }

}
