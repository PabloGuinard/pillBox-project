/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ss2_rpi_2021;

import com.pi4j.wiringpi.Gpio;
import com.pi4j.wiringpi.GpioUtil;

public abstract class AbstractDHT {

 
    private static final int MAXTIMINGS = 85;
    private final int[] dht_dat = {0, 0, 0, 0, 0};
    
    private int pinNumber;
  

    public AbstractDHT(){
    
    }
    
    public AbstractDHT(int pinNumber) {

        this.pinNumber = pinNumber;
        // setup wiringPi
        if (Gpio.wiringPiSetup() == -1) {
            System.out.println(" ==>> GPIO SETUP FAILED");
            return;
        }

        GpioUtil.export(pinNumber, GpioUtil.DIRECTION_OUT);
    }

    // pinNumber number = Wriring PI 
    abstract public void getTemperature();

    
}
