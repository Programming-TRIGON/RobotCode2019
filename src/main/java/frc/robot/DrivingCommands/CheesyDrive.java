package frc.robot.DrivingCommands;

import java.util.function.Supplier;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Robot;
import frc.robot.RobotComponents;
import frc.robot.RobotStates;

public class CheesyDrive extends Command {
  Supplier<Double> rotentionSupplier, speedSupplier;
  DifferentialDrive differentialDrive;
  double sensetivity = 1.25;  
  public CheesyDrive(Supplier<Double> speedSupplier, Supplier<Double> rotentionSupplier) {
    requires(Robot.driveTrain);
    this.rotentionSupplier = rotentionSupplier;
    this.speedSupplier = speedSupplier;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    //right controller speed
    SpeedController rightController = new SpeedController(){
    
      @Override
      public void pidWrite(double output) {
        RobotComponents.DriveTrain.REAR_RIGHT_M.set(ControlMode.PercentOutput, output);
      }
    
      @Override
      public void stopMotor() {
        RobotComponents.DriveTrain.REAR_RIGHT_M.set(ControlMode.PercentOutput, 0);
      }
    
      @Override
      public void setInverted(boolean isInverted) {
        RobotStates.setDriveInverted(isInverted);
      }
    
      @Override
      public void set(double speed) {
        RobotComponents.DriveTrain.REAR_RIGHT_M.set(ControlMode.PercentOutput, speed);
      }
    
      @Override
      public boolean getInverted() {
        return RobotStates.isDriveInverted();
      }
    
      @Override
      public double get() {
        return RobotComponents.DriveTrain.REAR_RIGHT_M.getMotorOutputPercent();
      }
    
      @Override
      public void disable() {
        RobotComponents.DriveTrain.REAR_RIGHT_M.set(ControlMode.PercentOutput, 0);
      }
    }; 
    
    //left controller speed
    SpeedController leftController = new SpeedController(){
    
      @Override
      public void pidWrite(double output) {
        RobotComponents.DriveTrain.REAR_LEFT_M.set(ControlMode.PercentOutput, output);
      }
    
      @Override
      public void stopMotor() {
        RobotComponents.DriveTrain.REAR_LEFT_M.set(ControlMode.PercentOutput, 0);
      }
    
      @Override
      public void setInverted(boolean isInverted) {
        RobotStates.setDriveInverted(isInverted);
      }
    
      @Override
      public void set(double speed) {
        RobotComponents.DriveTrain.REAR_LEFT_M.set(ControlMode.PercentOutput, speed);
      }
    
      @Override
      public boolean getInverted() {
        return RobotStates.isDriveInverted();
      }
    
      @Override
      public double get() {
        return RobotComponents.DriveTrain.REAR_LEFT_M.getMotorOutputPercent();
      }
    
      @Override
      public void disable() {
        RobotComponents.DriveTrain.REAR_LEFT_M.set(ControlMode.PercentOutput, 0);
      }
    }; 
    
    this.differentialDrive = new DifferentialDrive(leftController, rightController);
  }

  @Override
  protected void execute() {
    double y = speedSupplier.get();
    this.differentialDrive.curvatureDrive(RobotStates.isDriveInverted() ? this.sensetivity * y
    : -this.sensetivity * y, this.rotentionSupplier.get() * this.sensetivity, Math.abs(y) <= 0.50);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    differentialDrive.stopMotor();
    differentialDrive.close();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
