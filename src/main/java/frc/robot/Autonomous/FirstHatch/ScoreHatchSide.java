/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Autonomous.FirstHatch;

import com.spikes2212.genericsubsystems.drivetrains.commands.DriveArcadeWithPID;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveTankWithPID;
import com.spikes2212.genericsubsystems.drivetrains.commands.OrientWithPID;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.RobotComponents;
import frc.robot.RobotConstants;
import frc.robot.CommandGroups.EjectHatch;
import frc.robot.Commands.SetLiftHeight;
import frc.robot.RobotConstants.LiftHeight;
import frc.robot.Vision.VisionPIDSource;
import frc.robot.Vision.VisionPIDSource.VisionDirectionType;
import frc.robot.Vision.VisionPIDSource.VisionTarget;

public class ScoreHatchSide extends CommandGroup {
  final double TURN_TO_TARGET = 90;
  final double TARGET_TRACK_TIME = 5;

  final double REVERSE_DIST = 0.5;

  enum CargoShipHatch {
    kFirstHatch(6.9 - RobotConstants.robotLength),
    kSecondHatch(7.45  - RobotConstants.robotLength),
    kThirdHatch(8 - RobotConstants.robotLength);

    public double key;
    CargoShipHatch(double distance){
      this.key = distance;
    }
  }

  public ScoreHatchSide(CargoShipHatch driveDistance, boolean isLeft) {
    // Drive towards the Cargo Ship
    addSequential(new DriveTankWithPID(Robot.driveTrain, RobotComponents.DriveTrain.LEFT_ENCODER,
    RobotComponents.DriveTrain.RIGHT_ENCODER, () -> driveDistance.key, () -> driveDistance.key, 
    RobotConstants.RobotPIDSettings.DRIVE_SETTINGS));

    // Turn towards the cargo ship
    addSequential(new OrientWithPID(Robot.driveTrain, RobotComponents.DriveTrain.GYRO, 
    TURN_TO_TARGET * (isLeft ? 1:-1), 
    RobotConstants.RobotPIDSettings.TURN_SETTINGS, 360, true));

    // Prepare Robot to deliver hatch:
    addSequential(new SetLiftHeight(LiftHeight.kRocketBottomHatch));

    // Use vision to deliver the hatch
    addSequential(new DriveArcadeWithPID(Robot.driveTrain, 
    new VisionPIDSource(VisionTarget.kReflector, VisionDirectionType.x), 0, Robot.oi.getYLeft(), 
    RobotConstants.RobotPIDSettings.REFLECTOR_TRACK_SETTINGS, 2000, false), TARGET_TRACK_TIME);

    addSequential(new EjectHatch());

    // Orient towards hatch feeder and go there for second hatch:
    // Reverse out of the cargo ship
    addSequential(new DriveTankWithPID(Robot.driveTrain, RobotComponents.DriveTrain.LEFT_ENCODER,
    RobotComponents.DriveTrain.RIGHT_ENCODER, () -> REVERSE_DIST, () -> REVERSE_DIST, 
    RobotConstants.RobotPIDSettings.DRIVE_SETTINGS));

    // turn to face the feeder 
    double start_feederDistance = 1.5;
    // angle to turn home depends on how far we drove, so 
    // tan(angle to turn) = distance between starting point and feeder / distance driven
  
    double TURN_TO_FEEDER = Math.atan(start_feederDistance / driveDistance.key);

    addSequential(new OrientWithPID(Robot.driveTrain, RobotComponents.DriveTrain.GYRO, 
    TURN_TO_FEEDER * (isLeft ? 1:-1), 
    RobotConstants.RobotPIDSettings.TURN_SETTINGS, 360, true));

    double distanceToFeeder = Math.sqrt(Math.pow(start_feederDistance, 2) +
     Math.pow(driveDistance.key, 2));

    addSequential(new DriveTankWithPID(Robot.driveTrain, RobotComponents.DriveTrain.LEFT_ENCODER,
    RobotComponents.DriveTrain.RIGHT_ENCODER, () -> distanceToFeeder, () -> distanceToFeeder, 
    RobotConstants.RobotPIDSettings.DRIVE_SETTINGS));

    // face feeder 
    addSequential(new OrientWithPID(Robot.driveTrain, RobotComponents.DriveTrain.GYRO, 
    -TURN_TO_FEEDER * (isLeft ? 1:-1), 
    RobotConstants.RobotPIDSettings.TURN_SETTINGS, 360, true));


  }

}
