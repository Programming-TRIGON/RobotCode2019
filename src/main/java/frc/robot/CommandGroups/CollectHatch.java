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
import frc.robot.Commands.SetHatchLock;
import frc.robot.Commands.SetLiftHeight;
import frc.robot.Commands.setHatchCollectorstate;

/**
 * Add your docs here.
 */
public class CollectHatch extends CommandGroup {
    private double liftHeight, angle;
    //TODO switch to the correct values for the go down and up
    private boolean SSDown = true;
    private boolean SSUp;
    private Value lock = Value.kForward;

    public CollectHatch(double liftHeight, double angle){
        this.liftHeight = liftHeight;
        this.angle = angle;

        addParallel(new SetLiftHeight(liftHeight));
        addSequential(new SetAngle(angle));
        addSequential(new setHatchCollectorstate(SSDown));
        addSequential(new setHatchCollectorstate(SSUp));
        addSequential(new SetHatchLock(lock));
    }
}
