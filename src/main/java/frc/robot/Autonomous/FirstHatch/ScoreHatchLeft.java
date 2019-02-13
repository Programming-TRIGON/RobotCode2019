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
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveArcadeWithPID;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveTankWithPID;
import com.spikes2212.utils.PIDSettings;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
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


  Supplier<Double> KP = ConstantHandler.addConstantDouble("KP", -0.0001);
  Supplier<Double> KI = ConstantHandler.addConstantDouble("KI", 0);
  Supplier<Double> KD = ConstantHandler.addConstantDouble("KD", 0.001);
  Supplier<Double> tolerance = ConstantHandler.addConstantDouble("tolerance", 10);
  Supplier<Double> t = () -> {return 0.0;};

  

  public ScoreHatchLeft(Target target) {
    PIDSettings pidSettings = new PIDSettings(KP.get(), KI.get(), KD.get(), tolerance.get(), 0);
    
    RobotComponents.DriveTrain.LEFT_ENCODER.reset();
    RobotComponents.DriveTrain.RIGHT_ENCODER.reset();
    
    //addSequential(new DriveTankWithPID(Robot.driveTrain, RobotComponents.DriveTrain.LEFT_ENCODER, RobotComponents.DriveTrain.RIGHT_ENCODER, target.getDistance(), pidSettings));
    //addSequential(new WaitCommand(5));
    addSequential(new DriveArcadeWithPID(Robot.driveTrain, RobotComponents.DriveTrain.GYRO, () -> 90.0, () -> 0.0, pidSettings, 360.0, true));
  }
}
