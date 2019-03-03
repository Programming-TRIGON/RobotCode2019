package frc.robot.DrivingCommands;

import java.util.function.Supplier;

import com.spikes2212.genericsubsystems.drivetrains.commands.DriveArcadeWithPID;
import com.spikes2212.utils.PIDSettings;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.RobotComponents;
import frc.robot.RobotConstants;
import frc.robot.Commands.DistancePIDSource;

public class DriveWithGyro extends CommandGroup {
  /**
   * a drive command group for driving forward with gyro
   */
  double movementPidOutput = 0, lastTimeNotOnTarget, currentSpeedFactor = 0.2;;
  Supplier<Double> movementSupplier = () -> movementPidOutput;
  PIDController movementPidController;
  PIDSettings drivePidSettings;
  public DriveWithGyro(double distance) {
    drivePidSettings = RobotConstants.RobotPIDSettings.DRIVE_SETTINGS;
    RobotComponents.DriveTrain.RIGHT_ENCODER.reset();
    RobotComponents.DriveTrain.LEFT_ENCODER.reset();

    this.movementPidController = new PIDController(drivePidSettings.getKP(),
    this.drivePidSettings.getKI(), 
    drivePidSettings.getKD(), 
    new DistancePIDSource(), (output) -> {
      if (currentSpeedFactor >= 1)
        movementPidOutput = output;
      else {
        currentSpeedFactor += 0.05;
        movementPidOutput = output * currentSpeedFactor;
      }
    });
    
    movementPidController.setAbsoluteTolerance(drivePidSettings.getTolerance());
    movementPidController.setOutputRange(-1, 1);
    movementPidController.setSetpoint(distance);

    double angleSetPoint = RobotComponents.DriveTrain.GYRO.getAngle();
    
    addSequential(new DriveArcadeWithPID(Robot.driveTrain, RobotComponents.DriveTrain.GYRO, 
    () -> angleSetPoint, this.movementSupplier, () -> movementPidController.onTarget(), RobotConstants.RobotPIDSettings.GYRO_DRIVE_SETTINGS, 360, true));    
    this.movementPidController.enable();       
  }
}
