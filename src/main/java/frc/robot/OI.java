package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.POVButton;
import frc.robot.CommandGroups.AfterHatchFeederPreparation;
import frc.robot.CommandGroups.CargoCollectCmdG;
import frc.robot.CommandGroups.CollectHatch;
import frc.robot.Commands.CollectCargo;
import frc.robot.Commands.DriveArcadeWithVision;
import frc.robot.Commands.SetDriveInverted;
import frc.robot.Vision.VisionPIDSource.VisionTarget;
import frc.robot.CommandGroups.DefenceMode;
import frc.robot.CommandGroups.CollectHatchFromFeeder;
import frc.robot.Commands.LiftSwitchOverride;
import frc.robot.Commands.OneEightySwitchOverride;
import frc.robot.CommandGroups.PrepareToScore;
import frc.robot.CommandGroups.Push;
import frc.robot.Commands.SetDriveInverted;
import frc.robot.Commands.SetHasCargo;
import frc.robot.RobotConstants.PrepareToScoreHeight;

public class OI {
    public XboxController operatorXbox = new XboxController(0);
    public XboxController driverXbox = new XboxController(1);
    Button driverButtonY, driverButtonA, driverButtonB, driverButtonX;
    Button operatorButtonX, operatorButtonY, operatorButtonLB, operatorButtonRB, operatorButtonA, operatorButtonB, operatorStartButton, operatorButtonAxisRight, operatorButtonAxisLeft;
    POVButton operatorRightPOVButton, operatorLeftPOVButton; 
    public OI() {
        // driver buttons
        this.driverButtonA = new JoystickButton(driverXbox, 1);
        this.driverButtonB = new JoystickButton(driverXbox, 2);
        this.driverButtonX = new JoystickButton(driverXbox, 3);
        this.driverButtonY = new JoystickButton(driverXbox, 4);

    // this.driverButtonX.whileHeld(new );
        // this.driverButtonB.whileHeld(new );
        this.driverButtonA.whenPressed(new SetDriveInverted(true)); //checked
        this.driverButtonY.whenPressed(new SetDriveInverted(false)); //checked

        // operator buttons
        this.operatorButtonA = new JoystickButton(operatorXbox, 1);
        this.operatorButtonB = new JoystickButton(operatorXbox, 2);
        this.operatorButtonX = new JoystickButton(operatorXbox, 3);
        this.operatorButtonY = new JoystickButton(operatorXbox, 4);
        this.operatorButtonLB = new JoystickButton(operatorXbox, 5);
        this.operatorButtonRB = new JoystickButton(operatorXbox, 6);
        this.operatorStartButton = new JoystickButton(operatorXbox, 8);
        this.operatorButtonAxisLeft = new JoystickButton(operatorXbox, 9);
        this.operatorButtonAxisRight = new JoystickButton(operatorXbox, 10);
        this.operatorRightPOVButton = new POVButton(operatorXbox, 90);
        this.operatorLeftPOVButton = new POVButton(operatorXbox, 270);

        this.operatorButtonAxisRight.whenPressed(new LiftSwitchOverride()); //issue!
        this.operatorButtonAxisLeft.whenPressed(new OneEightySwitchOverride()); //checked
        //this.operatorButtonA.whenPressed(new CargoCollectCmdG()); //checked
        //this.operatorButtonB.whenPressed(new Push()); //checked
        //this.operatorButtonY.whenPressed(new CollectHatchFromFeeder()); //checked
        //this.operatorButtonY.whenReleased(new AfterHatchFeederPreparation()); //checked
        //this.operatorButtonRB.whenPressed(new PrepareToScore(true)); //checked 
        //this.operatorButtonLB.whenPressed(new PrepareToScore(false)); //checked
        //this.operatorButtonX.whenPressed(new PrepareToScore(PrepareToScoreHeight.kCargoShip)); //checked
        //this.operatorStartButton.whenPressed(new DefenceMode()); //checked
        this.operatorRightPOVButton.whenPressed(new SetHasCargo(true)); //checked
        this.operatorLeftPOVButton.whenPressed(new SetHasCargo(false)); //checked
    }
}
