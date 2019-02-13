package frc.robot;
//TODO: add real ports
/** a class that defines the ports of the robot */
public class RobotMap {

    // Device ID's for devices connected to the CAN bus
   static class CAN {
        public static final int pcm = 1;

        public static final int LIFT_LEFT_MOTOR = 7;
        public static final int LIFT_RIGHT_MOTOR = 4;

        public final static int ONE_EIGHTY_MOTOR = 3;

        public final static int REAR_LEFT_MOTOR = 6;
        public final static int FRONT_LEFT_MOTOR = 10;
        public final static int REAR_RIGHT_MOTOR = 9;
        public final static int FRONT_RIGHT_MOTOR = 8;
     
        public final static int CARGO_COLLECTOR_MOTOR = 12;
        public final static int CARGO_COLLECTOR_HOLDER_RIGHT_MOTOR = 5;
        public final static int CARGO_COLLECTOR_HOLDER_LEFT_MOTOR = 11;
    }

    // Solenoid ports connected to PCM0
    static class PCM0 {
        public final static int HATCH_COLLECTOR_SOLENOID_A = 0;
        public final static int HATCH_COLLECTOR_SOLENOID_B = 1;

        public final static int HATCH_HOLDER_PVC_SOLENOID_A = 2;
        public final static int HATCH_HOLDER_PVC_SOLENOID_B = 3;

        public final static int HATCH_HOLDER_PUSH_SOLENOID_A = 4;
        public final static int HATCH_HOLDER_PUSH_SOLENOID_B = 5;

        public final static int CARGO_FOLDER_SOLENOID_A = 6;
        public final static int CARGO_FOLDER_SOLENOID_B = 7;

    }

    // Robot components connected to roboRIO Digital Input Output ports
    static class DIO {

        public final static int DRIVE_TRAIN_LEFT_ENCODER_CHANNEL_A = 0;        
        public final static int DRIVE_TRAIN_LEFT_ENCODER_CHANNEL_B = 1;
        public final static int DRIVE_TRAIN_RIGHT_ENCODER_CHANNEL_A = 3;
        public final static int DRIVE_TRAIN_RIGHT_ENCODER_CHANNEL_B = 4;
        public static final int LIFT_TOP_MICRO_SWITCH = 5;
        public static final int LIFT_BOTTOM_MICRO_SWITCH = 6;
        public final static int CARGO_FOLDER_TOP_SWITCH = 7;
        public final static int CARGO_FOLDER_BOTTOM_SWITCH = 8;
        public final static int CARGO_COLLECTOR_SWITCH = 9;

    }

    // Analog input device ports
    static class ANALOG_INPUT {
        public static final int LIFT_POTENTIOMETER = 0;
        public final static int ONE_EIGHTY_POTENTIOMETER = 1;
        public final static int GYRO = 2;
    }

}
