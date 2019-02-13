/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Notifier;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.PathfinderFRC;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.EncoderFollower;

/**
 * Add your docs here.
 */
public class PathFinderClass {
    public static double desiredAngle, angle, angleDifference, turn, rightSpeed, turnSize, leftSpeed;
    public static double kP, kI, kD, kA, maxVelocity;
    public static EncoderFollower encoderFollowerLeft, encoderFollowerRight;
    public static Trajectory leftTrajectory, rightTrajectory;
    public static String pathName;
    public static Notifier pathNotifier;

    public PathFinderClass() {
        PathFinderClass.pathName = "Test";
        PathFinderClass.kP = 0.2;
        PathFinderClass.kI = 0;
        PathFinderClass.kD = 0;
        PathFinderClass.kA = 0.1;
        this.turnSize = -0.01;
        PathFinderClass.maxVelocity = 0.5;
    }

    public static void followPath(){
             if(encoderFollowerLeft.isFinished() && encoderFollowerRight.isFinished()){
 
             }
               
           /** PID on encoders */
           leftSpeed = encoderFollowerLeft.calculate(RobotComponents.DriveTrain.DRIVETRAIN_ENCODER_LEFT.get());
           rightSpeed = encoderFollowerRight.calculate(RobotComponents.DriveTrain.DRIVETRAIN_ENCODER_RIGHT.get());
           /** getting the current angle */
           angle = RobotComponents.DriveTrain.DRIVETRAIN_GYRO.getAngle();
           /**
         * getting oucr desired angle. we use only the left path because both sides of
         * the robot are parallel
         */
          desiredAngle = Pathfinder.r2d(encoderFollowerLeft.getHeading());
           /**
            * getting the anle the robot needs to move. boundHalf: converts values to a
            * number between 180 - -180 so it doesn't end up turning over 180 degrees
            */
           angleDifference = Pathfinder.boundHalfDegrees(desiredAngle - angle);
           /**
            * the output is returned in 180 - -180 degress we want it as a speed to insert
            * into the motors
            */
           // TODO: Set real value
           turn = turnSize * angleDifference;
           /** setting the numbers given by the PID to the motors */
           Robot.DriveTrain.tankDrive((leftSpeed + turn), (rightSpeed - turn));
    }
}