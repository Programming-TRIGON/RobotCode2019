package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.POVButton;
import frc.robot.CommandGroups.CargoCollectCmdG;
import frc.robot.CommandGroups.DefenceMode;
import frc.robot.CommandGroups.CollectHatchFromFeeder;
import frc.robot.Commands.LiftSwitchOverride;
import frc.robot.Commands.OneEightySwitchOverride;
import frc.robot.CommandGroups.PrepareToScore;
import frc.robot.CommandGroups.Push;
import frc.robot.Commands.SetDriveInverted;
import frc.robot.Commands.SetHatchLock;
import frc.robot.Commands.SwitchGamePiece;
import frc.robot.RobotConstants.PrepareToScoreHeight;

public class OI {
    public XboxController operatorXbox = new XboxController(0);
    //TODO:remove comments
    public XboxController driverXbox = new XboxController(1);
    Button driverButtonY, driverButtonA, driverButtonB, driverButtonX, operatorButtonAxisLeft;
    Button operatorButtonX, operatorButtonY, operatorButtonLB, operatorButtonRB, operatorButtonA, operatorButtonB, operatorStartButton, operatorButtonAxisRight;
    POVButton operatorRightPOVButton, operatorLeftPOVButton; 
    public OI() {
        //TODO:REMOVE COMMENTS
        // driver buttons
        this.driverButtonA = new JoystickButton(driverXbox, 1);
        this.driverButtonY = new JoystickButton(driverXbox, 4);
        this.driverButtonB = new JoystickButton(driverXbox, 2);
        this.driverButtonX = new JoystickButton(driverXbox, 3); 

        // this.driverButtonB.whileHeld(new );
        this.driverButtonA.whenPressed(new SetDriveInverted(true));
        this.driverButtonY.whenPressed(new SetDriveInverted(false)); 

        // operator buttons
        this.operatorButtonA = new JoystickButton(operatorXbox, 1);
        this.operatorButtonB = new JoystickButton(operatorXbox, 2);
        this.operatorButtonX = new JoystickButton(operatorXbox, 3);
        this.operatorButtonY = new JoystickButton(operatorXbox, 4);
        this.operatorButtonLB = new JoystickButton(operatorXbox, 5);
        this.operatorButtonRB = new JoystickButton(operatorXbox, 6);
        this.operatorButtonAxisLeft = new JoystickButton(operatorXbox, 9);
        this.operatorButtonAxisRight = new JoystickButton(operatorXbox, 10);
        this.operatorRightPOVButton = new POVButton(operatorXbox, 90);
        this.operatorLeftPOVButton = new POVButton(operatorXbox, 270);
        this.operatorStartButton = new JoystickButton(operatorXbox, 8);

        this.operatorButtonAxisRight.whenPressed(new LiftSwitchOverride());
        this.operatorButtonAxisLeft.whenPressed(new OneEightySwitchOverride());
        this.operatorButtonA.whenPressed(new CargoCollectCmdG());
        this.operatorButtonB.whenPressed(new Push());
        this.operatorButtonY.whenPressed(new CollectHatchFromFeeder());
        this.operatorButtonY.whenReleased(new SetHatchLock(Value.kForward));
        this.operatorButtonRB.whenPressed(new PrepareToScore(true));
        this.operatorButtonLB.whenPressed(new PrepareToScore(false));
        this.operatorButtonX.whenPressed(new PrepareToScore(PrepareToScoreHeight.kCargoShip));
        this.operatorStartButton.whenPressed(new DefenceMode());
        this.operatorRightPOVButton.whenPressed(new SwitchGamePiece(true));
        this.operatorLeftPOVButton.whenPressed(new SwitchGamePiece(false));
    }
}
