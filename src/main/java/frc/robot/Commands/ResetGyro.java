package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotComponents;

public class ResetGyro extends Command {
  public ResetGyro() {
    setRunWhenDisabled(true);
  }

  @Override
  protected void initialize() {
    RobotComponents.DriveTrain.GYRO.calibrate();
    RobotComponents.DriveTrain.GYRO.reset();
  }

  @Override
  protected void execute() {
  }

  @Override
  protected boolean isFinished() {
    return true;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
