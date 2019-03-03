package frc.robot.CommandGroups;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.RobotStates;
import frc.robot.Commands.SetHatchLock;
import frc.robot.RobotConstants.OneEightyAngle;
import frc.robot.RobotConstants.LiftHeight;

/**
 * Sets the 180 and lift to feeder height and direction and opens the hatch collector
 */
public class CollectHatchFromFeeder extends CommandGroup {

    public CollectHatchFromFeeder() {
        addSequential(new SetHatchLock(Value.kReverse));
        OneEightyAngle angleToSet = RobotStates.isDriveInverted() ? OneEightyAngle.kBack : OneEightyAngle.kStraight; 
        //this sequence checks if the 180 will try to move 180 dgree when he cant because the lift is to low.
        //if the 180 wont do 180 degrees but rather do pid corrections its allowed and the lift will go to fider height,
        //elses the lift will go up to 180 safety height, the 180 will turn 180 degree and do pid corrections and then the lift will
        //go down to fider hatch height. 
        if(angleToSet.equals(RobotStates.getDesireOneEightyAngle()))
            addParallel(new SetOneEightyAngle(angleToSet));
        else{
            //if the 180 is override we wont want to go up and down
            if(!RobotStates.isOneEightyOverride()){
            addParallel(new SetLiftHeight(LiftHeight.kOneEightySafety));
            addSequential(new WaitCommand(0.5));
            addParallel(new SetOneEightyAngle(angleToSet));
            }
        }
        addSequential(new WaitCommand(0.3));
        addParallel(new SetLiftHeight(LiftHeight.kLiftBottomHatch));
    }
}
