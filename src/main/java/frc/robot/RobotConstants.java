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
        public static final double ONE_EIGHTY_POTENTIOMETER_OFFSET =3080+17;

        public static final double DRIVE_ENCODER_DPP = RobotDimensions.DRIVE_WHEEL_DIAMETER * Math.PI / 360;

        public static final double LIFT_ENCODER_DPP = 1/21000.0; //OFFSET = 0.38;

        public static final double FORWARD_REFLECTOR_SETPOINT = 0.3;
        public static final double BACKWARD_REFLECTOR_SETPOINT = 0;
        public static final double VISION_DISTANCE_SETPOINT = 0;
    }

    public static class RobotPIDSettings {
        // (kP, kI, kD, Tolerance, WaitTime)
        public static final PIDSettings DRIVE_SETTINGS = new PIDSettings(0.0025, 0, 0.004, 5, 0.5);
        public static final PIDSettings TURN_SETTINGS = new PIDSettings(0.022, 0.00009, 0.0735, 4, 0.5);
        public static final PIDSettings GYRO_DRIVE_SETTINGS = new PIDSettings(0.075, 0, 0.35, 0, 0);
        public static final PIDSettings ONE_EIGHTY_STABILIZE_ANGLE_SETTINGS = new PIDSettings(0.009, 0.00005, 0.00075, 2, 0);
        public static final PIDSettings VISION_TURN_SETTINGS = new PIDSettings(0.4, 0, 0.01, 2, 2);
        public static final PIDSettings VISION_DISTANCE_SETTINGS = new PIDSettings(0, 0, 0, 0, 0);
        public static final PIDSettings LIFT_HEIGHT_SETTINGS = new PIDSettings(6,0,5.5,0.05,1);
    }


    /** the angles of the oneEighty subsystem when performing a task */
    public static enum OneEightyAngle {
        kStraight(0), // The cargo collector faces ahead in this angle.
        kBack(180), // The cargo collector faces back in this angle.
        kTopStraight(-65),
        kTopBack(220),
        kCargoCollection(17),
        kCargoShipForward(-27.5),
        kCargoShipBack(180 + 22),
        kFortyFive(20);     
        public double key;

        OneEightyAngle(double angle) {
            this.key = angle;
        }
    }

    /** the height the lift should be in for certain tasks */
    public static enum LiftHeight {
        /** Hatch rocket heights */
        kLiftBottomHatch(0.45), kLiftBottomHatchCargoSide(0.4), kRocketMiddleHatch(1.95), kRocketTopHatch(1),
        /** Cargo rocket heights */
        kRocketTopCargo(1), kRocketBottomCargo(1.2), kRocketMiddleCargo(1.95), 
        /** A height that is safe to use OneEighty */
        kOneEightySafety(1), kOneEightyCargoSafety(1), //kOneEightySafety not checked currently on cargo safety
        /** Collection heights */
        kCargoCollection(0), kHatchCollection(0.3),
        /** Cargo ship height */
        kCargoShip(1.6), //was 1.9
        /** Cargo safty height */
        kCargoFolderSafty(0.45);

        public double key;

        LiftHeight(double height) {
            this.key = height;
        }
    }

    public static enum PrepareToScoreHeight {
        kLow, kMedium, kHigh, kCargoShip
    }

	public static final double LIFT_BASE_POWER = 0;

    public static PrepareToScoreHeight[] heights = {PrepareToScoreHeight.kLow, PrepareToScoreHeight.kMedium, 
        PrepareToScoreHeight.kHigh, PrepareToScoreHeight.kCargoShip};
}
