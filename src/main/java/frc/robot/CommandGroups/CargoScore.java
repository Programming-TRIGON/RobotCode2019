package frc.robot.CommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotConstants;
import frc.robot.Commands.PushCargo;
import frc.robot.Commands.SetAngle;
import frc.robot.Commands.SetLiftHeight;

public class CargoScore extends CommandGroup {
  double startingAngle, finishingAngle, height, power;

  public CargoScore(double startingAngle, double finishingAngle, double height, double power) {
    this.startingAngle = startingAngle;
    this.height = height;
    this.power = 0.5;
    this.finishingAngle = finishingAngle;
    /** one eighty turns to the desired angle */
    addSequential(new SetAngle(startingAngle));
    /** setting the lift to the desired height. */
    addSequential(new SetLiftHeight(height));
    /** ejecting the cargo from the system. */
    addSequential(new PushCargo(power));
    /**
     * setting the one eighty to thedesied angle for use after this command group
     */
    addSequential(new SetAngle(finishingAngle));
    /** returning the lift to the bottom */
    addSequential(new SetLiftHeight(RobotConstants.RobotDimensions.BOTTOM_LIFT_HEIGHT));
  }
}
