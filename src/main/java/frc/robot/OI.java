package frc.robot;

import com.spikes2212.genericsubsystems.drivetrains.commands.DriveArcade;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.POVButton;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotConstants.LiftHeight;
import frc.robot.RobotConstants.OneEightyAngle;
import frc.robot.RobotConstants.PrepareToScoreHeight;
import frc.robot.Vision.DriveArcadeWithVision;
import frc.robot.Vision.VisionPIDSource.VisionTarget;
import frc.robot.CargoCollectorCommands.CollectCargo;
import frc.robot.CargoCollectorCommands.PushCargo;
import frc.robot.CargoFolderCommands.SetCargoFolderState;
import frc.robot.Commands.CancelCommand;
import frc.robot.Commands.ReachCargoShipHeight;
import frc.robot.DrivingCommands.ToggleDriveInverted;
import frc.robot.HatchCollectorCommands.SetHatchCollectorState;
import frc.robot.HatchHolderCommands.EjectHatch;
import frc.robot.HatchHolderCommands.SetHatchLock;
import frc.robot.LiftCommands.LiftSwitchOverride;
import frc.robot.LiftCommands.ReachLiftHeight;
import frc.robot.LiftCommands.ResetLift;
import frc.robot.LiftCommands.SetHeightIndex;
import frc.robot.LiftCommands.SetLiftOverride;
import frc.robot.OICommands.AfterCargoFloorPreparation;
import frc.robot.OICommands.AfterHatchFeederPreparation;
import frc.robot.OICommands.CollectCargoFromFloor;
import frc.robot.OICommands.CollectHatchFromFeeder;
import frc.robot.OICommands.CollectHatchFromFloor;
import frc.robot.OICommands.DefenceMode;
import frc.robot.OICommands.PrepareToScore;
import frc.robot.OneEightyCommands.OneEightyToggleOverride;
import frc.robot.OneEightyCommands.SetOneEightyDesireAngle;
import frc.robot.OneEightyCommands.SetOneEightyOverride;

public class OI {
    public XboxController operatorXbox = new XboxController(0);
    public XboxController driverXbox = new XboxController(1);
    Button driverButtonY, driverButtonA, driverButtonB, driverButtonX, driverButtonLB, driverButtonRB;
    Button operatorButtonX, operatorButtonY, operatorButtonLB, operatorButtonRB, operatorButtonA, operatorButtonB, operatorStartButton, operatorButtonAxisRight, operatorButtonAxisLeft;
    POVButton operatorRightPOVButton, operatorLeftPOVButton; 

    UsbCamera cam0, cam1;
    UsbCamera[] cams = new UsbCamera[2];
    int currentCam = 0;
    private POVButton operatorBottomPOVButton;
    private POVButton operatorTopPOVButton;

    public OI() {
        // driver buttons
        this.driverButtonA = new JoystickButton(driverXbox, 1);
        this.driverButtonB = new JoystickButton(driverXbox, 2);
        this.driverButtonX = new JoystickButton(driverXbox, 3); 
        this.driverButtonY = new JoystickButton(driverXbox, 4);
        this.driverButtonLB = new JoystickButton(driverXbox, 5);
        this.driverButtonRB = new JoystickButton(driverXbox, 6);

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
        driverButtonB.whenPressed(new DriveArcadeWithVision(Robot.driveTrain, VisionTarget.kReflectorForward, () -> this.driverXbox.getY(Hand.kLeft), 
        RobotConstants.RobotPIDSettings.VISION_TURN_SETTINGS));
        driverButtonB.whenReleased(new CancelCommand(() -> Robot.driveTrain.getCurrentCommand()));

        this.driverButtonA.whenPressed(new ToggleDriveInverted()); 

        //-------------------- OPERATOR --------------------------------------------
        this.operatorButtonY.whenPressed(new CollectHatchFromFeeder());
        this.operatorButtonY.whenReleased(new AfterHatchFeederPreparation());

        this.operatorButtonRB.whenPressed(new PrepareToScore(true));  
        this.operatorButtonLB.whenPressed(new PrepareToScore(false));
        this.operatorButtonX.whenPressed(new PrepareToScore(PrepareToScoreHeight.kCargoShip)); 
        this.operatorButtonB.whenPressed(new PrepareToScore(PrepareToScoreHeight.kMedium));

        this.operatorButtonAxisRight.whenPressed(new OneEightyToggleOverride());
        this.operatorButtonAxisLeft.whenPressed(new LiftSwitchOverride());

        this.operatorLeftPOVButton.whenPressed(new SetOneEightyDesireAngle(OneEightyAngle.kBack));
        this.operatorRightPOVButton.whenPressed(new SetOneEightyDesireAngle(OneEightyAngle.kStraight));

        this.operatorButtonA.whenPressed(new CollectCargoFromFloor());
        this.operatorButtonA.whenReleased(new AfterCargoFloorPreparation());  
        
        this.operatorStartButton.whenPressed(new DefenceMode());
        
        /*
        //-------------------- DRIVER --------------------------------------------
        
        this.driverButtonLB.whenPressed(new EjectHatch());
        this.driverButtonRB.whenPressed(new PushCargo());
        this.driverButtonA.whenPressed(new ToggleDriveInverted()); 

        // driverButtonB.whenPressed(new CollectHatchFromFloor());
        // driverButtonB.whenReleased(new SetHatchCollectorState(Value.kForward));//should be cmdG?

        driverButtonY.whileHeld(new CollectCargo(-1, -1,false));

        driverButtonB.whenPressed(new DriveArcadeWithVision(Robot.driveTrain, VisionTarget.kReflectorForward, () -> this.driverXbox.getY(Hand.kLeft), 
        RobotConstants.RobotPIDSettings.VISION_TURN_SETTINGS));
        driverButtonB.whenReleased(new CancelCommand(() -> Robot.driveTrain.getCurrentCommand()));
        
        //-------------------- OPERATOR --------------------------------------------

        this.operatorButtonRB.whenPressed(new ReachCargoShipHeight());
        this.operatorButtonLB.whenPressed(new CollectHatchFromFeeder());//make cmdG like reachcargoshipheight

        this.operatorButtonA.whenPressed(new CollectCargoFromFloor());        
        this.operatorButtonA.whenReleased(new AfterCargoFloorPreparation());
        
        this.operatorButtonX.whenPressed(new CollectHatchFromFeeder());
        this.operatorButtonX.whenPressed(new AfterHatchFeederPreparation());

        this.operatorButtonY.whenPressed(new SetCargoFolderState(Value.kForward));//might change that btn

        this.operatorLeftPOVButton.whenPressed(new SetOneEightyDesireAngle(OneEightyAngle.kBack));
        this.operatorRightPOVButton.whenPressed(new SetOneEightyDesireAngle(OneEightyAngle.kStraight));

        operatorTopPOVButton.whenPressed(new SetHeightIndex(LiftHeight.kRocketMiddleHatch));
        operatorBottomPOVButton.whenPressed(new SetHeightIndex(LiftHeight.kLiftBottomHatch));

        
        this.operatorButtonB.whenPressed(new SetHatchLock(Value.kReverse));
        this.operatorButtonB.whenReleased(new SetHatchLock(Value.kForward));

        operatorStartButton.whenPressed(new ResetLift());

        this.operatorButtonAxisRight.whenPressed(new SetOneEightyOverride());
        this.operatorButtonAxisLeft.whenPressed(new SetLiftOverride());
        */
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
