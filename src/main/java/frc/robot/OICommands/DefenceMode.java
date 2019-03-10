package frc.robot.OICommands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.CargoFolderCommands.SetCargoFolderState;
import frc.robot.HatchCollectorCommands.SetHatchCollectorState;
import frc.robot.HatchHolderCommands.SetHatchLock;
 /**
   * retracts all systems 
   */
public class DefenceMode extends CommandGroup {
 
  public DefenceMode() {
    addSequential(new SetCargoFolderState(Value.kReverse));
    addSequential(new SetHatchCollectorState(Value.kReverse));   
    addSequential(new SetHatchLock(Value.kReverse));

    /*addParallel(new SetCargoFolderState(Value.kForward));
    addSequential(new ReachOneEightyAngle(OneEightyAngle.kStraight));    
    if(RobotStates.isHasCargo())
      addSequential(new PushCargo());
    else
      addSequential(new EjectHatch());
    addSequential(new ResetLift(), 3);
    addParallel(new SetCargoFolderState(Value.kReverse));
    addSequential(new SetHatchCollectorState(Value.kReverse));*/
  }
}
