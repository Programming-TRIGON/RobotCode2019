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
import frc.robot.Commands.SetLiftHeight;
import frc.robot.RobotConstants.RobotDimensions.Angle;

public class OneEightyCmdG extends CommandGroup {
double angle;
  public OneEightyCmdG(Angle angle) {
    this.angle = angle.key;
    if (this.angle - 1 < Robot.oneEighty.getAngle() && Robot.oneEighty.getAngle() > this.angle + 1){
    }
    else {
      if(Robot.lift.getHeight() < RobotConstants.RobotDimensions.Height.kOneEightySafety.key){
        addSequential(new SetLiftHeight(RobotConstants.RobotDimensions.Height.kOneEightySafety));
      }
    }
    // Add Commands here:
    // e.g. addSequential(new Command1());
    // addSequential(new Command2());
    // these will run in order.

    // To run multiple commands at the same time,
    // use addParallel()
    // e.g. addParallel(new Command1());
    // addSequential(new Command2());
    // Command1 and Command2 will run in parallel.

    // A command group will require all of the subsystems that each member
    // would require.
    // e.g. if Command1 requires chassis, and Command2 requires arm,
    // a CommandGroup containing them would require both the chassis and the
    // arm.
  }
}
