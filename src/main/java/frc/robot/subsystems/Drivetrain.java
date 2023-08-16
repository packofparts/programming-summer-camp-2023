// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.lang.reflect.Field;
import java.text.FieldPosition;

import edu.wpi.first.hal.simulation.RoboRioDataJNI;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.FieldObject2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Odometry;

public class Drivetrain extends SubsystemBase{


  private Spark leftMotor = new Spark(0);
  private Spark rightMotor = new Spark(1);

  private Encoder leftEncoder = new Encoder(4, 5);
  private Encoder rightEncoder = new Encoder(6, 7);



  Odometry odo;

  Field2d field;
  

  private double desiredX = 100;
  private double desiredY = 100;

  //sim stuff dont really need for class but can show maybe idk
  public Timer simTimer;

  private double prevTime = 0;
  private double simPeriodicDelta = 0;

  private double leftSimDistance = 0;
  private double rightSimDistance = 0;


  public Drivetrain() {

    
    leftMotor.setInverted(true);

    leftEncoder.setDistancePerPulse(Constants.kWheelCircumference / Constants.kPulsesPerRevolution);
    rightEncoder.setDistancePerPulse(Constants.kWheelCircumference / Constants.kPulsesPerRevolution);
    leftEncoder.reset();
    rightEncoder.reset();
    

    odo = new Odometry();
    field = new Field2d();
    field.getObject("Marker").setPose(desiredX/100, desiredY/100, new Rotation2d());

    SmartDashboard.putData("Field", field);

    simTimer = new Timer();
    simTimer.start();
  }

  public void reset(){
    leftEncoder.reset();
    rightEncoder.reset();
    odo.reset();

    //sim reset too
    leftSimDistance = 0;
    rightSimDistance = 0;
    simTimer.reset();
    prevTime = 0;
    simPeriodicDelta = 0;
  }

  @Override
  public void periodic() {

    simPeriodicDelta = simTimer.get() - prevTime;
    

    odo.update(getLeftDistance(), getRightDistance());
    field.setRobotPose(odo.getXPos()/100, odo.getYPos()/100, Rotation2d.fromRadians(odo.getRadians()));


    //automatic test for ik | random path following
    double[] speeds = odo.inverseKinematics(desiredX, desiredY); //topright corner of sim
    speeds[0] /= Math.max(Math.abs(speeds[0]), Math.abs(speeds[1]));
    speeds[1] /= Math.max(Math.abs(speeds[0]), Math.abs(speeds[1]));

    leftSimDistance += speeds[0] * Constants.kMaxSpeedPerSecCM * simPeriodicDelta;
    rightSimDistance += speeds[1] * Constants.kMaxSpeedPerSecCM * simPeriodicDelta;

    if(Math.abs(odo.getXPos() - desiredX) < 5 && Math.abs(odo.getYPos() - desiredY) < 5){
      desiredX = Math.random() * 100;
      desiredY = Math.random() * 100;

      field.getObject("Marker").setPose(desiredX/100, desiredY/100, new Rotation2d());
    }



    SmartDashboard.putNumber("xPos", odo.getXPos());
    SmartDashboard.putNumber("yPos", odo.getYPos());
    SmartDashboard.putNumber("Radians", odo.getRadians());

    SmartDashboard.putNumber("LeftDistance", getLeftDistance());
    SmartDashboard.putNumber("RightDistance", getRightDistance());

    prevTime = simTimer.get();
  }

  public void setMotors(double left, double right){
    leftMotor.set(left);
    rightMotor.set(right);
  }

  public void drive(double drive, double rotation, boolean norm){

    double leftInput = drive - .5 * rotation;
    double rightInput = drive + .5 * rotation;

    if (norm){
      double max = Math.abs(drive) + Math.abs(rotation);

      leftInput /= max;
      rightInput /= max;
    }
    
    if (Constants.kSimulate){
      leftSimDistance += leftInput * Constants.kMaxSpeedPerSecCM * simPeriodicDelta;
      rightSimDistance += rightInput * Constants.kMaxSpeedPerSecCM * simPeriodicDelta;
      return;
    }

    this.setMotors(leftInput, rightInput);
  }

  public double getLeftDistance(){
    return Constants.kSimulate ? leftSimDistance : leftEncoder.getDistance();
  }

  public double getRightDistance(){
    return Constants.kSimulate ? rightSimDistance : rightEncoder.getDistance();
  }
}
