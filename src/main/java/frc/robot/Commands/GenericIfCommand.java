/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.ConditionalCommand;

public class GenericIfCommand extends ConditionalCommand {
  Supplier<Boolean> condition;

  public GenericIfCommand(Command c1, Command c2, Supplier<Boolean> condition) {
    super(c1, c2);
    this.condition = condition;
  }

  public GenericIfCommand(Command c1, Supplier<Boolean> condition){
    this(c1, null, condition);
  }

  @Override
  protected boolean condition() {
    return this.condition.get();
  }
}
