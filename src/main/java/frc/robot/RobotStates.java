/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.RobotConstants.LiftHeight;

/**
 * Add your docs here.
 */
public class RobotStates {
    static double liftSetpoint;
    static double oneEightySetpoint;
    static boolean hasCargo;
    static boolean driveInverted;
    static double heightIndex = 1;   
    
    public static void increaseHeight() {
        if(RobotStates.heightIndex<2)
            RobotStates.heightIndex ++;
    }

    public static void decreaseHeight() {
        if(RobotStates.heightIndex>0)
            RobotStates.heightIndex --;
    }

    public static double getHeightIndex(){
        return RobotStates.heightIndex;
    }

    /**
     * @return the liftSetpoint
     */
    public static double getLiftSetpoint() {
        return liftSetpoint;
    }

    /**
     * @param liftSetpoint the liftSetpoint to set
     */
    public static void setLiftSetpoint(double liftSetpoint) {
        RobotStates.liftSetpoint = liftSetpoint;
    }

    /**
     * @return the oneEightySetpoint
     */
    public static double getOneEightySetpoint() {
        return oneEightySetpoint;
    }

    /**
     * @param oneEightySetpoint the oneEightySetpoint to set
     */
    public static void setOneEightySetpoint(double oneEightySetpoint) {
        RobotStates.oneEightySetpoint = oneEightySetpoint;
    }

    /**
     * @return the hasCargo
     */
    public static boolean isHasCargo() {
        return hasCargo;
    }

    /**
     * @param hasCargo the hasCargo to set
     */
    public static void setHasCargo(boolean hasCargo) {
        RobotStates.hasCargo = hasCargo;
    }

    /**
     * @return the driveInverted
     */
    public static boolean isDriveInverted() {
        return driveInverted;
    }

    /**
     * @param driveInverted the driveInverted to set
     */
    public static void setDriveInverted(boolean driveInverted) {
        RobotStates.driveInverted = driveInverted;
    }
}
