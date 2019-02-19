/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.CommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.Autonomous.TestPID;
import frc.robot.Commands.CollectCargo;
import frc.robot.Commands.PushCargo;
import frc.robot.Commands.SetLiftHeight;
import frc.robot.RobotConstants.LiftHeight;
import frc.robot.RobotConstants.PushCargoPower;

public class Tizer extends CommandGroup {
  /**
   * Add your docs here.
   */
  public Tizer() {
    addSequential(new CollectCargo(0.9, 0.75));
    addParallel(new TestPID());
    addSequential(new WaitCommand(0.85));
    addParallel(new SetOneEightyAngle(232));
    addSequential(new WaitCommand(2));
    addSequential(new PushCargo(PushCargoPower.kTopRocket));
    // addSequential(new Command2());
    // these will run in order.

    // To run multiple commands at the same time,
    // use addParallel()
    // e.g. addParallel(new Command1());
    // addSequential(new Command2());
    // Command1 and Command2 will run in parallel.

    // A command group will require all of the subsystems that each member
    // would require.
    // e.g. if Command1 requires chassis, and Command2 requires arm,
    // a CommandGroup containing them would require both the chassis and the
    // arm.
  }
}
