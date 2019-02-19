package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.CommandGroups.CargoCollectCmdG;
import frc.robot.CommandGroups.CollectHatch;
import frc.robot.Commands.CollectCargo;
import frc.robot.Commands.DriveArcadeWithVision;
import frc.robot.Commands.SetDriveInverted;
import frc.robot.Vision.VisionPIDSource.VisionTarget;

/**
 * Add your docs here.
 */

public class OI {
    public XboxController operatorXbox = new XboxController(0);
    public XboxController driverXbox = new XboxController(1);
    Button driverButtonY, driverButtonA, driverButtonB, driverButtonX;
    Button operatorButtonX, operatorButtonY, operatorButtonLB, operatorButtonRB, operatorButtonA, operatorButtonB;

    public OI() {
        // driver buttons
        this.driverButtonA = new JoystickButton(driverXbox, 1);
        this.driverButtonY = new JoystickButton(driverXbox, 4);
        this.driverButtonB = new JoystickButton(driverXbox, 2);
        this.driverButtonX = new JoystickButton(driverXbox, 3);
        this.driverButtonB.whileHeld(new DriveArcadeWithVision(Robot.driveTrain, VisionTarget.kReflector, () -> 0.0,
                () -> driverXbox.getY(Hand.kLeft), RobotConstants.RobotPIDSettings.VISION_TURN_SETTINGS, false));
        this.driverButtonA.whenPressed(new SetDriveInverted(true));
        this.driverButtonY.whenPressed(new SetDriveInverted(false));
        this.driverButtonX.whileHeld(new CollectHatch());

        // operator buttons
        this.operatorButtonA = new JoystickButton(operatorXbox, 1);
        this.operatorButtonB = new JoystickButton(operatorXbox, 2);
        this.operatorButtonX = new JoystickButton(operatorXbox, 3);
        this.operatorButtonY = new JoystickButton(operatorXbox, 4);
        this.operatorButtonLB = new JoystickButton(operatorXbox, 5);
        this.operatorButtonRB = new JoystickButton(operatorXbox, 6);

        this.operatorButtonA.whenPressed(new CargoCollectCmdG());
    }
}
