package ss2_rpi_2021;

/*
 * #%L
 * **********************************************************************
 * ORGANIZATION  :  Pi4J
 * PROJECT       :  Pi4J :: Java Examples
 * FILENAME      :  StepperMotorGpio.java
 *
 * This file is part of the Pi4J project. More information about
 * this project can be found here:  https://www.pi4j.com/
 * **********************************************************************
 * %%
 * Copyright (C) 2012 - 2021 Pi4J
 * %%
 * #L%
 */

import com.pi4j.component.motor.impl.GpioStepperMotorComponent;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiBcmPin;
import com.pi4j.io.gpio.RaspiGpioProvider;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.RaspiPinNumberingScheme;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This example code demonstrates how to control a stepper motor
 * using the GPIO pins on the Raspberry Pi.
 *
 * @author Robert Savage
 *  simplified & adapted by Lionel Buathier
 *  for CX28BYJ48-08 (4096 steps)
 * 
 *  connecteurs D22 & D23 / D24 & D25 (!! cables dédoublés)
 */
public class StepperMotorGpio implements Execute{

    final private GpioStepperMotorComponent motor;
    final private GpioController gpio;
   
    public  StepperMotorGpio(Pin in1, Pin in2, Pin in3, Pin in4) {
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

        // test motor control : STEPPING FORWARD for half revolution
        System.out.println("   Motor FORWARD for 2048 steps.");
        motor.step(2048*4);
        System.out.println("   Motor STOPPED for 2 seconds.");
       try {
           Thread.sleep(2000);

            // test motor control : STEPPING REVERSE
            System.out.println("   Motor REVERSE for 2048 steps.");
            motor.step(-2048);
            
            System.out.println("   Motor STOPPED for 2 seconds.");
            Thread.sleep(2000);

            // test motor control : ROTATE FORWARD
            System.out.println("   Motor FORWARD for 2 revolutions.");
            motor.rotate(2);
            System.out.println("   Motor STOPPED for 2 seconds.");
            Thread.sleep(2000);

            // test motor control : ROTATE REVERSE
            System.out.println("   Motor REVERSE for 2 revolutions.");
            motor.rotate(-2);
            System.out.println("   Motor STOPPED for 2 seconds.");
            Thread.sleep(2000);

            // test motor control : TIMED FORWARD
            System.out.println("   Motor FORWARD for 5 seconds.");
            motor.forward(5000);
            System.out.println("   Motor STOPPED for 2 seconds.");
            Thread.sleep(2000);

            // test motor control : TIMED REVERSE
            System.out.println("   Motor REVERSE for 5 seconds.");
            motor.reverse(5000);
            System.out.println("   Motor STOPPED for 2 seconds.");
            Thread.sleep(2000);
        
        } catch (InterruptedException ex) {
           Logger.getLogger(StepperMotorGpio.class.getName()).log(Level.SEVERE, null, ex);
       }
/*
        // test motor control : ROTATE FORWARD with different timing and sequence
        System.out.println("   Motor FORWARD with slower speed and higher torque for 1 revolution.");
        motor.setStepSequence(double_step_sequence);
        motor.setStepInterval(10);
        motor.rotate(1);
*/
        System.out.println("   Motor STOPPED.");

        // final stop to ensure no motor activity
        motor.stop();

        // stop all GPIO activity/threads by shutting down the GPIO controller
        // (this method will forcefully shutdown all GPIO monitoring threads and scheduled tasks)
        gpio.shutdown();

        System.out.println("Exiting StepperMotorGpio");
    }

  
}
