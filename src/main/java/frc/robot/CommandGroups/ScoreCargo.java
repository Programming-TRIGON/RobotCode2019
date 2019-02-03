package frc.robot.CommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotConstants;
import frc.robot.Commands.PushCargo;
import frc.robot.Commands.SetOneEightyAngle;
import frc.robot.Commands.SetLiftHeight;
import frc.robot.RobotConstants.Angle;
import frc.robot.RobotConstants.Height;

public class ScoreCargo extends CommandGroup {
  double startingAngle;
  double POWER = 0.5;
  Height height;
  Angle finishingAngle = RobotConstants.Angle.kStraight;;
  Height finishingHeight = RobotConstants.Height.kLiftBottom; ;

  public ScoreCargo(double startingAngle, Height height) {
    this.startingAngle = startingAngle;
    this.height = height;
    addSequential(new SetOneEightyAngle(startingAngle));
    addSequential(new SetLiftHeight(height));
    addSequential(new PushCargo(POWER));

    addSequential(new SetOneEightyAngle(finishingAngle));
    addSequential(new SetLiftHeight(finishingHeight));
  }
}
