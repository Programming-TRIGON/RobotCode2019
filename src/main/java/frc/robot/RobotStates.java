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
    double liftSetpoint;
    double oneEightySetpoint;
    boolean hasCargo;
    boolean driveInverted;

    /**
     * @return the liftSetpoint
     */
    public double getLiftSetpoint() {
        return liftSetpoint;
    }

    /**
     * @param liftSetpoint the liftSetpoint to set
     */
    public void setLiftSetpoint(double liftSetpoint) {
        this.liftSetpoint = liftSetpoint;
    }

    /**
     * @return the oneEightySetpoint
     */
    public double getOneEightySetpoint() {
        return oneEightySetpoint;
    }

    /**
     * @param oneEightySetpoint the oneEightySetpoint to set
     */
    public void setOneEightySetpoint(double oneEightySetpoint) {
        this.oneEightySetpoint = oneEightySetpoint;
    }

    /**
     * @return the hasCargo
     */
    public boolean isHasCargo() {
        return hasCargo;
    }

    /**
     * @param hasCargo the hasCargo to set
     */
    public void setHasCargo(boolean hasCargo) {
        this.hasCargo = hasCargo;
    }

    /**
     * @return the driveInverted
     */
    public boolean isDriveInverted() {
        return driveInverted;
    }

    /**
     * @param driveInverted the driveInverted to set
     */
    public void setDriveInverted(boolean driveInverted) {
        this.driveInverted = driveInverted;
    }
}
