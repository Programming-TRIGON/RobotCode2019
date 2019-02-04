package frc.robot.CommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotConstants;
import frc.robot.Commands.PushCargo;
import frc.robot.Commands.SetOneEightyAngle;
import frc.robot.Commands.SetLiftHeight;
import frc.robot.RobotConstants.OneEightyAngle;
import frc.robot.Subsystems.Lift;
import frc.robot.RobotConstants.LiftHeight;

public class CargoScore extends CommandGroup {
  double startingAngle, POWER;
  LiftHeight height;
  OneEightyAngle finishingAngle;
  LiftHeight finishingHeight;

  public CargoScore(double startingAngle, LiftHeight height) {
    this.startingAngle = startingAngle;
    this.height = height;
    this.finishingAngle = RobotConstants.OneEightyAngle.kStraight;
    this.finishingHeight = RobotConstants.LiftHeight.kLiftBottom; 


    /** one eighty turns to the desired angle */
    addSequential(new SetOneEightyAngle(startingAngle));
    /** setting the lift to the desired height. */
    addSequential(new SetLiftHeight(height));
    /** ejecting the cargo from the system. */
    addSequential(new PushCargo(RobotConstants.heightToCargoPower.get(height)));
    /**
     * setting the one eighty to thedesied angle for use after this command group
     */
    addSequential(new SetOneEightyAngle(finishingAngle));
    /** returning the lift to the bottom */
    addSequential(new SetLiftHeight(finishingHeight));
  }
}
