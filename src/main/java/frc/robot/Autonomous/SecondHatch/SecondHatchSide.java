/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Autonomous.SecondHatch;

import com.spikes2212.genericsubsystems.drivetrains.commands.DriveArcadeWithPID;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveTankWithPID;
import com.spikes2212.genericsubsystems.drivetrains.commands.OrientWithPID;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.RobotComponents;
import frc.robot.RobotConstants;
import frc.robot.CommandGroups.ScoreHatch;
import frc.robot.Vision.VisionPIDSource;
import frc.robot.Vision.VisionPIDSource.VisionDirectionType;
import frc.robot.Vision.VisionPIDSource.VisionTarget;

public class SecondHatchSide extends CommandGroup {

  enum CargoShipHatch {
    kFirstHatch(6.9 - RobotConstants.robotLength), kSecondHatch(7.45 - RobotConstants.robotLength),
    kThirdHatch(8 - RobotConstants.robotLength);

    public double key;

    CargoShipHatch(double distance) {
      this.key = distance;
    }
  }

  /**
   * Add your docs here.
   */
  public SecondHatchSide(CargoShipHatch driveDistance, boolean isLeft) {
    final double start_feederDistance = 2.32;
    final double angle = Math.atan(start_feederDistance / driveDistance.key);
    final double TURN_TO_HATCH = 90 + angle;
    final double TURN_TO_ROCKET = 180 - angle;
    final double distanceToRocket = Math.sqrt(Math.pow(start_feederDistance, 2) + Math.pow(driveDistance.key, 2));
    final double TARGET_TRACK_TIME = 5;

    // turns to face the rocket for going to it from the feeder.
    addSequential(new OrientWithPID(Robot.driveTrain, RobotComponents.DriveTrain.GYRO,
        TURN_TO_ROCKET * (isLeft ? 1 : -1), RobotConstants.RobotPIDSettings.TURN_SETTINGS, 360, true));

    // drive to the rocket
    addSequential(new DriveTankWithPID(Robot.driveTrain, RobotComponents.DriveTrain.LEFT_ENCODER,
        RobotComponents.DriveTrain.RIGHT_ENCODER, distanceToRocket, RobotConstants.RobotPIDSettings.DRIVE_SETTINGS));

    // turns to face the rocket for putting the hatch pannel
    addSequential(new OrientWithPID(Robot.driveTrain, RobotComponents.DriveTrain.GYRO,
        TURN_TO_HATCH * (isLeft ? 1 : -1), RobotConstants.RobotPIDSettings.TURN_SETTINGS, 360, true));

   // Use vision to deliver the hatch
   addSequential(new DriveArcadeWithPID(Robot.driveTrain, 
   new VisionPIDSource(VisionTarget.kReflector, VisionDirectionType.x), 0, Robot.oi.getYLeft(), 
   RobotConstants.RobotPIDSettings.REFLECTOR_TRACK_SETTINGS, 2000, false), TARGET_TRACK_TIME);

  //  scores the hatch
  addSequential(new ScoreHatch(RobotConstants.LiftHeight.kRocketMiddleHatch));
  }

}
