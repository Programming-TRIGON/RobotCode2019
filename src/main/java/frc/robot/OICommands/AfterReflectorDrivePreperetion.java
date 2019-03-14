package frc.robot.OICommands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.RobotStates;
import frc.robot.Commands.CancelCommand;
import frc.robot.LiftCommands.SetHeightIndex;
import frc.robot.RobotConstants.LiftHeight;

public class AfterReflectorDrivePreperetion extends CommandGroup {
  /**
   * cancel ref drive and lift goes to its last height before going up for image proccesing 
   */
  LiftHeight heightToSet;
  public AfterReflectorDrivePreperetion() {
    addSequential(new CancelCommand(() -> Robot.driveTrain.getCurrentCommand()));
    //addSequential(new SetHeightIndex(() -> heightToSet));
  }

  @Override
  protected void initialize(){
    this.heightToSet = RobotStates.getLastHeight();
  }
}
