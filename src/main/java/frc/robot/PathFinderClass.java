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
    public static double desiredAngle, angle, angleDifference, turn, rightSpeed, leftSpeed;
    public static double kP, kI, kD, kA, turnSize, maxVelocity;
    public static EncoderFollower encoderFollowerLeft, encoderFollowerRight;
    public static Trajectory leftTrajectory, rightTrajectory;
    public static String pathName;
    public static Notifier pathNotifier;
    {
     pathName = "Test";
     kP = 0.2;
     kI = 0;
     kD = 0;
     kA = 0.1;
     turnSize = -0.01;
     maxVelocity = 0.5;
         /** reseting sensors */
         RobotComponents.DriveTrain.DRIVETRAIN_ENCODER_RIGHT.reset();
         RobotComponents.DriveTrain.DRIVETRAIN_ENCODER_LEFT.reset();
         RobotComponents.DriveTrain.DRIVETRAIN_GYRO.reset();
         /**
          * The tarjectory is the path(each side of the drivetrain has one) this is like
          * the "error" in PID
          */
         // TODO: check when this bug is fixed (wpilibs side)
         leftTrajectory = PathfinderFRC.getTrajectory(pathName + ".right");
         rightTrajectory = PathfinderFRC.getTrajectory(pathName + ".left");
         /**
          * The encoder follower does PID using encoder input to find the motor power
          */
         encoderFollowerLeft = new EncoderFollower(leftTrajectory);
         encoderFollowerRight = new EncoderFollower(rightTrajectory);
         /**
          * giving the encoderFollowers the current ticks (starting point) and the CPR
          * and wheelDiamter as we don't want it to be in ticks we want a usable
          * measurment
          */
         encoderFollowerLeft.configureEncoder(RobotComponents.DriveTrain.DRIVETRAIN_ENCODER_LEFT.get(),
             RobotConstants.Sensors.TicksPerRevolution, RobotConstants.RobotDimensions.WheelDiameter);
         encoderFollowerRight.configureEncoder(RobotComponents.DriveTrain.DRIVETRAIN_ENCODER_RIGHT.get(),
             RobotConstants.Sensors.TicksPerRevolution, RobotConstants.RobotDimensions.WheelDiameter);
         /** setting the PID values: kp, ki, kd, kv, ka */
         encoderFollowerLeft.configurePIDVA(kP, kI, kD, 1 / maxVelocity, kA);
         encoderFollowerRight.configurePIDVA(kP, kI, kD, 1 / maxVelocity, kA); } 
         public void followPath(){
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
          desiredAngle = Pathfinder.r2d(EncoderFollower.getHeading());
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
