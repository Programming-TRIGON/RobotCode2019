/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.CommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.RobotConstants;
import frc.robot.Commands.SetOneEightyAngle;
import frc.robot.Commands.SetLiftHeight;
import frc.robot.RobotConstants.RobotDimensions.Angle;

public class OneEightyCmdG extends CommandGroup {
final double ONE_EIGHTY_SAFETY_HEIGHT = 5;

  public OneEightyCmdG(Angle angle) {
    addSequential(new SetOneEightyAngle(angle));  
  }

  @Override
  protected void execute() {
    if(RobotConstants.RobotDimensions.ONE_EIGHTY_LENGTH * Math.cos(Robot.oneEighty.getAngle()) < Robot.lift.getHeight() + ONE_EIGHTY_SAFETY_HEIGHT){
      addParallel(new SetLiftHeight(RobotConstants.RobotDimensions.ONE_EIGHTY_LENGTH * Math.cos(Robot.oneEighty.getAngle())));;
    }
  }
}

