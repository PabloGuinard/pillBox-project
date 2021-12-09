/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.modele;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinAnalog;
import com.pi4j.io.gpio.GpioPinAnalogInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiBcmPin;
import com.pi4j.io.gpio.RaspiGpioProvider;
import com.pi4j.io.gpio.RaspiPinNumberingScheme;
import java.util.ArrayList;

/**
 *
 * @author p2007867
 */
public class NFC {
    protected ArrayList<Integer> carte;
    
    final private GpioPinAnalogInput pin;
    final private GpioController gpio;
    
    /**
     * constructeur
     * 
     * @param pinNumber
     * @throws UnsupportedOperationException 
     */
    public NFC(Pin pinNumber) throws UnsupportedOperationException{
        // in order to use the Broadcom GPIO pin numbering scheme, we need to configure the
        // GPIO factory to use a custom configured Raspberry Pi GPIO provider
        GpioFactory.setDefaultProvider(new RaspiGpioProvider(RaspiPinNumberingScheme.BROADCOM_PIN_NUMBERING));

        // create gpio controller
        gpio = GpioFactory.getInstance();

        // provision broadcom gpio pin #16 as an output pin and turn on
        pin = gpio.provisionAnalogInputPin(pinNumber, "NFC");
                
                //provisionDigitalOutputPin(pinNumber, "NFC", PinState.HIGH);

    }
    
    /**
     * méthode de lecture d'un badge
     */
    public void read(){
        boolean etat = true;
        while (etat == true)
            System.out.println(gpio.getValue(pin));  
    }    
}
