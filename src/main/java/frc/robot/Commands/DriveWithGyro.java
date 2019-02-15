/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.Robot;
import frc.robot.RobotComponents;
import frc.robot.RobotConstants;

public class DriveWithGyro extends Command {
  double movmentPidOutput, distance;
  Supplier<Double> movmentSupplier = () -> movmentPidOutput; 
  PIDController movmentPidController;
  public DriveWithGyro(double distance) {
    this.distance=distance;
    
    this.movmentPidController = new PIDController(RobotConstants.RobotPIDSettings.DRIVE_SETTINGS.getKP(),
    RobotConstants.RobotPIDSettings.DRIVE_SETTINGS.getKI(), 
    RobotConstants.RobotPIDSettings.DRIVE_SETTINGS.getKD(), 
    new PIDSource(){
    
      @Override
      public void setPIDSourceType(PIDSourceType pidSource) {
      }
    
      @Override
      public double pidGet() {
        return (RobotComponents.DriveTrain.LEFT_ENCODER.getDistance() + RobotComponents.DriveTrain.RIGHT_ENCODER.getDistance())/2;
      }
    
      @Override
      public PIDSourceType getPIDSourceType() {
        return PIDSourceType.kDisplacement;
      }
    },
    new PIDOutput(){
      @Override
      public void pidWrite(double output) {
        movmentPidOutput = output;  
      }
    });
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    RobotComponents.DriveTrain.RIGHT_ENCODER.reset();
    RobotComponents.DriveTrain.LEFT_ENCODER.reset();
    RobotComponents.DriveTrain.GYRO.reset();
    movmentPidController.setAbsoluteTolerance(5);
    movmentPidController.setOutputRange(-1, 1);
    movmentPidController.setSetpoint(this.distance);
    movmentPidController.enable();

    Command command = new DriveArcadeWithPID(Robot.driveTrain, RobotComponents.DriveTrain.GYRO, 
    () -> RobotComponents.DriveTrain.GYRO.getAngle(), 
    this.movmentSupplier, RobotConstants.RobotPIDSettings.GYRO_DRIVE_SETTINGS, 360, true);
    Scheduler.getInstance().add(command);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return this.movmentPidController.onTarget();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    this.movmentPidController.disable();
    this.movmentPidController.close();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
