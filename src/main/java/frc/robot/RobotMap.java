package frc.robot;

/** a class that defines the ports of the robot */
public class RobotMap {

    class CAN {
        public static final int LIFT_LEFT_MOTOR = 0;
        public static final int LIFT_RIGHT_MOTOR = 1;

        public final static int ONE_EIGHTY_MOTOR = 2;

        public final static int CARGO_COLLECTOR_MOTOR = 3;
        public final static int CARGO_COLLECTOR_HOLDER_RIGHT_MOTOR = 4;
        public final static int CARGO_COLLECTOR_HOLDER_LEFT_MOTOR = 5;
    }

    class PCM {
        public final static int HATCH_COLLECTOR_SOLENOID_A = 0;
        public final static int HATCH_COLLECTOR_SOLENOID_B = 1;

        public final static int HATCH_HOLDER_PVC_SOLENOID_A = 2;
        public final static int HATCH_HOLDER_PVC_SOLENOID_B = 3;

        public final static int HATCH_HOLDER_PUSH_SOLENOID_A = 4;
        public final static int HATCH_HOLDER_PUSH_SOLENOID_B = 5;

        public final static int CARGO_FOLDER_SOLENOID_A = 6;
        public final static int CARGO_FOLDER_SOLENOID_B = 7;
    }

    class DIO {
        public static final int LIFT_TOP_MICRO_SWITCH = 0;
        public static final int LIFT_BOTTOM_MICRO_SWITCH = 1;
        public final static int CARGO_FOLDER_TOP_SWITCH = 2;
        public final static int CARGO_FOLDER_BOTTOM_SWITCH = 3;
        public final static int CARGO_COLLECTOR_SWITCH = 4;
    }

    class AI {
        public static final int LIFT_POTENTIOMETER = 0;
        public final static int ONE_EIGHTY_POTENTIOMETER = 1;
    }

}
