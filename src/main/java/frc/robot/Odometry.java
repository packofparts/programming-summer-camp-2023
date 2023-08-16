package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Odometry {

    double xPosition = 0;
    double yPosition = 0;
    double accumulatedRadians = 0;

    double prevLeftDistance, prevRightDistance;

    public Odometry(){

    }

    public void reset(){
        xPosition = 0;
        yPosition = 0;
        accumulatedRadians = 0;
    }

    public Odometry(double initX, double initY, double initRot){
        xPosition = initX;
        yPosition = initY;
        accumulatedRadians = initRot;
    }

    public void update(double leftDistance, double rightDistance){
        double dLeft = leftDistance - prevLeftDistance;
        double dRight = rightDistance - prevRightDistance;

        double dCenter = (dRight + dLeft) / 2;

        double dRadians = (dRight - dLeft) / Constants.kDistanceBetweenWheelsCM;

        SmartDashboard.putNumber("dRadians", dRadians);
        
     
        xPosition += dCenter * Math.cos(accumulatedRadians + dRadians / 2);
        yPosition += dCenter * Math.sin(accumulatedRadians + dRadians / 2);
        accumulatedRadians += dRadians;
        

        prevLeftDistance = leftDistance;
        prevRightDistance = rightDistance;
    }

    public double getXPos(){
        return xPosition;
    }

    public double getYPos(){
        return yPosition;
    }

    public double getRadians(){
        return accumulatedRadians;
    }

}
