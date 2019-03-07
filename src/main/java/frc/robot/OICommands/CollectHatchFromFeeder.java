package frc.robot.OICommands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.RobotConstants.LiftHeight;
import frc.robot.RobotConstants.OneEightyAngle;
import frc.robot.Subsystems.CargoFolder;
import frc.robot.RobotStates;
import frc.robot.CargoFolderCommands.SetCargoFolderState;
import frc.robot.HatchHolderCommands.SetHatchLock;
import frc.robot.LiftCommands.SetLiftHeight;
import frc.robot.OneEightyCommands.SetOneEightyAngle;

/**
 * Sets the 180 and lift to feeder height and direction and opens the hatch collector
 */
public class CollectHatchFromFeeder extends CommandGroup {

    public CollectHatchFromFeeder() {
        addParallel(new SetOneEightyAngle(OneEightyAngle.kStraight));
        addSequential(new SetLiftHeight(LiftHeight.kLiftBottomHatch));
        
        /*addSequential(new SetHatchLock(Value.kReverse));
        addSequential(new SetCargoFolderState(Value.kReverse));
        addParallel(new SetOneEightyAngle(OneEightyAngle.kStraight));
        addSequential(new WaitCommand(0.5));
        addParallel(new SetLiftHeight(LiftHeight.kLiftBottomHatch));*/
    }
}
