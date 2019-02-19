package frc.robot;

/**
 * listeninng to the statse 
 */
public class RobotStates {
    static double liftSetpoint;
    static double oneEightySetpoint;
    static boolean hasCargo;
    static boolean driveInverted;
    static int heightIndex = 1; // The values can be 0,1,2 for the rocket - low, middle and high height. 3 for cargo ship height And -1 for none of these heights.     
    
    public static void increaseHeight() {
        if(RobotStates.heightIndex<2)
            RobotStates.heightIndex ++;
    }

    public static void decreaseHeight() {
        if(RobotStates.heightIndex>0)
            RobotStates.heightIndex --;
    }

    public static int getHeightIndex(){
        return RobotStates.heightIndex;
    }

    public static void setHeightIndex(int index){ 
        RobotStates.heightIndex = index;
    }

    /**
     * @return the liftSetpoint
     */
    public static double getLiftSetpoint() {
        return liftSetpoint;
    }

    /**
     * @param liftSetpoint the liftSetpoint to set
     */
    public static void setLiftSetpoint(double liftSetpoint) {
        RobotStates.liftSetpoint = liftSetpoint;
    }

    /**
     * @return the oneEightySetpoint
     */
    public static double getOneEightySetpoint() {
        return oneEightySetpoint;
    }

    /**
     * @param oneEightySetpoint the oneEightySetpoint to set
     */
    public static void setOneEightySetpoint(double oneEightySetpoint) {
        RobotStates.oneEightySetpoint = oneEightySetpoint;
    }

    /**
     * @return the hasCargo
     */
    public static boolean isHasCargo() {
        return hasCargo;
    }

    /**
     * @param hasCargo the hasCargo to set
     */
    public static void setHasCargo(boolean hasCargo) {
        RobotStates.hasCargo = hasCargo;
    }

    /**
     * @return the driveInverted
     */
    public static boolean isDriveInverted() {
        return driveInverted;
    }

    /**
     * @param driveInverted the driveInverted to set
     */
    public static void setDriveInverted(boolean driveInverted) {
        RobotStates.driveInverted = driveInverted;
    }
}
