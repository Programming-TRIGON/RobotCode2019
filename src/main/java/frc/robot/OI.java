/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * Add your docs here.
 */

public class OI {
    public XboxController operatorXbox = new XboxController(0);
    public XboxController driverXbox = new XboxController(1);  
    
    public OI(){
        Button Abut = new JoystickButton(operatorXbox, 1);
        Button Bbut = new JoystickButton(operatorXbox, 2);
        Button Xbut = new JoystickButton(operatorXbox, 3); 
    }   
    public double getYLeft(){
        return this.operatorXbox.getY(Hand.kLeft);
    }
}
