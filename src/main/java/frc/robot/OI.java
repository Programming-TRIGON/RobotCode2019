package frc.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.POVButton;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.RobotConstants.OneEightyAngle;
import frc.robot.RobotConstants.PrepareToScoreHeight;
import frc.robot.Triggers.XboxTrigger;
import frc.robot.Commands.CancelCommand;
import frc.robot.Commands.ReflectorDrive;
import frc.robot.DrivingCommands.ToggleDriveInverted;
import frc.robot.LiftCommands.LiftSwitchOverride;
import frc.robot.OICommands.AfterCargoFloorPreparation;
import frc.robot.OICommands.AfterHatchFeederPreparation;
import frc.robot.OICommands.AfterPushPreperetion;
import frc.robot.OICommands.CollectCargoFromFloor;
import frc.robot.OICommands.CollectHatchFromFeeder;
import frc.robot.OICommands.DefenceMode;
import frc.robot.OICommands.PrepareToScore;
import frc.robot.OICommands.Push;
import frc.robot.OneEightyCommands.OneEightyToggleOverride;
import frc.robot.OneEightyCommands.SetOneEightyDesireAngle;

public class OI {
    
    public XboxController operatorXbox = new XboxController(0);
    public XboxController driverXbox = new XboxController(1);
    Button driverButtonY, driverButtonA, driverButtonB, driverButtonX, driverButtonLB, driverButtonRB;
    Button operatorButtonX, operatorButtonY, operatorButtonLB, operatorButtonRB, operatorButtonA, operatorButtonB, operatorStartButton, operatorButtonAxisRight, operatorButtonAxisLeft;
    POVButton operatorRightPOVButton, operatorLeftPOVButton, operatorBottomPOVButton, operatorTopPOVButton;
    XboxTrigger LTrigger, RTrigger; 

    UsbCamera cam0, cam1;
    UsbCamera[] cams = new UsbCamera[2];
    int currentCam = 0;

    public OI() {
        // driver buttons
        this.driverButtonA = new JoystickButton(driverXbox, 1);
        this.driverButtonB = new JoystickButton(driverXbox, 2);
        this.driverButtonX = new JoystickButton(driverXbox, 3); 
        this.driverButtonY = new JoystickButton(driverXbox, 4);
        this.driverButtonLB = new JoystickButton(driverXbox, 5);
        this.driverButtonRB = new JoystickButton(driverXbox, 6);
        this.LTrigger = new XboxTrigger(this.driverXbox, Hand.kLeft); 
        this.RTrigger = new XboxTrigger(this.driverXbox, Hand.kRight);

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
        this.operatorTopPOVButton = new POVButton(operatorXbox, 0);
        this.operatorBottomPOVButton = new POVButton(operatorXbox, 180);

        cam0 = CameraServer.getInstance().startAutomaticCapture(0);
        cam1 = CameraServer.getInstance().startAutomaticCapture(1);
        cams[0] = cam0;
        cams[1] = cam1;

        //-------------------- DRIVER --------------------------------------------
        this.driverButtonB.whenPressed(new ReflectorDrive());
        this.driverButtonB.whenReleased(new CancelCommand(() -> Robot.driveTrain.getCurrentCommand()));

        this.driverButtonA.whenPressed(new ToggleDriveInverted());
        
        this.RTrigger.whenActive(new Push());
        this.RTrigger.whenInactive(new AfterPushPreperetion());

        this.LTrigger.whenActive(new InstantCommand(() -> RobotStates.toggleOneEightyDesiredAngle())); //make cmd group for button 180 turning

        //-------------------- OPERATOR --------------------------------------------
        this.operatorButtonY.whenPressed(new CollectHatchFromFeeder());
        this.operatorButtonY.whenReleased(new AfterHatchFeederPreparation());
        
        this.operatorButtonA.whenPressed(new CollectCargoFromFloor());
        this.operatorButtonA.whenReleased(new AfterCargoFloorPreparation());

        this.operatorButtonRB.whenPressed(new PrepareToScore(true));  
        this.operatorButtonLB.whenPressed(new PrepareToScore(false));
        
        this.operatorButtonX.whenPressed(new PrepareToScore(PrepareToScoreHeight.kCargoShip)); 
        this.operatorButtonB.whenPressed(new PrepareToScore(PrepareToScoreHeight.kMedium));

        this.operatorButtonAxisRight.whenPressed(new OneEightyToggleOverride());
        this.operatorButtonAxisLeft.whenPressed(new LiftSwitchOverride());

        this.operatorLeftPOVButton.whenPressed(new SetOneEightyDesireAngle(OneEightyAngle.kBack));
        this.operatorRightPOVButton.whenPressed(new SetOneEightyDesireAngle(OneEightyAngle.kStraight));  
        
        this.operatorStartButton.whenPressed(new DefenceMode());
        
    }

	public void changeCam(int cam) {
        NetworkTableInstance.getDefault().getTable("").getEntry("cameraSelection")
        .setValue(cams[cam].getName());
        currentCam = cam;
	}

	public void tooggleCam() {
        NetworkTableInstance.getDefault().getTable("").getEntry("cameraSelection")
        .setValue(cams[1-currentCam].getName());
        currentCam = 1 - currentCam;
	}
}
