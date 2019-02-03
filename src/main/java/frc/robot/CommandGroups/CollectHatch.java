/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.CommandGroups;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotConstants;
import frc.robot.Commands.SetAngle;
import frc.robot.Commands.SetHatchLock;
import frc.robot.Commands.SetLiftHeight;
import frc.robot.Commands.setHatchCollectorState;
import edu.wpi.first.wpilibj.command.WaitCommand;
/**
 * Add your docs here.
 */
public class CollectHatch extends CommandGroup {
    //TODO switch to the correct values for the go down and up
    private boolean SSDown = true;
    private boolean SSUp;
    private Value lock = Value.kForward;

    public CollectHatch(){

        addParallel(new SetLiftHeight(RobotConstants.RobotDimensions.Height.kLiftBottom));
        addSequential(new SetAngle(RobotConstants.RobotDimensions.Angle.kStraight));
        addSequential(new setHatchCollectorState(SSDown));
        addSequential(new WaitCommand(1));
        addSequential(new setHatchCollectorState(SSUp));
        addSequential(new SetHatchLock(lock));
    }
}
