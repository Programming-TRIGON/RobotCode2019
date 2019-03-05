package frc.robot.Autonomous.SecondHatch;


import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.RobotComponents;
import frc.robot.DrivingCommands.DriveWithGyro;
import frc.robot.DrivingCommands.Turn;
import frc.robot.RobotConstants.RobotPIDSettings;

public class SecondHatchMiddle extends CommandGroup {
  /**
   * continues the autonomous from the feeder and puts the second hatch in the
   * middle.
   */
  public SecondHatchMiddle(boolean isLeft) {
    final double DRIVE_TO_FEEDER = 6.48;
    final double RAMP_TO_FEEDER = 2.32 + 1.2;
    final double RAMP_TO_HATCH = 5.45;
    final double TARGET_TRACK_TIME = 5;
    final double TURN_TO_FEEDER = Math.atan(180 - (RAMP_TO_FEEDER / RAMP_TO_HATCH));
    final double TURN_TO_ROCKET = -TURN_TO_FEEDER;

    // turn.
    addSequential(new Turn(TURN_TO_ROCKET * (isLeft ? 1 : -1)));

    // drive to the rocket.
    addSequential(new DriveWithGyro(DRIVE_TO_FEEDER));

    // turn to put thhe hatch.
    addSequential(new Turn(TURN_TO_ROCKET - 180));

    //delivers the hatch using vision
    /*addSequential(
        new DriveArcadeWithVision(Robot.driveTrain, VisionPIDSource.VisionTarget.kReflector, () -> 0.0,
            Robot.oi::getYLeft, RobotPIDSettings.VISION_TURN_SETTINGS, false),
        TARGET_TRACK_TIME);*/

    // score the hatch.
    // TODO: switch to the needed hatch height.

  }
}
