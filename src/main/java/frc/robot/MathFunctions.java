package frc.robot;

/** A class used to store math functions */
public class MathFunctions {
    /**
     * The Encoder uses ticks to measure movement. 4 tick = 1 pulse. This function
     * tells us how many meters does a single pulse produce
     */
    public final static Double PULSE_TO_METER = RobotConstants.RobotDimensions.LIFT_TUBE_DIAMETER * Math.PI
            * RobotConstants.Sensors.ENCODER_PPR / 100;
}
