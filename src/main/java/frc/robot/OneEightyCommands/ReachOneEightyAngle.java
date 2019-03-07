package frc.robot.OneEightyCommands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotStates;
import frc.robot.RobotConstants.OneEightyAngle;

public class ReachOneEightyAngle extends Command {
  private double angle, power;
  private Supplier<OneEightyAngle> angleSupplier;

  public ReachOneEightyAngle(double angle) {
    requires(Robot.oneEighty);
    this.angle = angle;
  }
  public ReachOneEightyAngle(Supplier<OneEightyAngle> desiredAngle) {
    this.angleSupplier = desiredAngle;
  }

  public ReachOneEightyAngle(OneEightyAngle angle) {
    requires(Robot.oneEighty);
    this.angle = angle.key;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    if(this.angleSupplier!=null)
      this.angle = angleSupplier.get().key;
    if (this.angle > Robot.oneEighty.getAngle())
      power = 0.5;
    else
    power = -0.5;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.oneEighty.setOneEighty(this.power);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    double currentAngle = Robot.oneEighty.getAngle();
    return ((currentAngle > angle - 7) && (currentAngle < angle + 7)); //|| RobotStates.isOneEightyOverride();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.oneEighty.setOneEighty(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
