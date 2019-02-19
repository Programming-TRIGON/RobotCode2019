/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.CommandGroups;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Commands.SetHatchLock;
import frc.robot.Commands.SetLiftHeight;
import frc.robot.RobotConstants;

/**
 * Add your docs here.
 */
public class CollectHatchFromFeeder extends CommandGroup {
    Value lock = Value.kForward;
    Value unlock = Value.kReverse;

    public CollectHatchFromFeeder() {
        addParallel(new SetOneEightyAngle(RobotConstants.OneEightyAngle.kStraight));
        addSequential(new SetLiftHeight(RobotConstants.LiftHeight.kLiftBottomHatch));
        addSequential(new SetHatchLock(unlock));
    }
}
