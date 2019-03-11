package frc.robot.OneEightyCommands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotStates;
import frc.robot.RobotConstants.LiftHeight;
import frc.robot.RobotConstants.OneEightyAngle;

public class ReachOneEightyAngle extends Command {
  private double power;
  Supplier<Double> angle, liftHeight;


  public ReachOneEightyAngle(double angle, Supplier<Double> liftHeight) {
    requires(Robot.oneEighty);
    this.angle = () -> angle;
    this.liftHeight = liftHeight; 
  }
  public ReachOneEightyAngle(Supplier<OneEightyAngle> desiredAngle, Supplier<Double> liftHeight) {
    requires(Robot.oneEighty);
    Supplier<Double> doubleSupplier = () -> {return desiredAngle.get().key;};
    this.angle = doubleSupplier;
    this.liftHeight = liftHeight; 
  
  }

  public ReachOneEightyAngle(OneEightyAngle angle, Supplier<Double> liftHeight) {
    requires(Robot.oneEighty);
    this.angle = () -> angle.key;
    this.liftHeight = liftHeight; 
  
  }


  @Override
  protected void initialize() {
    if (this.angle.get() > Robot.oneEighty.getAngle())
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
    return  RobotStates.isOneEightyOverride() || 
    liftHeight.get() <= LiftHeight.kOneEightySafety.key ||
    ((currentAngle > angle.get() - 7) && (currentAngle < angle.get() + 7));
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
