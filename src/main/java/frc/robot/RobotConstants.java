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
        public static final PIDSettings ONE_EIGHTY_STABILIZE_ANGLE_SETTINGS = new PIDSettings(0.003, 0.00025, 0.01, 0, 0);
        public static final PIDSettings VISION_TURN_SETTINGS = new PIDSettings(0, 0, 0, 0, 0);
        public static final PIDSettings VISION_DISTANCE_SETTINGS = new PIDSettings(0, 0, 0, 0, 0);

    }


    /** the angles of the oneEighty subsystem when performing a task */
    public static enum OneEightyAngle {
        // TODO: Set real angles.
        kStraight(0), // The cargo collector faces ahead in this angle.
        kBack(180), // The cargo collector faces back in this angle.
        kTopStraight(-1),
        kTopBack(181),
        kCargoCollection(1);    
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
        kRocketTopCargo(1), kRocketBottomCargo(1), kRocketMiddleCargo(1), 
        /** A height that is safe to use OneEighty */
        kOneEightySafety(1),
        /** Collection heights */
        kCargoCollection(1), kHatchCollection(1),
        /** Cargo ship height */
        kCargoShip(1),
        /** Cargo safty height */
        kCargoSafty(0.3);

        public double key;

        LiftHeight(double height) {
            this.key = height;
        }
    }

    public static enum PrepareToScoreHeight {
        kLow, kMedium, kHigh, kCargoShip
    }
    // we might want to change the enum name...
    public static enum PushCargoPower {
        // TODO:set real values.
        /** the speed we want to push the cargo */
        kCargoShip(-1), kLowRocket(-1), kMiddleRocket(-1), kTopRocket(-1);
        public double key;

        PushCargoPower(double power) {
            this.key = power;
        }
    }

    public static PrepareToScoreHeight[] heights = {PrepareToScoreHeight.kLow, PrepareToScoreHeight.kMedium, 
        PrepareToScoreHeight.kHigh, PrepareToScoreHeight.kCargoShip};
}
