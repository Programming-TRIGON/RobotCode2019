/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.CommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotConstants;
import frc.robot.Commands.SetLiftHeight;
import frc.robot.RobotConstants.LiftHeight;
/**
 * Add your docs here.
 */
public class ScoreHatch extends CommandGroup{
    private LiftHeight height; 

    public ScoreHatch(LiftHeight height) {
        this.height = height;


        addParallel(new SetOneEightyAngle(RobotConstants.OneEightyAngle.kStraight));
        addSequential(new SetLiftHeight(this.height));
        addSequential(new EjectHatch());
        addSequential(new SetLiftHeight(RobotConstants.LiftHeight.kLiftBottom));
    }
}
