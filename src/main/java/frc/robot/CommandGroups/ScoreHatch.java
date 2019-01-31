/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.CommandGroups;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Commands.SetAngle;
import frc.robot.Commands.SetHatchEject;
import frc.robot.Commands.SetHatchLock;
import frc.robot.Commands.SetLiftHeight;

/**
 * Add your docs here.
 */
public class ScoreHatch extends CommandGroup {

    private Value lockedValue = Value.kReverse;
    private Value push = Value.kForward;
    private Value retract = Value.kReverse;
    private double height, angle;
    private double startingHeight = 1;

    public ScoreHatch(double height, double angle) {
        this.height = height;
        this.angle = angle;

        addParallel(new SetAngle(angle));
        addSequential(new SetLiftHeight(height));
        addSequential(new SetHatchLock(lockedValue));
        addSequential(new SetHatchEject(push));
        addSequential(new SetHatchEject(retract));
        addSequential(new SetLiftHeight(startingHeight));
    }

}
