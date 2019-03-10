package frc.robot.OneEightyCommands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotConstants.OneEightyAngle;

public class ReachOneEightyAngle extends Command {
  private double angle, power;

  public ReachOneEightyAngle(double angle) {
    requires(Robot.oneEighty);
    this.angle = angle;
  }
  public ReachOneEightyAngle(Supplier<OneEightyAngle> desiredAngle) {
    this.angle = desiredAngle.get().key;
  }

  public ReachOneEightyAngle(OneEightyAngle angle) {
    requires(Robot.oneEighty);
    this.angle = angle.key;
  }

  @Override
  protected void initialize() {
    if (this.angle > Robot.oneEighty.getAngle())
      power = 0.75;
    else
    power = -0.75;
  }

  @Override
  protected void execute() {
    Robot.oneEighty.setOneEighty(this.power);
  }

  @Override
  protected boolean isFinished() {
    double currentAngle = Robot.oneEighty.getAngle();
    return ((currentAngle > angle - 7) && (currentAngle < angle + 7)); //|| RobotStates.isOneEightyOverride();
  }

  @Override
  protected void end() {
    Robot.oneEighty.setOneEighty(0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
