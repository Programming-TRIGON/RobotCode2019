/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 * Add your docs here.
 */
public class CancelCommand extends InstantCommand {
  /**
   * Add your docs here.
   */

  Supplier<Command> command;
  public CancelCommand(Command c) {
    super();
    command = () -> c;
  }

  public CancelCommand(Supplier<Command> c) {
    command = c;
}

// Called once when the command executes
  @Override
  protected void initialize() {
    command.get().cancel();
  }

}
