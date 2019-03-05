package frc.robot.Autonomous.SecondHatch;


import edu.wpi.first.wpilibj.command.CommandGroup;

import frc.robot.Robot;
import frc.robot.RobotComponents;
import frc.robot.DrivingCommands.DriveWithGyro;
import frc.robot.DrivingCommands.Turn;
import frc.robot.RobotConstants.RobotDimensions;
import frc.robot.RobotConstants.RobotPIDSettings;

public class SecondHatchSide extends CommandGroup {
  /**
   * continues the autonomous from the feeder and puts the second hatch in the in
   * one of the three side rockets.
   */
  enum CargoShipHatch {
    kFirstHatch(6.9 - RobotDimensions.ROBOT_LENGTH),
    kSecondHatch(7.45 - RobotDimensions.ROBOT_LENGTH),
    kThirdHatch(8 - RobotDimensions.ROBOT_LENGTH);

    public double key;

    CargoShipHatch(double distance) {
      this.key = distance;
    }
  }

  // boolean is left mirrors the course of robot dependding on its side in the
  // field.
  public SecondHatchSide(CargoShipHatch driveDistance, boolean isLeft) {
    final double RAMP_TO_FEEDER_DIST = 2.32;
    final double angle = Math.atan(RAMP_TO_FEEDER_DIST / driveDistance.key);
    final double TURN_TO_HATCH = 90 + angle;
    final double TURN_TO_ROCKET = 180 - angle;
    final double DISTANCE_TO_ROCKET = Math.sqrt(Math.pow(RAMP_TO_FEEDER_DIST, 2) + Math.pow(driveDistance.key, 2));
    final double TARGET_TRACK_TIME = 5;

    // turns to face the rocket for going to it from the feeder.
    addSequential(new Turn(TURN_TO_ROCKET * (isLeft ? 1 : -1)));

    // drive to the rocket
    addSequential(new DriveWithGyro(DISTANCE_TO_ROCKET));

    // turns to face the rocket for putting the hatch pannel
    addSequential(new Turn(TURN_TO_HATCH * (isLeft ? 1 : -1)));

    // Use vision to deliver the hatch
    /*addSequential(new DriveArcadeWithVision(Robot.driveTrain, VisionPIDSource.VisionTarget.kReflector, () -> 0.0,
        Robot.oi::getYLeft, RobotPIDSettings.VISION_TURN_SETTINGS, false), TARGET_TRACK_TIME);
    */
    // scores the hatch
  }

}
