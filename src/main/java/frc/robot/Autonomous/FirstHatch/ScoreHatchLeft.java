/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Autonomous.FirstHatch;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveArcade;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveTankWithPID;
import com.spikes2212.utils.PIDSettings;

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


  Supplier<Double> KP = ConstantHandler.addConstantDouble("ScoreHatchLeft-KP", 0.2);
  Supplier<Double> KI = ConstantHandler.addConstantDouble("ScoreHatchLeft-KI", 0);
  Supplier<Double> KD = ConstantHandler.addConstantDouble("ScoreHatchLeft-KD", 0);
  Supplier<Double> tolerance = ConstantHandler.addConstantDouble("ScoreHatchLeft- tolerance", 0.1);

  public ScoreHatchLeft(Target target) {
    RobotComponents.DriveTrain.LEFT_ENCODER.reset();
    RobotComponents.DriveTrain.RIGHT_ENCODER.reset();
    
    addSequential(new DriveTankWithPID(Robot.driveTrain, RobotComponents.DriveTrain.LEFT_ENCODER, RobotComponents.DriveTrain.RIGHT_ENCODER, target.getDistance(), new PIDSettings(KP.get(), KI.get(), KD.get(), tolerance.get(), 0.1)));
    //addSequential(new DriveArcade(Robot.driveTrain, moveValueSupplier, rotateValueSupplier));

  }
}
