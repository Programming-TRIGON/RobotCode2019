package frc.robot;

import java.util.HashMap;
import java.util.Map;

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

        static final double LIFT_ENCODER_TICKS_PER_REV = 0;
        public static final double LIFT_ENCODER_DPP = 1; //RobotDimensions.LIFT_TUBE_DIAMETER * Math.PI / LIFT_ENCODER_TICKS_PER_REV
    }

    public static class RobotPIDSettings {
        // (kP, kI, kD, Tolerance, WaitTime)
        public static final PIDSettings DRIVE_SETTINGS = new PIDSettings(0.0025,0,0.004,5,0.5);
        public static final PIDSettings TURN_SETTINGS = new PIDSettings(0.022,0.00009,0.0735,4,0.5);
        public static final PIDSettings GYRO_DRIVE_SETTINGS = new PIDSettings(0.075,0,0.35,0,0);
        public static final PIDSettings ONE_EIGHTY_STABILIZE_ANGLE_SETTINGS = new PIDSettings(0.003,0.00025,0.01,0,0);
        public static final PIDSettings VISION_TURN_SETTINGS = new PIDSettings(0,0,0,0,0);
    }

    /** the angles of the oneEighty subsystem when performing a task */
    public static enum OneEightyAngle {
        // TODO: Set real angles.
        kStraight(0), // The cargo collector faces ahead in this angle.
        kBack(180), // The cargo collector faces back in this angle.
        kFeeder(270); // This is the angle in order to collect the hatch from the feeder.
        public double key;

        OneEightyAngle(double angle) {
            this.key = angle;
        }
    }

    /** the height the lift should be in for certain tasks */
    public static enum LiftHeight {
        // TODO:set real values.
        kRocketTopHatch(1), kRocketMiddleHatch(1), kRocketBottomHatch(1), kRocketTopCargo(1), kRocketBottomCargo(1),
        kRocketMiddleCargo(1), kLiftBottom(1),
        /** a height that is safe to use OneEighty */
        kOneEightySafety(1),
        /** a height that is safe to fold cargo colleter */
        kCargoFolderSafty(1);

        public double key;

        LiftHeight(double height) {
            this.key = height;
        }
    }

    // we might want to change the enum name...
    public static enum PushCargoPower {
        // TODO:set real values.
        /** the speed we want to push the cargo */
        kCargoShip(1), kLowRocket(1), kMiddleRocket(1), kTopRocket(-1);
        public double key;

        PushCargoPower(double power) {
            this.key = power;
        }
    }

    public static Map<LiftHeight, PushCargoPower> heightToCargoPower = new HashMap<LiftHeight, PushCargoPower>() {
        {
            put(LiftHeight.kRocketBottomCargo, PushCargoPower.kLowRocket);
            put(LiftHeight.kRocketMiddleCargo, PushCargoPower.kMiddleRocket);
            put(LiftHeight.kRocketTopCargo, PushCargoPower.kTopRocket);
        }
    };
}
