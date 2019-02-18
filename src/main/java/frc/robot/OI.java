/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.Commands.SetDriveInverted;

/**
 * Add your docs here.
 */

public class OI {
    public XboxController operatorXbox = new XboxController(0);  
    public XboxController driveXboxController = new XboxController(1);
    JoystickButton driverButtonY, driverButtonA;
    
    public OI(){
        this.driverButtonA = new JoystickButton(driveXboxController, 1);
        this.driverButtonY = new JoystickButton(driveXboxController, 4);

        driverButtonA.whenPressed(new SetDriveInverted(true));
        driverButtonY.whenPressed(new SetDriveInverted(false));
    }   

    public double getYLeft(){
        return this.operatorXbox.getY(Hand.kLeft);
    }
}
