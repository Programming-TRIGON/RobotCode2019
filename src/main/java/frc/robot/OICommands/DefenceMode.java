package frc.robot.OICommands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.RobotConstants.OneEightyAngle;
import frc.robot.RobotStates;
import frc.robot.CargoCollectorCommands.PushCargo;
import frc.robot.CargoFolderCommands.SetCargoFolderState;
import frc.robot.HatchCollectorCommands.SetHatchCollectorState;
import frc.robot.HatchHolderCommands.EjectHatch;
import frc.robot.LiftCommands.ResetLift;
import frc.robot.OneEightyCommands.SetOneEightyAngle;
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
