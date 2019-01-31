/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Commands;

import org.hamcrest.core.Is;
import org.junit.rules.Timeout;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class PushCargo extends Command {
  double power;
  //defines power
  public PushCargo (double power, double timeout) {
    //requires cargoCollector SS
    requires(Robot.cargoCollector);
    this.power = power;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    setTimeout(1);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //the cargo holder motors turn on 
    Robot.cargoCollector.setHolderMotors(power);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    //the command is finished when the ball is being held
    return isTimedOut();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
