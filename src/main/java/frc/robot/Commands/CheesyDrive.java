package frc.robot.Commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Robot;
import frc.robot.RobotComponents;
import frc.robot.RobotStates;

public class CheesyDrive extends Command {
  XboxController driverXbox;
  DifferentialDrive differentialDrive;  
  public CheesyDrive(XboxController driverXbox) {
    requires(Robot.driveTrain);
    this.driverXbox=driverXbox;
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

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double y =  Robot.oi.driverXbox.getY(Hand.kLeft);
    this.differentialDrive.curvatureDrive(RobotStates.isDriveInverted() ? 1 * y
    : -1 * y, Robot.oi.driverXbox.getX(Hand.kLeft), Math.abs(y) <= 0.50);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    differentialDrive.stopMotor();
    differentialDrive.close();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
