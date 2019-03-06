/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Autonomous;

import com.spikes2212.genericsubsystems.drivetrains.commands.DriveArcade;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.RobotConstants;
import frc.robot.CargoCollectorCommands.PushCargo;
import frc.robot.DrivingCommands.DriveWithGyro;
import frc.robot.DrivingCommands.Turn;
import frc.robot.OICommands.PrepareToScore;

public class ScoreCargoSide extends CommandGroup {
  //distance from hab to
  final double firstDistance = 542;
  public ScoreCargoSide(boolean isLeft) {
    /*addParallel(new PrepareToScore(RobotConstants.PrepareToScoreHeight.kCargoShip));
    addSequential(new DriveWithGyro(firstDistance));    
    //might need to switch it
    addSequential(new Turn(isLeft ? 90:-90));
    addSequential(new DriveWithGyro(300),2);
    addSequential(new PushCargo());*/

    addSequential(new DriveArcade(Robot.driveTrain, 0.8, 0), 2);
  }
}
