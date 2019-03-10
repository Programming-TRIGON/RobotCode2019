package frc.robot.OneEightyCommands;

import java.util.function.Supplier;

import com.spikes2212.utils.PIDSettings;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotConstants.OneEightyAngle;
import frc.robot.RobotStates;

public class OneEightyDefaultCommand extends CommandGroup {

  public OneEightyDefaultCommand(OneEightyAngle angle, PIDSettings pIDSettings) {
    addSequential(new ReachOneEightyAngle(angle));
    addSequential(new StabilizeOneEightyAngle(angle, pIDSettings));
    addSequential(new SetOneEightyOverride());
  }

  public OneEightyDefaultCommand(OneEightyAngle angle) {
    addSequential(new ReachOneEightyAngle(angle));
    addSequential(new StabilizeOneEightyAngle(angle));
    addSequential(new SetOneEightyOverride());
  }

  public OneEightyDefaultCommand(Supplier<OneEightyAngle> angleSupplier) {
    addSequential(new ReachOneEightyAngle(angleSupplier));
    addSequential(new StabilizeOneEightyAngle(angleSupplier));
    addSequential(new SetOneEightyOverride());
  }
}
