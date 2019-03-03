package frc.robot.LiftCommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ResetLift extends Command {
  double LIFT_POWER = 0.5;
  public ResetLift() {
    requires(Robot.lift);
  }

  @Override
  protected void initialize() {
   Robot.lift.setMotorSpeed(LIFT_POWER);
  }

  @Override
  protected void execute() {
  }

  @Override
  protected boolean isFinished() {
    return Robot.lift.isAtBottom();
  }

  @Override
  protected void end() {
    Robot.lift.setMotorSpeed(0);

  }

  @Override
  protected void interrupted() {
    end();
  }
}
