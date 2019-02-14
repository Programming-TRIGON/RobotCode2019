/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Autonomous;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveArcadeWithPID;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveTank;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveTankWithPID;
import com.spikes2212.utils.PIDSettings;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotComponents;
import frc.robot.Commands.SetOneEightyAngle;

public class TestPID extends Command {
  Supplier<Double> KP = ConstantHandler.addConstantDouble("KP", -0.0001);
  Supplier<Double> KI = ConstantHandler.addConstantDouble("KI", 0);
  Supplier<Double> KD = ConstantHandler.addConstantDouble("KD", 0.001);
  Supplier<Double> tolerance = ConstantHandler.addConstantDouble("tolerance", 10);
  Supplier<Double> WAIT_TIME = ConstantHandler.addConstantDouble("WAIT_TIME", 1);
  double movmentPidOutput;
  Supplier<Double> movmentSupplier = () -> movmentPidOutput; 

  PIDController movmentPidController;
  Supplier<Double> Setpoint = ConstantHandler.addConstantDouble("Setpoint", 0);

  PIDSettings pidSettings;
  Command command;

  public TestPID() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override

  protected void initialize() {
    updatePID();
    RobotComponents.DriveTrain.RIGHT_ENCODER.reset();
    RobotComponents.DriveTrain.LEFT_ENCODER.reset();
    RobotComponents.DriveTrain.GYRO.reset();
    // RobotComponents.DriveTrain.GYRO.reset();
    // Command command = new DriveArcadeWithPID(Robot.driveTrain, RobotComponents.DriveTrain.GYRO, 
    // () -> 90.0, () -> 0.0, pidSettings, 360.0, true);
    // Command command = new SetOneEightyAngle(90, pidSettings);
    //Command command = new DriveArcadeWithPID(Robot.driveTrain, );
    this.movmentPidController = new PIDController(0.0025, 0, 0.004, new PIDSource(){
    
      @Override
      public void setPIDSourceType(PIDSourceType pidSource) {        
      }
    
      @Override
      public double pidGet() {
        return (RobotComponents.DriveTrain.LEFT_ENCODER.getDistance());// + RobotComponents.DriveTrain.RIGHT_ENCODER.getDistance())/2;
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
    movmentPidController.setAbsoluteTolerance(5);
    movmentPidController.setOutputRange(-1, 1);
    movmentPidController.setSetpoint(450);
    movmentPidController.enable();
    
    Command command = new DriveArcadeWithPID(Robot.driveTrain, RobotComponents.DriveTrain.GYRO, Setpoint, this.movmentSupplier, pidSettings, 360, true);
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


  public void updatePID(){
    this.pidSettings = new PIDSettings(KP.get(), KI.get(), KD.get(), tolerance.get(), WAIT_TIME.get());
    SmartDashboard.putString("PID setting", "" + KP.get() + KI.get() + KD.get() + tolerance.get() + WAIT_TIME.get());
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {

  }
}

