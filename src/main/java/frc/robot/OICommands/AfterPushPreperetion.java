package frc.robot.OICommands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.RobotConstants.LiftHeight;
import frc.robot.RobotConstants.OneEightyAngle;
import frc.robot.CargoFolderCommands.SetCargoFolderState;
import frc.robot.LiftCommands.SetHeightIndex;
import frc.robot.OneEightyCommands.SetOneEightyDesireAngle;

public class AfterPushPreperetion extends CommandGroup {
  /**
   * prepare the robot after pushing the right game piece
   */
  public AfterPushPreperetion() {
    /*addSequential(new SetOneEightyDesireAngle(OneEightyAngle.kStraight));
    addSequential(new SetCargoFolderState(Value.kReverse));
    addSequential(new WaitCommand(0.3));
    addSequential(new SetHeightIndex(LiftHeight.kCargoCollection));*/
  }
}
