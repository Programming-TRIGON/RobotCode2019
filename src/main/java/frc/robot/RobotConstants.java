package frc.robot;

import com.spikes2212.utils.PIDSettings;

/** a class used to store constants related to the robot */
public class RobotConstants {
    /** The measurments of the robot */
    public static class RobotDimensions {
        public static final double ONE_EIGHTY_LENGTH = 42;

        public static final double LIFT_TUBE_DIAMETER = 0.04; // 3.447; //what is it moshe?!
        public static final double DISTANCE_LIFT_MECHANISM_FROM_FLOOR = 32.24;

        public static final double ROBOT_LENGTH = 0.974; // without bumpers 0.82
        public static final double DRIVE_WHEEL_DIAMETER = 15.24;
    }

    /** constants for sensors on the robot */
    public static class Sensors {
        public static final double ONE_EIGHTY_POTENTIOMETER_ANGLE_MULTIPLIER = -3600;
        public static final double ONE_EIGHTY_POTENTIOMETER_OFFSET = 525;

        public static final double DRIVE_ENCODER_DPP = RobotDimensions.DRIVE_WHEEL_DIAMETER * Math.PI / 360;


        public static final double LIFT_ENCODER_DPP = 1/21000.0;
        public static final double LIFT_ENCODER_OFFSET = 0.4;
    }

    public static class RobotPIDSettings {
        // (kP, kI, kD, Tolerance, WaitTime)
        public static final PIDSettings DRIVE_SETTINGS = new PIDSettings(0.0025, 0, 0.004, 5, 0.5);
        public static final PIDSettings TURN_SETTINGS = new PIDSettings(0.022, 0.00009, 0.0735, 4, 0.5);
        public static final PIDSettings GYRO_DRIVE_SETTINGS = new PIDSettings(0.075, 0, 0.35, 0, 0);
        public static final PIDSettings ONE_EIGHTY_STABILIZE_ANGLE_SETTINGS = new PIDSettings(0.009, 0.00005, 0.00075, 2, 0);
        public static final PIDSettings VISION_TURN_SETTINGS = new PIDSettings(0, 0, 0, 0, 0);
        public static final PIDSettings VISION_DISTANCE_SETTINGS = new PIDSettings(0, 0, 0, 0, 0);
        public static final PIDSettings LIFT_HEIGHT_SETTINGS = new PIDSettings(5,0,15,0,0);
    }


    /** the angles of the oneEighty subsystem when performing a task */
    public static enum OneEightyAngle {
        kStraight(40.5), // The cargo collector faces ahead in this angle.
        kBack(250), // The cargo collector faces back in this angle.
        kTopStraight(4),
        kTopBack(278),
        kCargoCollection(60);    
        public double key;

        OneEightyAngle(double angle) {
            this.key = angle;
        }
    }

    /** the height the lift should be in for certain tasks */
    public static enum LiftHeight {
        /** Hatch rocket heights */
        kLiftBottomHatch(0.1851), kRocketTopHatch(1.461),  kRocketMiddleHatch(0.875),
        /** Cargo rocket heights */
        kRocketTopCargo(1.81), kRocketBottomCargo(0.8611), kRocketMiddleCargo(0.547), 
        /** A height that is safe to use OneEighty */
        kOneEightySafety(0.5314), kOneEightyCargoSafety(0.7),
        /** Collection heights */
        kCargoCollection(0), kHatchCollection(1),
        /** Cargo ship height */
        kCargoShip(1.1570),
        /** Cargo safty height */
        kCargoFolderSafty(0.7);

        public double key;

        LiftHeight(double height) {
            this.key = height;
        }
    }

    public static enum PrepareToScoreHeight {
        kLow, kMedium, kHigh, kCargoShip
    }

    public static PrepareToScoreHeight[] heights = {PrepareToScoreHeight.kLow, PrepareToScoreHeight.kMedium, 
        PrepareToScoreHeight.kHigh, PrepareToScoreHeight.kCargoShip};
}
