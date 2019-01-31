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
import frc.robot.Commands.SetHatchEject;
import frc.robot.Commands.SetHatchLock;
import frc.robot.Commands.SetLiftHeight;
import frc.robot.RobotConstants.RobotDimensions.Angle;
import frc.robot.RobotConstants.RobotDimensions.Height;
/**
 * Add your docs here.
 */
public class ScoreHatch extends CommandGroup{
    private Value lockedValue = Value.kReverse;
    private Value push = Value.kForward;
    private Value retract = Value.kReverse;
    private Height height;
    private Angle angle; 

    public ScoreHatch(Height height, Angle angle) {
        this.height = height;
        this.angle = angle;

        addParallel(new SetAngle(this.angle));
        addSequential(new SetLiftHeight(this.height));
        addSequential(new SetHatchLock(this.lockedValue));
        addSequential(new SetHatchEject(this.push));
        addSequential(new SetHatchEject(this.retract));
        addSequential(new SetLiftHeight(RobotConstants.RobotDimensions.Height.kLiftBottom));
    }
}
