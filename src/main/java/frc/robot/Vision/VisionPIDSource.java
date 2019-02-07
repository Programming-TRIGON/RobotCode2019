/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Vision;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class VisionPIDSource implements PIDSource {
    private VisionDirectionType type;
    private NetworkTableEntry visionEntry;
    private double imageWidth; // important to know if the target on the middle of the image

    public VisionPIDSource(VisionTarget target, VisionDirectionType type) {
        this.type = type;
        NetworkTableInstance inst = NetworkTableInstance.getDefault();
        NetworkTable targetTable = inst.getTable("SmartDashboard");
        this.visionEntry = targetTable.getEntry(target.key);
        imageWidth = type.width;
    }

    @Override
    public void setPIDSourceType(PIDSourceType pidSource) {
    }

    @Override
    public PIDSourceType getPIDSourceType() {
        return PIDSourceType.kDisplacement;
    }

    @Override
    public double pidGet() {
        if (this.visionEntry == null) {
            return 9999;
        }
        //gets directions from networktable.
        String targetLocation = this.visionEntry.getString("9999");
        if (targetLocation.equals("9999")) {
            //If no target is found, 9999 will be returned.
            return 9999;
        }
        //extracts the x/y direction from the targetLocation 
        double directionValue = Double.parseDouble(targetLocation.split(" ")[type.key]);
        return (-directionValue / (this.imageWidth / 2)) + 1; // give the pid controller value between -1 and 1
    }
     /**
     * types of targets that the robot can track 
     */
    public static enum VisionTarget {

        kHatch("HatchDirection") {
            public String toString() {
                return "hatch";
            }
        },
        kCargo("CargoDirection") {
            public String toString() {
                return "cargo";
            }
        },
        kReflector("ReflectorDirection") {
            public String toString() {
                return "reflector";
            }
        },
        kLine("LineDirection") {
            public String toString() {
                return "line";
            }
        };
        public String key;

        private VisionTarget(String key) {
            this.key = key;
        }
    }
    /**
     * VisionDirectionType is either x or y.
     */
    public static enum VisionDirectionType {
        x(0,320), y(1,240);
        public int key;
        public int width;//the width/height

        private VisionDirectionType(int key,int width) {
            this.key = key;
            this.width = width;
        }
    }

    /**
     * 
     * @return true if the image has been updated recently, otherwise it returns
     *         false
     */
    public boolean isUpdated() {
        return !this.visionEntry.getString("9999").equals("9999");
    }
}