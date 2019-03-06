package frc.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.POVButton;
import frc.robot.RobotConstants.LiftHeight;
import frc.robot.RobotConstants.OneEightyAngle;
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
import frc.robot.OneEightyCommands.SetOneEightyAngle;

public class OI {
    public XboxController operatorXbox = new XboxController(0);
    public XboxController driverXbox = new XboxController(1);
    Button driverButtonY, driverButtonA, driverButtonB, driverButtonX;
    Button operatorButtonX, operatorButtonY, operatorButtonLB, operatorButtonRB, operatorButtonA, operatorButtonB, operatorStartButton, operatorButtonAxisRight, operatorButtonAxisLeft;
    POVButton operatorRightPOVButton, operatorLeftPOVButton; 

    UsbCamera cam0, cam1;
    UsbCamera[] cams = new UsbCamera[2];
    int currentCam = 0;

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

        cam0 = CameraServer.getInstance().startAutomaticCapture(0);
        cam1 = CameraServer.getInstance().startAutomaticCapture(1);
        cams[0] = cam0;
        cams[1] = cam1;


        this.operatorButtonA.whenPressed(new SetOneEightyAngle(OneEightyAngle.kStraight));
        this.operatorButtonB.whenPressed(new SetOneEightyAngle(OneEightyAngle.kBack));
        this.operatorButtonY.whenPressed(new SetOneEightyAngle(OneEightyAngle.kTopStraight));
        this.operatorButtonX.whenPressed(new SetOneEightyAngle(OneEightyAngle.kTopBack));
        this.operatorButtonRB.whenPressed(new SetOneEightyAngle(OneEightyAngle.kCargoCollection));
        //this.operatorButtonLB.whenPressed(new SetOneEightyAngle(OneEightyAngle.k));
        this.operatorButtonAxisLeft.whenPressed(new ReachLiftHeight(LiftHeight.kOneEightyCargoSafety));
        this.operatorButtonAxisRight.whenPressed(new ReachLiftHeight(LiftHeight.kCargoCollection));


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
        this.operatorButtonA.whenPressed(new AfterCargoFloorPreparation());  
        
        this.operatorStartButton.whenPressed(new DefenceMode());*/   
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
