/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.CargoCollectorCommands;

import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotStates;

public class KeepCargo extends Command {
  public KeepCargo() {
    requires(Robot.cargoCollector);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    if(!RobotStates.isHasCargo())
      Robot.cargoCollector.setHolderMotors(-0.5);
    else
      Robot.cargoCollector.setHolderMotors(0);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.cargoCollector.setCollectorMotor(0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
