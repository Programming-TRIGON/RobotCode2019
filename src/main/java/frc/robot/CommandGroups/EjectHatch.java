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
import frc.robot.Commands.SetOneEightyAngle;
import frc.robot.Commands.SetHatchEject;
import frc.robot.Commands.SetHatchLock;
/**
 * Add your docs here.
 */
public class EjectHatch extends CommandGroup{
    
    Value unlock = Value.kReverse;
    Value extend = Value.kForward;
    Value retract = Value.kReverse;

    public EjectHatch(){

        addSequential(new SetOneEightyAngle(RobotConstants.OneEightyAngle.kStraight));
        addSequential(new SetHatchLock(unlock));
        addSequential(new SetHatchEject(extend));
        addSequential(new SetHatchEject(retract));
    }
}
