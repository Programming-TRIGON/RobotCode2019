/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Commands;

import com.spikes2212.utils.PIDSettings;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotComponents;

public class ArcDrive extends Command {
  private final double leftSpeed;
  private final double rightSpeed;
  private final double axis = 0.553;
  private final double arcLength;
  private PIDController leftController;
  private PIDController rightController;
  private PIDSettings pidSettings;
  private Encoder leftEncoder;
  private Encoder rightEncoder;
  public ArcDrive(double radius, double angle, double speed, boolean isLeft, PIDSettings pidSettings) {
    this.pidSettings = pidSettings;
    leftEncoder = RobotComponents.DriveTrain.LEFT_ENCODER;
    rightEncoder = RobotComponents.DriveTrain.RIGHT_ENCODER;
    // calculations for speeds and time were done by Hilel Amiel and Eliya Fishman
    if (!isLeft) {
      leftSpeed = (radius + axis) * speed / (radius + axis / 2);
      rightSpeed = radius * speed / (radius + axis / 2);
    } else {
      leftSpeed = radius * speed / (radius + axis / 2);
      rightSpeed = (radius + axis) * speed / (radius + axis / 2);
    }
    System.out.println("left speed - " + leftSpeed);
    System.out.println("right speed - " + rightSpeed);
    arcLength = angle * 2 * Math.PI * radius / 360;
    System.out.println("arcLength - " + arcLength*100);
    setTimeout(angle * 2 * Math.PI * radius / (360 * speed));
    requires(Robot.driveTrain);
    
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    //sets the encoder to return rate in pid and reset them.
/*     leftEncoder.setPIDSourceType(PIDSourceType.kRate);
    rightEncoder.setPIDSourceType(PIDSourceType.kRate); */
    leftEncoder.reset();
    rightEncoder.reset();
    //creates pidControllers for rate and enables them;
/*     leftController = new PIDController(pidSettings.getKP(), pidSettings.getKI(), pidSettings.getKD(),
        leftEncoder, (output) -> Robot.driveTrain.setLeft(output));
    leftController.setAbsoluteTolerance(pidSettings.getTolerance());
    leftController.setSetpoint(leftSpeed);
    rightController = new PIDController(pidSettings.getKP(), pidSettings.getKI(), pidSettings.getKD(),
        rightEncoder, (output) -> Robot.driveTrain.setRight(output));
    rightController.setAbsoluteTolerance(pidSettings.getTolerance());
    rightController.setSetpoint(rightSpeed);
    leftController.enable();
    rightController.enable(); */
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.driveTrain.setLeft(leftSpeed);
    Robot.driveTrain.setRight(rightSpeed);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    //checks if the drivetrain moved enough
    return (leftEncoder.getDistance()
        + rightEncoder.getDistance()) / 2 <= arcLength*100 + 10
        && (leftEncoder.getDistance()
            + rightEncoder.getDistance()) / 2 >= arcLength*100 - 10/* ||isTimedOut() */;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    //Disables the controllers and return the encoders to the way they were before.
   /*  leftController.disable();
    rightController.disable();
    leftController.close();
    rightController.close();
    leftEncoder.setPIDSourceType(PIDSourceType.kDisplacement);
    rightEncoder.setPIDSourceType(PIDSourceType.kDisplacement); */
    Robot.driveTrain.tankDrive(0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
