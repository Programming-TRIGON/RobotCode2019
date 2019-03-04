package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.POVButton;
import frc.robot.RobotConstants.LiftHeight;
import frc.robot.RobotConstants.PrepareToScoreHeight;
import frc.robot.CargoCollectorCommands.CollectCargo;
import frc.robot.OICommands.CollectCargoFromFloor;
import frc.robot.Commands.SetHasCargo;
import frc.robot.DrivingCommands.ToggleDriveInverted;
import frc.robot.LiftCommands.LiftSwitchOverride;
import frc.robot.LiftCommands.ReachLiftHeight;
import frc.robot.OICommands.AfterHatchFeederPreparation;
import frc.robot.OICommands.CollectHatchFromFeeder;
import frc.robot.OICommands.DefenceMode;
import frc.robot.OICommands.PrepareToScore;
import frc.robot.OICommands.Push;
import frc.robot.OICommands.PushWhenLiftMoved;
import frc.robot.OneEightyCommands.OneEightyToggleOverride;

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

        //this.driverButtonX.whileHeld(new );
        //this.driverButtonB.whileHeld(new );
        this.driverButtonA.whenPressed(new ToggleDriveInverted()); 

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

        this.operatorButtonA.whenPressed(new ReachLiftHeight(LiftHeight.kLiftBottomHatch));
        this.operatorButtonB.whenPressed(new ReachLiftHeight(LiftHeight.kRocketMiddleHatch));
        this.operatorButtonY.whenPressed(new ReachLiftHeight(LiftHeight.kRocketTopHatch));
        this.operatorButtonX.whenPressed(new ReachLiftHeight(LiftHeight.kLiftBottomHatchCargoSide));

        /*this.operatorButtonB.whenPressed(new Push());
        this.operatorButtonB.whenReleased(new PushWhenLiftMoved());

        this.operatorButtonY.whenPressed(new CollectHatchFromFeeder());
        this.operatorButtonY.whenReleased(new AfterHatchFeederPreparation());

        this.operatorButtonRB.whenPressed(new PrepareToScore(true));  
        this.operatorButtonLB.whenPressed(new PrepareToScore(false));
        this.operatorButtonX.whenPressed(new PrepareToScore(PrepareToScoreHeight.kCargoShip)); 

        this.operatorButtonAxisRight.whenPressed(new OneEightyToggleOverride());
        this.operatorButtonAxisLeft.whenPressed(new LiftSwitchOverride());

        this.operatorRightPOVButton.whileHeld(new SetHasCargo(true)); 
        this.operatorLeftPOVButton.whileHeld(new SetHasCargo(false));

        this.operatorButtonA.whenPressed(new CollectCargoFromFloor());
        //that ensure that the cargo collector would stop spinning 
        //if CargoCollectCmdG would interapted or the drivers wont catch cargo  
        this.operatorButtonA.whenPressed(new CollectCargo(0,0));  
        
        this.operatorStartButton.whenPressed(new DefenceMode());*/   
    }
}
