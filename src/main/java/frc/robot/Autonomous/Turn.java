/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Autonomous;

import com.spikes2212.genericsubsystems.drivetrains.commands.OrientWithPID;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotComponents;
import frc.robot.RobotConstants;

public class Turn extends OrientWithPID {
  /**
   * 
   * @param angle the setpoint which the Robot turns to (using PID).
   */
  public Turn(double angle) {
    super(Robot.driveTrain, RobotComponents.DriveTrain.GYRO, angle, RobotConstants.RobotPIDSettings.TURN_SETTINGS, 360,
        true);
  }
}
