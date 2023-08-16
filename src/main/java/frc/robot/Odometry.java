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

    public double[] inverseKinematics(double desiredX, double desiredY){

        double dx = desiredX - xPosition;
        double dy = desiredY - yPosition;
        
        double v = Math.sqrt(dx * dx + dy * dy);

        
        double w = angleDifference(accumulatedRadians, Math.atan2(dy, dx));
        

        double vL =  (2.0 * 1.0 - w * Constants.kDistanceBetweenWheelsCM * Constants.kTurnFactor) / 2.0;
        double vR = (w * Constants.kDistanceBetweenWheelsCM * Constants.kTurnFactor + 2.0 * 1.0) / 2.0;

        SmartDashboard.putNumber("LeftVel", vL);
        SmartDashboard.putNumber("RightVel", vR);

        return new double[]{vL, vR};
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

    public static double angleDifference( double angle1, double angle2 )
    {
        double diff = ( angle2 - angle1 + Math.PI ) % (2*Math.PI) - Math.PI;
        return diff < -Math.PI ? diff + 2*Math.PI : diff;
    }

}
