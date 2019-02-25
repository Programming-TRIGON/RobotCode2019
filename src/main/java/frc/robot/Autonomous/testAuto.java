/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Autonomous;

import com.spikes2212.genericsubsystems.drivetrains.commands.OrientWithPID;
import com.spikes2212.utils.PIDSettings;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.Robot;
import frc.robot.RobotComponents;
import frc.robot.RobotConstants;
import frc.robot.Commands.DriveWithGyro;

public class testAuto extends CommandGroup {
  /**
   * insert distances for driving and turning for testing the autonomous
   */
  public testAuto() {
    addSequential(new Turn(90), 2);
    addSequential(new DriveWithGyro(15));
  }
}

