package frc.robot.OICommands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.CargoFolderCommands.SetCargoFolderState;
import frc.robot.HatchCollectorCommands.SetHatchCollectorState;
import frc.robot.HatchHolderCommands.SetHatchLock;
import frc.robot.LiftCommands.ResetLift;
import frc.robot.OneEightyCommands.ReachOneEightyAngle;
import frc.robot.RobotConstants.OneEightyAngle;
 /**
   * retracts all systems for deffence 
   */
public class DefenceMode extends CommandGroup {
 
  public DefenceMode() {
    addParallel(new SetCargoFolderState(Value.kReverse));
    addSequential(new ReachOneEightyAngle(OneEightyAngle.kStraight));    
    addSequential(new Push());
    addSequential(new WaitCommand(0.3));
    addSequential(new ResetLift(), 3);
    addParallel(new SetCargoFolderState(Value.kReverse));
    addParallel(new SetHatchCollectorState(Value.kReverse));
    addSequential(new SetHatchLock(Value.kForward));
  }
}
