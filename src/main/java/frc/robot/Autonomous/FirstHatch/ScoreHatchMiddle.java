/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Autonomous.FirstHatch;

import com.spikes2212.genericsubsystems.drivetrains.commands.DriveTankWithPID;
import com.spikes2212.genericsubsystems.drivetrains.commands.OrientWithPID;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.RobotComponents;
import frc.robot.RobotConstants;
import frc.robot.CommandGroups.CollectHatchFromFeeder;
import frc.robot.CommandGroups.EjectHatch;
import frc.robot.Commands.DriveArcadeWithVision;
import frc.robot.Vision.VisionPIDSource;
import frc.robot.Vision.VisionPIDSource.VisionDirectionType;
import frc.robot.Vision.VisionPIDSource.VisionTarget;

/**
 * scores the first hatch in the middle rocket then goes to ht efeeder for
 * getting the second hatch.
 */
public class ScoreHatchMiddle extends CommandGroup {
  final double FIRST_DISTANCE = 4.25 - RobotConstants.robotLength;
  final double TURN_45 = 45;
  final double SECOND_DISTANCE = 1.6907;
  final double TARGET_TRACK_TIME = 5;
  final double DRIVE_TO_FEEDER = 6.48;
  final double RAMP_TO_FEEDER = 2.32 + 1.2;
  final double RAMP_TO_HATCH = 5.45;
  final double TURN_TO_FEEDER = Math.atan(180 - (RAMP_TO_FEEDER / RAMP_TO_HATCH));

  public ScoreHatchMiddle(boolean isLeft) {
    // drive towards the cargo ship
    addSequential(new DriveTankWithPID(Robot.driveTrain, RobotComponents.DriveTrain.LEFT_ENCODER,
        RobotComponents.DriveTrain.RIGHT_ENCODER, () -> FIRST_DISTANCE, () -> FIRST_DISTANCE,
        RobotConstants.RobotPIDSettings.DRIVE_SETTINGS));

    // turn to be in line with the rocket
    addSequential(new OrientWithPID(Robot.driveTrain, RobotComponents.DriveTrain.GYRO, TURN_45 * (isLeft ? 1 : -1),
        RobotConstants.RobotPIDSettings.TURN_SETTINGS, 360, true));

    // diagonal drive in the cargo ship's general direction
    addSequential(new DriveTankWithPID(Robot.driveTrain, RobotComponents.DriveTrain.LEFT_ENCODER,
        RobotComponents.DriveTrain.RIGHT_ENCODER, () -> SECOND_DISTANCE, () -> SECOND_DISTANCE,
        RobotConstants.RobotPIDSettings.DRIVE_SETTINGS));

    // face the cargo ship
    addSequential(new OrientWithPID(Robot.driveTrain, RobotComponents.DriveTrain.GYRO, -TURN_45 * (isLeft ? 1 : -1),
        RobotConstants.RobotPIDSettings.TURN_SETTINGS, 360, true));

    // Use vision to deliver the hatch
    addSequential(
        new DriveArcadeWithVision(Robot.driveTrain, VisionPIDSource.VisionTarget.kReflector, () -> 0.0,
            Robot.oi::getYLeft, RobotConstants.RobotPIDSettings.REFLECTOR_TRACK_SETTINGS, false),
        TARGET_TRACK_TIME);

    addSequential(new EjectHatch());

    // Turn to be able to drive to the feeder
    addSequential(new OrientWithPID(Robot.driveTrain, RobotComponents.DriveTrain.GYRO,
        TURN_TO_FEEDER * (isLeft ? 1 : -1), RobotConstants.RobotPIDSettings.TURN_SETTINGS, 360, true));

    // drive to feeder
    addSequential(new DriveTankWithPID(Robot.driveTrain, RobotComponents.DriveTrain.LEFT_ENCODER,
        RobotComponents.DriveTrain.RIGHT_ENCODER, () -> DRIVE_TO_FEEDER,
        RobotConstants.RobotPIDSettings.DRIVE_SETTINGS));

    // Turn to face the feeder
    addSequential(new OrientWithPID(Robot.driveTrain, RobotComponents.DriveTrain.GYRO,
        TURN_TO_FEEDER - 180 * (isLeft ? 1 : -1), RobotConstants.RobotPIDSettings.TURN_SETTINGS, 360, true));

    // collects the hatch from the feeder
    addSequential(new CollectHatchFromFeeder());
  }
}
