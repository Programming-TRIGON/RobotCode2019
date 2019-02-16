package frc.robot;

import java.util.HashMap;
import java.util.Map;

import com.spikes2212.utils.PIDSettings;

/** a class used to store constants related to the robot */
public class RobotConstants {
    // oneEighty constants
    public static final double robotLength = 0.82;
    public static class oneEighty {
        // TODO set real height
        public static final double MINIMUM_HEIGHT = 1;
    }

    /** The measurments of the robot */
    public static class RobotDimensions {
        // TODO set real values
        /**
         * The diameter (in CM) of the tube in the lift which twists the rope connected
         * to the lift. this is used to caculate the circumference of the tube.
         */

        public static final double ONE_EIGHTY_LENGTH = 42;

        public static final Double LIFT_TUBE_DIAMETER = 3.447;
        /**
         * The circumference (in meters) of the tube in the lift which twists the rope
         * connected to the lift. this is used to caculate the scale fractor used to
         * change the given values from the potentoimeter to CM.
         */
        public static final double LIFT_TUBE_CIRCUMFERENCE = LIFT_TUBE_DIAMETER * Math.PI;
        /**
         * the potentoimeter gets its initial height for subtracting from the
         * calculations
         */
        public static final double DISTANCE_LIFT_MECHANISM_FROM_FLOOR = 32.24;

        public static final double WHEEL_DIAMETER = 15.24;
    }

    /** constants for sensors on the robot */
    public static class Sensors {

        /** returns potentiometer value by meters */
        public static final double LIFT_POTENTIOMETER_SCALE_FACTOR = RobotDimensions.LIFT_TUBE_CIRCUMFERENCE;

        /**
         * The value that the potentiometer will add to the number that the it returns
         */
        public static final Double LIFT_POTENTOIMETER_OFFSET = RobotDimensions.DISTANCE_LIFT_MECHANISM_FROM_FLOOR;
    }

    public static class RobotPIDSettings {
        // (kP, kI, kD, Tolerance, WaitTime)
        public static final PIDSettings DRIVE_SETTINGS = new PIDSettings(0.0025,0,0.004,5,0.5);
        public static final PIDSettings TURN_SETTINGS = new PIDSettings(0.022,0.00009,0.0735,4,0.5);
        public static final PIDSettings REFLECTOR_TRACK_SETTINGS = new PIDSettings(0,0,0,0,0); 
        public static final PIDSettings GYRO_DRIVE_SETTINGS = new PIDSettings(0.05,0,0.3,0,0);

    }
    
    /**
     * The value at which the potentiometer will multiply the fraction it returns
     */
    public static final double ONE_EIGHTY_POTENTIOMETER_ANGLE_MULTIPLIER = -3600;
    // TODO change change offset
    /** the angle at which the potentiometer starts */
    public static final double ONE_EIGHTY_POTENTIOMETER_OFFSET = 525;

    public static final double ENCODER_DPP = RobotDimensions.WHEEL_DIAMETER*Math.PI/360;

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
