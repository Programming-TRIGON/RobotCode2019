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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class VisionPIDSource implements PIDSource {
    private VisionTarget target;
    private VisionDirectionType type;
    private NetworkTableEntry visionEntry;
    private final double IMAGE_WIDGH = 2000; // important to know if the target on the middle of the image

    public VisionPIDSource(VisionTarget target, VisionDirectionType type) {
        this.target = target;
        this.type = type;
        NetworkTableInstance inst = NetworkTableInstance.getDefault();
        // TODO: find which table we are using to upload vision target dircetions
        NetworkTable targetTable = inst.getTable("SmartDashboard");
        this.visionEntry = targetTable.getEntry(target.key);
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
        String targetLocation = this.visionEntry.getString("9999");
        if (targetLocation.equals("9999")) {
            return 9999;
        }
        double directionValue = Double.parseDouble(targetLocation.split(" ")[type.key]);

        return (-directionValue / (this.IMAGE_WIDGH / 2)) + 1; // give the pid controller value between -1 and 1
    }

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

    public static enum VisionDirectionType {
        x(0), y(1);
        public int key;

        private VisionDirectionType(int key) {
            this.key = key;
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