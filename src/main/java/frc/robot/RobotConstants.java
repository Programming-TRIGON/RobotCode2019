package frc.robot;

/** a class used to store constants related to the robot */
public class RobotConstants {
    /** The measurments of the robot */
    public static class RobotDimensions {
        /**
         * The diameter (in CM) of the tube in the lift which twists the rope
         * connected to the lift. this is used to caculate the circumference of the
         * tube.
         */

        public final static Double LIFT_TUBE_DIAMETER = 3.447;
        /**
         * The circumference (in meters) of the tube in the lift which twists the rope
         * connected to the lift. this is used to caculate the scale fractor used to
         * change the given values from the potentoimeter to CM.
         */
        public final static Double LIFT_TUBE_CIRCUMFERENCE = LIFT_TUBE_DIAMETER * Math.PI;

        public final static Double DISTANCE_LIFT_mechanismMECHA
    }

    /** constants for sensors on the robot */
    public static class Sensors {
        /**
         * The value that the potentiometer will multiply the faction it returns (how
         * many times the potentiometer has turned). this is done to give the user a
         * useful unit.
         * it is currenlty set to the circumference of the tube connected
         * to the potentiometer beacuse we desire the value it returns to be in meters
         * and not in turns of the tube.
         */
        public static final double POTENTIOMETER_SCALE_FRACTOR = RobotDimensions.LIFT_TUBE_CIRCUMFERENCE;
        /**
         * The value that the potentiometer will add to the number that the
         * potentiometer returns after multiplying the potentiometer output by the scale
         * fractor. this is done to give the user a useful unit.
         * it is currntly set to
         */
        public static final int POTENTOIMETER_OFFSET = -1;
    }
}
