package frc.robot.CommandGroups;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.RobotConstants.OneEightyAngle;
import frc.robot.RobotStates;
import frc.robot.Commands.PushCargo;
import frc.robot.Commands.ResetLift;
import frc.robot.Commands.SetCargoFolderState;
import frc.robot.Commands.SetHatchCollectorState;
 /**
   * retracts all systems 
   */
public class DefenceMode extends CommandGroup {
 
  public DefenceMode() {
    addParallel(new SetOneEightyAngle(OneEightyAngle.kStraight));    
    addSequential(new WaitCommand(0.3));
    if(RobotStates.isHasCargo())
      addSequential(new PushCargo());
    else
      addSequential(new EjectHatch());
    addSequential(new ResetLift());
    addSequential(new WaitCommand(0.3));
    addParallel(new SetCargoFolderState(Value.kReverse));
    addSequential(new SetHatchCollectorState(Value.kReverse));
  }
}
