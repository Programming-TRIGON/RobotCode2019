/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Autonomous.FirstHatch;

import com.spikes2212.genericsubsystems.drivetrains.commands.DriveTankWithPID;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.RobotComponents;

public class ScoreHatchLeft extends CommandGroup {
  public enum Target{
    FIRST(100),
    SECOND(200),
    THIRD(300);
    public int distance;
    Target(int distance){
      this.distance = distance;
    }
    public int getDistance() {
      return distance;
    }
    
  }


  

  public ScoreHatchLeft(Target target) {
    addSequential(new DriveTankWithPID(Robot.driveTrain, RobotComponents.DriveTrain.LEFT_ENCODER, RobotComponents.DriveTrain.RIGHT_ENCODER, target.getDistance(), PIDSettings);
  }
}
