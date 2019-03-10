/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.CargoFolderCommands.SetCargoFolderState;
import frc.robot.LiftCommands.ReachLiftHeight;
import frc.robot.OneEightyCommands.OneEightyDefaultCommand;
import frc.robot.RobotConstants.LiftHeight;
import frc.robot.RobotConstants.OneEightyAngle;

public class ReachCargoShipHeight extends CommandGroup {
  /**
   * this shit cmdG made in distric #1
   */
  public ReachCargoShipHeight() {
    addSequential(new SetCargoFolderState(Value.kReverse));
    addSequential(new WaitCommand(0.4));
    addParallel(new ReachLiftHeight(LiftHeight.kRocketMiddleCargo));
    addParallel(new OneEightyDefaultCommand(OneEightyAngle.kCargoShip));
    addSequential(new WaitCommand(0.45));
    addSequential(new SetCargoFolderState(Value.kForward));
  }
}
