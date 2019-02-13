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
import frc.robot.CommandGroups.CollectHatch;
import frc.robot.CommandGroups.CollectHatchFromFeeder;

public abstract class BaseAutoCommand extends CommandGroup {
  /**
   * Add your docs here.
   */
  public BaseAutoCommand() {
    // Drive off platform and align against platform
    // TODO: create ArcadeDrive that isFinishes when a supplier (a joystick button) is released
    //addSequential(new DriveArcade(Robot.driveTrain, Robot.oi.operatorXbox::getX, Robot.oi.operatorXbox::getY));

    // drive to first hatch destination 
    driveToFirstHatch();

    // place hatch 
    // TODO: get vision commands

    // drive to hatch feeder 
    addSequential(new DriveArcade(Robot.driveTrain, Robot.oi.operatorXbox::getX, Robot.oi.operatorXbox::getY));

    // collect second hatch 
    addSequential(new CollectHatchFromFeeder());

    // Vision allign to hatch

    // drive to second hatch desetination
    driveToSecondHatch();

    // place second hatch
    // vision commands.... 
  }

  abstract protected void driveToFirstHatch();

  abstract protected void driveToSecondHatch();
}
