
package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotConstants.LiftHeight;
import frc.robot.RobotConstants.OneEightyAngle;
import frc.robot.RobotConstants.PrepareToScoreHeight;
import frc.robot.CommandGroups.CollectHatch;
import frc.robot.CommandGroups.CollectHatchFromFeeder;
import frc.robot.CommandGroups.EjectHatch;
import frc.robot.CommandGroups.PrepareToScore;
import frc.robot.CommandGroups.Push;
import frc.robot.CommandGroups.SetOneEightyAngle;
import frc.robot.Commands.CollectCargo;
import frc.robot.Commands.DriveWithGyro;
import frc.robot.Commands.PushCargo;
import frc.robot.Commands.ResetLift;
import frc.robot.Commands.SetCargoFolderState;
import frc.robot.Commands.SetDriveInverted;
import frc.robot.Commands.SetHatchCollectorState;
import frc.robot.Commands.SetHatchEject;
import frc.robot.Commands.SetHatchLock;
import frc.robot.Commands.SetLiftHeight;
import frc.robot.Commands.TrackTargetByDistance;
import frc.robot.Vision.VisionPIDSource.VisionTarget;

/**
 * we use this class for checking all of the commands by running them in a row.
 */
public class Tests {
    public static int i;
    public static double DrivingDistance;
    public Tests(){
    Tests.i = 0;
    Tests.DrivingDistance = 300;
    }
    public static Command commands[] = new Command[]{
        new DriveWithGyro(Tests.DrivingDistance),
        new DriveWithGyro(-Tests.DrivingDistance),
        new SetDriveInverted(true),
        new DriveWithGyro(Tests.DrivingDistance),
        new SetDriveInverted(false),
        new DriveWithGyro(Tests.DrivingDistance),
        //TODO: remove comment
//        new DriveArcadeWithVision(Robot.driveTrain, VisionTarget.kReflector, ()->0.0, ()->Robot.oi.driverXbox.getY(), RobotConstants.RobotPIDSettings.VISION_TURN_SETTINGS, false),
//        new TrackVisionTarget(VisionTarget.kCargo, Robot.oi.driverXbox, 0.0, RobotConstants.RobotPIDSettings.VISION_TURN_SETTINGS, 0.0, RobotConstants.RobotPIDSettings.VISION_TURN_SETTINGS),
        new TrackTargetByDistance(VisionTarget.kReflector,RobotConstants.RobotPIDSettings.VISION_TURN_SETTINGS , RobotConstants.RobotPIDSettings.DRIVE_SETTINGS, 1.0),
        new SetCargoFolderState(Value.kForward),
        new SetCargoFolderState(Value.kReverse),
        new SetHatchCollectorState(Value.kForward),
        new SetHatchCollectorState(Value.kReverse),
        new CollectCargo(0.2, 0.2),
        new PushCargo(),
        new SetHatchLock(Value.kForward),
        new SetHatchLock(Value.kReverse),
        new SetHatchEject(Value.kForward),
        new SetHatchEject(Value.kReverse),
        new CollectHatchFromFeeder(),
        new CollectHatch(),
        new EjectHatch(),
        new SetLiftHeight(LiftHeight.kRocketMiddleCargo),
        //new SetLiftOverride(),
        //new OneEightySwitchOverride(),
        new SetOneEightyAngle(OneEightyAngle.kStraight),
        new ResetLift(),
        new Push(),
        new PrepareToScore(PrepareToScoreHeight.kCargoShip),};
}

