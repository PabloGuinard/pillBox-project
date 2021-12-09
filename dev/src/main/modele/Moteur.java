/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.modele;

import com.pi4j.component.motor.impl.GpioStepperMotorComponent;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiBcmPin;
import com.pi4j.io.gpio.RaspiGpioProvider;
import com.pi4j.io.gpio.RaspiPinNumberingScheme;
import java.util.logging.Level;
import java.util.logging.Logger;
import ss2_rpi_2021.Execute;
import ss2_rpi_2021.StepperMotorGpio;

/**
 *
 * @author p2007867
 */
public class Moteur implements Execute{
    protected int angleRotation = 0;
    protected int indexCase = 0;
    final private GpioStepperMotorComponent motor;
    final private GpioController gpio;
    
    /**
     * constructeur
     * 
     * @param angle
     * @param in1
     * @param in2
     * @param in3
     * @param in4 
     */
    public Moteur(int angle, Pin in1, Pin in2, Pin in3, Pin in4){
        angleRotation = angle;
        
        // in order to use the Broadcom GPIO pin numbering scheme, we need to configure the
        // GPIO factory to use a custom configured Raspberry Pi GPIO provider
        GpioFactory.setDefaultProvider(new RaspiGpioProvider(RaspiPinNumberingScheme.BROADCOM_PIN_NUMBERING));

        // create gpio controller
        gpio = GpioFactory.getInstance();


        // provision gpio pins #00 to #03 as output pins and ensure in LOW state
        final GpioPinDigitalOutput[] pins = {
                gpio.provisionDigitalOutputPin(in1, "IN1", PinState.HIGH),
                gpio.provisionDigitalOutputPin(in2, "IN2", PinState.HIGH),
                gpio.provisionDigitalOutputPin(in3, "IN3", PinState.HIGH),
                gpio.provisionDigitalOutputPin(in4, "IN4", PinState.HIGH)};


        // this will ensure that the motor is stopped when the program terminates
        gpio.setShutdownOptions(true, PinState.LOW, pins);

        // create motor component
        motor = new GpioStepperMotorComponent(pins);
    }

    /**
     * méthode de rotation du moteur
     */
    @Override
    public void start(){
        
        System.out.println("<--Pi4J--> GPIO Stepper Motor Example ... started.");
  
        // create byte array to demonstrate a double-step sequencing
        // (In this method two coils are turned on simultaneously.  This method does not generate
        //  a smooth movement as the previous method, and it requires double the current, but as
        //  return it generates double the torque.)
        byte[] double_step_sequence = new byte[4];
        double_step_sequence[0] = (byte) 0b0011;
        double_step_sequence[1] = (byte) 0b0110;
        double_step_sequence[2] = (byte) 0b1100;
        double_step_sequence[3] = (byte) 0b1001;

        // define stepper parameters before attempting to control motor
        // anything lower than 2 ms does not work for my sample motor using single step sequence
        motor.setStepInterval(2);
        motor.setStepSequence(double_step_sequence);

        // There are 32 steps per revolution on my sample motor, and inside is a ~1/64 reduction gear set.
        // Gear reduction is actually: (32/9)/(22/11)x(26/9)x(31/10)=63.683950617
        // This means is that there are really 32*63.683950617 steps per revolution =  2037.88641975 ~ 2038 steps!
        motor.setStepsPerRevolution(2048);

       try {
            motor.step((6114/8) * angleRotation);
            indexCase = indexCase + angleRotation;
            
            if (indexCase < 0){
                indexCase = 8 + indexCase;
            }
            
            if (indexCase > 7){
                indexCase = indexCase - 8;
            }
            
            Thread.sleep(1000);
        
        } catch (InterruptedException ex) {
           Logger.getLogger(StepperMotorGpio.class.getName()).log(Level.SEVERE, null, ex);
       }
       
        System.out.println("Motor STOPPED.");

        // final stop to ensure no motor activity
        motor.stop();

        // stop all GPIO activity/threads by shutting down the GPIO controller
        // (this method will forcefully shutdown all GPIO monitoring threads and scheduled tasks)
        gpio.shutdown();

        System.out.println("Exiting StepperMotorGpio");
    }
    
    /**
     * méthode de callibration du moteur vers la gauche
     */
    public void calibrageG(){
        byte[] double_step_sequence = new byte[4];
        double_step_sequence[0] = (byte) 0b0011;
        double_step_sequence[1] = (byte) 0b0110;
        double_step_sequence[2] = (byte) 0b1100;
        double_step_sequence[3] = (byte) 0b1001;

        // define stepper parameters before attempting to control motor
        // anything lower than 2 ms does not work for my sample motor using single step sequence
        motor.setStepInterval(2);
        motor.setStepSequence(double_step_sequence);

        // There are 32 steps per revolution on my sample motor, and inside is a ~1/64 reduction gear set.
        // Gear reduction is actually: (32/9)/(22/11)x(26/9)x(31/10)=63.683950617
        // This means is that there are really 32*63.683950617 steps per revolution =  2037.88641975 ~ 2038 steps!
        motor.setStepsPerRevolution(2048);

        motor.step((long)76.4);   
       
        // final stop to ensure no motor activity
        motor.stop();

        // stop all GPIO activity/threads by shutting down the GPIO controller
        // (this method will forcefully shutdown all GPIO monitoring threads and scheduled tasks)
        gpio.shutdown();
    }
    
    /**
     * méthode de callibration du moteur vers la droite
     */
    public void calibrageD(){
        byte[] double_step_sequence = new byte[4];
        double_step_sequence[0] = (byte) 0b0011;
        double_step_sequence[1] = (byte) 0b0110;
        double_step_sequence[2] = (byte) 0b1100;
        double_step_sequence[3] = (byte) 0b1001;

        // define stepper parameters before attempting to control motor
        // anything lower than 2 ms does not work for my sample motor using single step sequence
        motor.setStepInterval(2);
        motor.setStepSequence(double_step_sequence);

        // There are 32 steps per revolution on my sample motor, and inside is a ~1/64 reduction gear set.
        // Gear reduction is actually: (32/9)/(22/11)x(26/9)x(31/10)=63.683950617
        // This means is that there are really 32*63.683950617 steps per revolution =  2037.88641975 ~ 2038 steps!
        motor.setStepsPerRevolution(2048);

        motor.step((long)-76.4);   
       
        // final stop to ensure no motor activity
        motor.stop();

        // stop all GPIO activity/threads by shutting down the GPIO controller
        // (this method will forcefully shutdown all GPIO monitoring threads and scheduled tasks)
        gpio.shutdown();
    }
    
    /**
     * méthode de modification de l'angle
     * @param angle 
     */
    public void setAngle(int angle){
        angleRotation = angle;
    }
    
    /**
     * méthode de lecture de l'ange
     * @return angleRotation
     */
    public int getAngleRotation(){
        return angleRotation;
    }
}