package frc.robot.DrivingCommands;

import java.util.function.Supplier;

import com.spikes2212.genericsubsystems.drivetrains.TankDrivetrain;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotStates;

public class CheesyDrive extends Command {
  Supplier<Double> rotentionSupplier, speedSupplier;
  double sensetivity = 1.15;
  TankDrivetrain drivetrain;
  public CheesyDrive(TankDrivetrain drivetrain, Supplier<Double> speedSupplier, Supplier<Double> rotentionSupplier) {
    requires(drivetrain);
    this.rotentionSupplier = rotentionSupplier;
    this.speedSupplier = speedSupplier;
    this.drivetrain = drivetrain;  
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    double y = speedSupplier.get();
    this.drivetrain.arcadeDrive(RobotStates.isDriveInverted() ? this.sensetivity * y
    : -this.sensetivity * y, this.rotentionSupplier.get() * this.sensetivity);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    drivetrain.stop();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
