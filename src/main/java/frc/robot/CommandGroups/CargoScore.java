package frc.robot.CommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotConstants;
import frc.robot.Commands.PushCargo;
import frc.robot.Commands.SetAngle;
import frc.robot.Commands.SetLiftHeight;
import frc.robot.RobotConstants.RobotDimensions.Angle;
import frc.robot.RobotConstants.RobotDimensions.Height;

public class CargoScore extends CommandGroup {
  double startingAngle, POWER;
  Height height;
  Angle finishingAngle;
  Height finishingHeight;

  public CargoScore(double startingAngle, Height height) {
    this.startingAngle = startingAngle;
    this.height = height;
    this.POWER = 0.5;
    this.finishingAngle = RobotConstants.RobotDimensions.Angle.kStraight;
    this.finishingHeight = RobotConstants.RobotDimensions.Height.kLiftBottom; 
    /** one eighty turns to the desired angle */
    addSequential(new SetAngle(startingAngle));
    /** setting the lift to the desired height. */
    addSequential(new SetLiftHeight(height));
    /** ejecting the cargo from the system. */
    addSequential(new PushCargo(POWER));
    /**
     * setting the one eighty to thedesied angle for use after this command group
     */
    addSequential(new SetAngle(finishingAngle));
    /** returning the lift to the bottom */
    addSequential(new SetLiftHeight(finishingHeight));
  }
}
