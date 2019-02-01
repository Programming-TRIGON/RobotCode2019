package frc.robot.CommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotConstants;
import frc.robot.Commands.PushCargo;
import frc.robot.Commands.SetAngle;
import frc.robot.Commands.SetLiftHeight;
import frc.robot.RobotConstants.RobotDimensions.Angle;
import frc.robot.RobotConstants.RobotDimensions.Height;

public class CargoScore extends CommandGroup {
  double startingAngle;
  double POWER = 0.5;
  Height height;
  Angle finishingAngle = RobotConstants.RobotDimensions.Angle.kStraight;;
  Height finishingHeight = RobotConstants.RobotDimensions.Height.kLiftBottom; ;

  public CargoScore(double startingAngle, Height height) {
    this.startingAngle = startingAngle;
    this.height = height;
    addSequential(new SetAngle(startingAngle));
    addSequential(new SetLiftHeight(height));
    addSequential(new PushCargo(POWER));

    addSequential(new SetAngle(finishingAngle));
    addSequential(new SetLiftHeight(finishingHeight));
  }
}
