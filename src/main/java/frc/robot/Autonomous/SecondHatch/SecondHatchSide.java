/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Autonomous.SecondHatch;

import com.spikes2212.genericsubsystems.drivetrains.commands.OrientWithPID;

import edu.wpi.first.wpilibj.command.CommandGroup;

import frc.robot.Robot;
import frc.robot.RobotComponents;
import frc.robot.RobotConstants;
import frc.robot.Commands.DriveArcadeWithVision;
import frc.robot.Commands.DriveWithGyro;
import frc.robot.Vision.VisionPIDSource;

public class SecondHatchSide extends CommandGroup {
  /**
   * continues the autonomous from the feeder and puts the second hatch in the in
   * one of the three side rockets.
   */
  enum CargoShipHatch {
    kFirstHatch(6.9 - RobotConstants.RobotDimensions.ROBOT_LENGTH),
    kSecondHatch(7.45 - RobotConstants.RobotDimensions.ROBOT_LENGTH),
    kThirdHatch(8 - RobotConstants.RobotDimensions.ROBOT_LENGTH);

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
    addSequential(new OrientWithPID(Robot.driveTrain, RobotComponents.DriveTrain.GYRO,
        TURN_TO_ROCKET * (isLeft ? 1 : -1), RobotConstants.RobotPIDSettings.TURN_SETTINGS, 360, true));

    // drive to the rocket
    addSequential(new DriveWithGyro(DISTANCE_TO_ROCKET));

    // turns to face the rocket for putting the hatch pannel
    addSequential(new OrientWithPID(Robot.driveTrain, RobotComponents.DriveTrain.GYRO,
        TURN_TO_HATCH * (isLeft ? 1 : -1), RobotConstants.RobotPIDSettings.TURN_SETTINGS, 360, true));

    // Use vision to deliver the hatch
    /*addSequential(new DriveArcadeWithVision(Robot.driveTrain, VisionPIDSource.VisionTarget.kReflector, () -> 0.0,
        Robot.oi::getYLeft, RobotConstants.RobotPIDSettings.VISION_TURN_SETTINGS, false), TARGET_TRACK_TIME);
    */
    // scores the hatch
  }

}
