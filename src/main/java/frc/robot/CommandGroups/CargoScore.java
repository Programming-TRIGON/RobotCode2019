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
import frc.robot.Commands.PushCargo;
import frc.robot.Commands.SetAngle;
import frc.robot.Commands.SetLiftHeight;

public class CargoScore extends CommandGroup {
  double startingAngle, finishingAngle, height, power;

  public CargoScore(double startingAngle, double finishingAngle, double height, double power) {
    this.startingAngle = startingAngle;
    this.height = height;
    this.power = power;
    /**
     * checks if one eighty is at the correct highet if it isn't it moves the lift
     * to a height at which the one eighty can turn then we turn it.
     */
    if (startingAngle - 1 < Robot.oneEighty.getAngle() && Robot.oneEighty.getAngle() < startingAngle + 1) {
      addSequential(new SetLiftHeight(RobotConstants.RobotDimensions.MINUMAN_ONE_EIGHTY_LIFT_HEIGHT));
      addSequential(new SetAngle(startingAngle));
    }
   /** setting the lift to the desired height. */
    addSequential(new SetLiftHeight(height));
    /**ejecting the cargo from the system. */
    addSequential(new PushCargo(power));
    /**setting the one eighty to thedesied angle for use after this command group */
    addSequential(new SetAngle(finishingAngle));
    /** returning the lift to the bottom */
    addSequential(new SetLiftHeight(RobotConstants.RobotDimensions.BOTTOM_LIFT_HEIGHT));
  }
}
