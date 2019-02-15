package frc.robot;

/** a class that defines the ports of the robot */
public class RobotMap {

    // Device ID's for devices connected to the CAN bus
   static class CAN {
        public static final int PCM = 1;

        public static final int LIFT_LEFT_MOTOR = 5; 
        public static final int LIFT_RIGHT_MOTOR = 6; 

        public static final int ONE_EIGHTY_MOTOR = 8;

        public static final int REAR_LEFT_MOTOR = 4;
        public static final int FRONT_LEFT_MOTOR = 3;
        public static final int REAR_RIGHT_MOTOR = 12;
        public static final int FRONT_RIGHT_MOTOR = 7;
 
        public static final int CARGO_COLLECTOR_MOTOR = 9;
        public static final int CARGO_COLLECTOR_HOLDER_RIGHT_MOTOR = 10;
        public static final int CARGO_COLLECTOR_HOLDER_LEFT_MOTOR = 11; 
    }

    // Solenoid ports connected to PCM0
    static class PCM0 {
        public static final int HATCH_COLLECTOR_SOLENOID_FORWARD = 4;
        public static final int HATCH_COLLECTOR_SOLENOID_REVERSE = 5;

        public static final int HATCH_HOLDER_PVC_SOLENOID_FORWARD = 3;
        public static final int HATCH_HOLDER_PVC_SOLENOID_REVERSE = 2;

        public static final int HATCH_HOLDER_PUSH_SOLENOID_FORWARD = 1;
        public static final int HATCH_HOLDER_PUSH_SOLENOID_REVERSE = 0;

        public static final int CARGO_FOLDER_SOLENOID_FORWARD = 6; 
        public static final int CARGO_FOLDER_SOLENOID_REVERSE = 7; 
    }

    // Robot components connected to roboRIO Digital Input Output ports
    static class DIO {
        public static final int LIFT_TOP_MICRO_SWITCH = 6;
        public static final int LIFT_BOTTOM_MICRO_SWITCH = 5;
        public static final int CARGO_FOLDER_TOP_SWITCH = 7;
        public static final int CARGO_FOLDER_BOTTOM_SWITCH = 8;
        public static final int CARGO_COLLECTOR_SWITCH = 4;
        public final static int DRIVE_TRAIN_LEFT_ENCODER_CHANNEL_A = 0;        
        public final static int DRIVE_TRAIN_LEFT_ENCODER_CHANNEL_B = 1;
        public final static int DRIVE_TRAIN_RIGHT_ENCODER_CHANNEL_A = 3;
        public final static int DRIVE_TRAIN_RIGHT_ENCODER_CHANNEL_B = 2;

    }

    // Analog input device ports
    static class ANALOG_INPUT {
        public static final int LIFT_POTENTIOMETER = 3;
        public static final int ONE_EIGHTY_POTENTIOMETER = 0;
    }

}
