package frc.robot.OICommands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotStates;
import frc.robot.DrivingCommands.ReflectorDriveChooser;
import frc.robot.LiftCommands.SetHeightIndex;
import frc.robot.RobotConstants.LiftHeight;

public class ReflectorDrive extends CommandGroup {
  /**
   * Add your docs here.
   */
  LiftHeight heightToSet;
  public ReflectorDrive() {
    addSequential(new SetHeightIndex(() -> this.heightToSet));
    addSequential(new ReflectorDriveChooser());
  }

  @Override
  protected void initialize(){
    if(RobotStates.getLiftHeight().equals(LiftHeight.kCargoCollection)
      || RobotStates.getLiftHeight().equals(LiftHeight.kLiftBottomHatch)){
      RobotStates.setLastHeight(RobotStates.getLiftHeight());
      this.heightToSet = LiftHeight.kRocketMiddleCargo;
    } else {
      this.heightToSet = RobotStates.getLiftHeight();
      RobotStates.setLastHeight(this.heightToSet);
    }
  }
}
