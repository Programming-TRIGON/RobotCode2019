package frc.robot;

/** a class that defines the ports of the robot */
public class RobotMap {

    // Device ID's for devices connected to the CAN bus
   static class CAN {
        public static final int PCM = 1;

        public static final int LIFT_LEFT_MOTOR = 5; //
        public static final int LIFT_RIGHT_MOTOR = 11; //

        public static final int ONE_EIGHTY_MOTOR = 8;

        public static final int REAR_LEFT_MOTOR = 3;
        public static final int FRONT_LEFT_MOTOR = 4;
        public static final int REAR_RIGHT_MOTOR = 12;
        public static final int FRONT_RIGHT_MOTOR = 7;
     
        public static final int CARGO_COLLECTOR_MOTOR = 9;
        public static final int CARGO_COLLECTOR_HOLDER_RIGHT_MOTOR = 6;
        public static final int CARGO_COLLECTOR_HOLDER_LEFT_MOTOR = 10; //
    }

    // Solenoid ports connected to PCM0
    static class PCM0 {
        // as of 12/2/19 02:20
        public static final int HATCH_COLLECTOR_SOLENOID_A = 7;
        public static final int HATCH_COLLECTOR_SOLENOID_B = 6;

        public static final int HATCH_HOLDER_PVC_SOLENOID_A = 4;
        public static final int HATCH_HOLDER_PVC_SOLENOID_B = 5;//6;

        public static final int HATCH_HOLDER_PUSH_SOLENOID_A = 3;
        public static final int HATCH_HOLDER_PUSH_SOLENOID_B = 1;

        public static final int CARGO_FOLDER_SOLENOID_A = 0; //3 Checked
        public static final int CARGO_FOLDER_SOLENOID_B = 2; //6 Checked

    }

    // Robot components connected to roboRIO Digital Input Output ports
    static class DIO {
        public static final int LIFT_TOP_MICRO_SWITCH = 4;
        public static final int LIFT_BOTTOM_MICRO_SWITCH = 5;
        public static final int CARGO_FOLDER_TOP_SWITCH = 2;
        public static final int CARGO_FOLDER_BOTTOM_SWITCH = 3;
        public static final int CARGO_COLLECTOR_SWITCH = 0;
    }

    // Analog input device ports
    static class ANALOG_INPUT {
        public static final int LIFT_POTENTIOMETER = 0;
        public static final int ONE_EIGHTY_POTENTIOMETER = 1;
    }

}
