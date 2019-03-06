package frc.robot.OneEightyCommands;

import java.util.function.Supplier;

import com.spikes2212.utils.PIDSettings;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotConstants.OneEightyAngle;
import frc.robot.RobotStates;

public class SetOneEightyAngle extends CommandGroup {

  public SetOneEightyAngle(OneEightyAngle angle, PIDSettings pIDSettings) {
    addSequential(new ReachOneEightyAngle(angle));
    addSequential(new StabilizeOneEightyAngle(angle, pIDSettings));
    addSequential(new SetOneEightyOverride());
  }

  public SetOneEightyAngle(OneEightyAngle angle) {
    addSequential(new SetOneEightyDesireAngle(angle));
    addSequential(new ReachOneEightyAngle(angle));
    addSequential(new StabilizeOneEightyAngle(angle));
    addSequential(new SetOneEightyOverride());
  }

  public SetOneEightyAngle(Supplier<OneEightyAngle> angleSupplier) {
    this(angleSupplier.get());
  }
}
