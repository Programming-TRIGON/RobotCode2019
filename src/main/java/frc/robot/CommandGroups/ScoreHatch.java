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
import frc.robot.Commands.SetHatchEject;
import frc.robot.Commands.SetHatchLock;
import frc.robot.Commands.SetLiftHeight;
import frc.robot.Commands.SetOneEightyAngle;
import frc.robot.RobotConstants.LiftHeight;
/**
 * Add your docs here.
 */
public class ScoreHatch extends CommandGroup{
    private Value unlockedValue = Value.kReverse;
    private Value push = Value.kForward;
    private Value retract = Value.kReverse;
    private LiftHeight height; 

    public ScoreHatch(LiftHeight height) {
        this.height = height;


        addParallel(new SetOneEightyAngle(RobotConstants.OneEightyAngle.kStraight));
        addSequential(new SetLiftHeight(this.height));
        addSequential(new SetHatchLock(this.unlockedValue));
        addSequential(new SetHatchEject(this.push));
        addSequential(new SetHatchEject(this.retract));
        addSequential(new SetLiftHeight(RobotConstants.LiftHeight.kLiftBottom));
    }
}
