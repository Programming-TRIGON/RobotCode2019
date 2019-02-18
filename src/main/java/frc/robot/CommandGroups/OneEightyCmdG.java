package frc.robot.CommandGroups;

import java.util.function.Supplier;

import com.spikes2212.utils.PIDSettings;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.RobotConstants;
import frc.robot.Commands.SetLiftHeight;
import frc.robot.RobotConstants.OneEightyAngle;;

public class OneEightyCmdG extends CommandGroup {
  final double ONE_EIGHTY_SAFETY_HEIGHT = 5;
  Supplier<Double> liftSetpointSupplier;

  public OneEightyCmdG(OneEightyAngle angle) {
    liftSetpointSupplier = new Supplier<Double>() {
      @Override
      public Double get() {
        // lengthOfArm*cos(angle) = the height from the robot
        if (RobotConstants.RobotDimensions.ONE_EIGHTY_LENGTH
            * Math.cos(Math.toRadians(Robot.oneEighty.getAngle())) < Robot.lift.getHeight()
                + ONE_EIGHTY_SAFETY_HEIGHT) {
          return RobotConstants.RobotDimensions.ONE_EIGHTY_LENGTH * Math.cos(Math.toRadians(Robot.oneEighty.getAngle()))
              + ONE_EIGHTY_SAFETY_HEIGHT;
        }
        return Robot.lift.getHeight();
      }
    };
    addSequential(new SetOneEightyAngle(angle));
    //TODO calibrate pid settings
    addParallel(new SetLiftHeight(liftSetpointSupplier.get(),new PIDSettings(0.2, 0, 0, 0.1, 0)));
  }
}
