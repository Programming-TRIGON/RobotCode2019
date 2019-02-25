package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Tests;

public class HigherI extends InstantCommand {
  public HigherI() {
  }

  @Override
  protected void initialize() {
    Tests.i++;
  }

}
