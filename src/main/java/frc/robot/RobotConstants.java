package frc.robot;

import java.util.HashMap;
import java.util.Map;

/** a class used to store constants related to the robot */
public class RobotConstants {
    //oneEighty constants
    public static class oneEighty{
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

        public final  static double ONE_EIGHTY_LENGTH = 42;

        public final static Double LIFT_TUBE_DIAMETER = 3.447;
        /**
         * The circumference (in meters) of the tube in the lift which twists the rope
         * connected to the lift. this is used to caculate the scale fractor used to
         * change the given values from the potentoimeter to CM.
         */
        public final static double LIFT_TUBE_CIRCUMFERENCE = LIFT_TUBE_DIAMETER * Math.PI;
        /**
         * the potentoimeter gets its initial height for subtracting from the
         * calculations
         */
        public final static double DISTANCE_LIFT_MECHANISM_FROM_FLOOR = 32.24;
    }

    /** constants for sensors on the robot */
    public static class Sensors {
        /**
         * The value that the potentiometer will multiply the faction it returns (how
         * many times the potentiometer has turned). this is done to give the user a
         * useful unit. it is currenlty set to the circumference of the tube connected
         * to the potentiometer beacuse we desire the value it returns to be in meters
         * and not in turns of the tube.
         */
        public static final double LIFT_POTENTIOMETER_SCALE_FACTOR = RobotDimensions.LIFT_TUBE_CIRCUMFERENCE;
        /**
         * The value that the potentiometer will add to the number that the
         * potentiometer returns after multiplying the potentiometer output by the scale
         * fractor. this is done to give the user a useful unit. it is currntly set to
         * the mechanisaims distance from the floor as we want to insert the hight of a
         * target from the floor and the potentiometer can translate it into a value it
         * understands.
         */
        public static final Double LIFT_POTENTOIMETER_OFFSET = RobotDimensions.DISTANCE_LIFT_MECHANISM_FROM_FLOOR;
    }

    /**
     * The value at which the potentiometer will multiply the fraction it returns
     */
    public static final double ONE_EIGHTY_POTENTIOMETER_ANGLE_MULTIPLIER = 0;
    // to do change change offset
    /** the angle at which the potentiometer starts */
    public static final double ONE_EIGHTY_POTENTIOMETER_OFFSET = 0;

    /**
     * the amount of time we want the lift to stay on the target fof the PID to end
     */
    /**
     * the angles which the oneEighty subsystem should do a PID on.
     */
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

    //we might want to change the enum name...
    public static enum PushCargoPower {
         // TODO:set real values.
         /** the speed we want to push the cargo */
        kCargoShip(1), kLowRocket(1), kMiddleRocket(1), kTopRocket(1);
        public double key;

        PushCargoPower(double power) {
            this.key = power;
        }
    }

    public static Map<LiftHeight, PushCargoPower> heightToCargoPower = new HashMap<LiftHeight, PushCargoPower>(){
        {
            put(LiftHeight.kRocketBottomCargo, PushCargoPower.kLowRocket);
            put(LiftHeight.kRocketMiddleCargo, PushCargoPower.kMiddleRocket);
            put(LiftHeight.kRocketTopCargo, PushCargoPower.kTopRocket);
        }
    };
}
