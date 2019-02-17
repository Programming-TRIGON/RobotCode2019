/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.CommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Commands.ReachOneEightyAngle;
import frc.robot.Commands.StabilizeOneEightyAngle;
import frc.robot.RobotConstants.OneEightyAngle;

public class SetOneEightyAngle extends CommandGroup {

  public SetOneEightyAngle(double angle) {
    addSequential(new ReachOneEightyAngle(angle));
    addSequential(new StabilizeOneEightyAngle(angle));
  }
  public SetOneEightyAngle(OneEightyAngle angle) {
    addSequential(new ReachOneEightyAngle(angle.key));
    addSequential(new StabilizeOneEightyAngle(angle));
  }
}
