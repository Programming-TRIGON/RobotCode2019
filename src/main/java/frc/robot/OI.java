package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.POVButton;
import frc.robot.CommandGroups.CargoCollectCmdG;
import frc.robot.CommandGroups.PrepareToScore;
import frc.robot.CommandGroups.Push;
import frc.robot.Commands.CollectCargo;
import frc.robot.Commands.SetDriveInverted;
import frc.robot.RobotConstants.PrepareToScoreHeight;

/**
 * Add your docs here.
 */

public class OI {
    public XboxController operatorXbox = new XboxController(0);  
    public XboxController driverXbox = new XboxController(1);
    Button driverButtonY, driverButtonA, driverButtonB, driverButtonX;
    Button operatorButtonX, operatorButtonY, operatorButtonLB, operatorButtonRB,
    operatorButtonA, operatorButtonB;
    
    public OI(){
        // driver buttons 
        this.driverButtonA = new JoystickButton(driverXbox, 1);
        this.driverButtonY = new JoystickButton(driverXbox, 4);
        this.driverButtonB = new JoystickButton(driverXbox, 2);
        this.driverButtonX = new JoystickButton(driverXbox, 3);
        
        //this.driverButtonB.whileHeld(new );
        this.driverButtonA.whenPressed(new SetDriveInverted(true));
        this.driverButtonY.whenPressed(new SetDriveInverted(false));

        // operator buttons 
        this.operatorButtonA = new JoystickButton(operatorXbox, 1);
        this.operatorButtonB = new JoystickButton(operatorXbox, 2);
        this.operatorButtonX = new JoystickButton(operatorXbox, 3);
        this.operatorButtonY = new JoystickButton(operatorXbox, 4);
        this.operatorButtonLB = new JoystickButton(operatorXbox, 5);
        this.operatorButtonRB = new JoystickButton(operatorXbox, 6);

        this.operatorButtonA.whenPressed(new CargoCollectCmdG());
        this.operatorButtonB.whenPressed(new Push());
        this.operatorButtonRB.whenPressed(new PrepareToScore(true));
        this.operatorButtonLB.whenPressed(new PrepareToScore(false));
        this.operatorButtonX.whenPressed(new PrepareToScore(PrepareToScoreHeight.kCargoShip));
    }   
}
