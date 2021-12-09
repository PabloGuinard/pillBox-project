/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.modele;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiGpioProvider;
import com.pi4j.io.gpio.RaspiPinNumberingScheme;
import java.util.logging.Level;
import java.util.logging.Logger;
import ss2_rpi_2021.DigitaBCMGpio;
import ss2_rpi_2021.Execute;

/**
 *
 * @author p2007867
 */
public class HautParleur implements Execute{
    protected int intensite;
    protected boolean etatHP;
    final private GpioPinDigitalOutput pin;
    final private GpioController gpio;
    
    /**
     * constructeur
     * 
     * @param i intensité du heaut-parleur
     * @param pinNumber pins du haut-parleur
     * @throws UnsupportedOperationException 
     */
    public HautParleur(int i, Pin pinNumber) throws UnsupportedOperationException{
        intensite= i;
        // in order to use the Broadcom GPIO pin numbering scheme, we need to configure the
        // GPIO factory to use a custom configured Raspberry Pi GPIO provider
        GpioFactory.setDefaultProvider(new RaspiGpioProvider(RaspiPinNumberingScheme.BROADCOM_PIN_NUMBERING));

        // create gpio controller
        gpio = GpioFactory.getInstance();

        // provision broadcom gpio pin #16 as an output pin and turn on
        pin = gpio.provisionDigitalOutputPin(pinNumber, "Haut-Parleur", PinState.HIGH);
        
        // set shutdown state for this pin
        pin.setShutdownOptions(true, PinState.LOW);      
    }
    
    /**
     * méthode d'activation du haut-parleur
     */
    @Override
    public void start()  {
        System.out.println("<--Pi4J--> GPIO Control LED on GPIO BCM_16 ... started.");

        System.out.println("--> GPIO state should be: ON");

        etatHP = true;
        
        try {
            while (etatHP == true){
                pin.high();
                Thread.sleep(intensite);
                pin.low();
                Thread.sleep(intensite);
            }
            
        }catch (InterruptedException ex) {
            System.out.println("test");
            Logger.getLogger(DigitaBCMGpio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * méthode d'arrêt du haut-parleur
     */
    public void stop(){
        etatHP = false;
    }
    
    /**
     * mutateur de l'intensité du haut-parleur
     * @param i intensité du heaut-parleur
     */
    public void setIntensite(int i){
        intensite = i;
    }
    
    /**
     * accesseur de l'intensité du haut-parleur
     * @return intensite intensité du heaut-parleur
     */
    public int getIntensite(){
        return intensite;
    }
}