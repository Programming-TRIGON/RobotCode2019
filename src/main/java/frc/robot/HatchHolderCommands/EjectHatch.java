package frc.robot.HatchHolderCommands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
/**
 * Cmd group to eject hatch
 */
public class EjectHatch extends CommandGroup{
    
    Value unlock = Value.kReverse;
    Value extend = Value.kForward;
    Value retract = Value.kReverse;

    public EjectHatch(){
        addSequential(new SetHatchLock(unlock));
        addSequential(new WaitCommand(0.05));
        addSequential(new SetHatchEject(extend));
        addSequential(new WaitCommand(0.3));
        addSequential(new SetHatchEject(retract));
    }
}
