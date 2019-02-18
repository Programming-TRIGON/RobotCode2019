/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Autonomous.SecondHatch;

import com.spikes2212.genericsubsystems.drivetrains.commands.DriveTankWithPID;
import com.spikes2212.genericsubsystems.drivetrains.commands.OrientWithPID;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.RobotComponents;
import frc.robot.RobotConstants;
import frc.robot.CommandGroups.ScoreHatch;
import frc.robot.Commands.DriveArcadeWithVision;
import frc.robot.Vision.VisionPIDSource;

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
    addSequential(new OrientWithPID(Robot.driveTrain, RobotComponents.DriveTrain.GYRO,
        TURN_TO_ROCKET * (isLeft ? 1 : -1), RobotConstants.RobotPIDSettings.TURN_SETTINGS, 360, true));

    // drive to the rocket.
    addSequential(new DriveTankWithPID(Robot.driveTrain, RobotComponents.DriveTrain.LEFT_ENCODER,
        RobotComponents.DriveTrain.RIGHT_ENCODER, () -> DRIVE_TO_FEEDER,
        RobotConstants.RobotPIDSettings.DRIVE_SETTINGS));

    // turn to put thhe hatch.
    addSequential(new OrientWithPID(Robot.driveTrain, RobotComponents.DriveTrain.GYRO, TURN_TO_ROCKET - 180,
        RobotConstants.RobotPIDSettings.TURN_SETTINGS, 360, true));

    //delivers the hatch using vision
    addSequential(
        new DriveArcadeWithVision(Robot.driveTrain, VisionPIDSource.VisionTarget.kReflector, () -> 0.0,
            Robot.oi::getYLeft, RobotConstants.RobotPIDSettings.REFLECTOR_TRACK_SETTINGS, false),
        TARGET_TRACK_TIME);
    // score the hatch.
    // TODO: switch to the needed hatch height.
    addSequential(new ScoreHatch(RobotConstants.LiftHeight.kRocketMiddleHatch));

  }
}
