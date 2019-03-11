package frc.robot.OneEightyCommands;

import java.util.function.Supplier;
import com.spikes2212.utils.PIDSettings;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotConstants.OneEightyAngle;

public class OneEightyDefaultCommand extends CommandGroup {

  public OneEightyDefaultCommand(OneEightyAngle angle, PIDSettings pIDSettings, Supplier<Double> liftHeight) {
    addSequential(new ReachOneEightyAngle(angle, liftHeight));
    addSequential(new StabilizeOneEightyAngle(angle, pIDSettings, liftHeight));
    addSequential(new SetOneEightyOverride());
  }

  public OneEightyDefaultCommand(OneEightyAngle angle, Supplier<Double> liftHeight) {
    addSequential(new ReachOneEightyAngle(angle, liftHeight));
    addSequential(new StabilizeOneEightyAngle(angle, liftHeight));
    addSequential(new SetOneEightyOverride());
  }

  public OneEightyDefaultCommand(Supplier<OneEightyAngle> angleSupplier, Supplier<Double> liftHeight) {
    addSequential(new ReachOneEightyAngle(angleSupplier, liftHeight));
    addSequential(new StabilizeOneEightyAngle(angleSupplier, liftHeight));
    addSequential(new SetOneEightyOverride());
  }
}
