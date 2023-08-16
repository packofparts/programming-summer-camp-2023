// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
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

  public Drivetrain() {
    leftMotor.setInverted(true);

    leftEncoder.setDistancePerPulse(Constants.kWheelCircumference / Constants.kPulsesPerRevolution);
    rightEncoder.setDistancePerPulse(Constants.kWheelCircumference / Constants.kPulsesPerRevolution);
  
    odo = new Odometry();
  }

  @Override
  public void periodic() {
    odo.update(leftEncoder.getDistance(), rightEncoder.getDistance());

    SmartDashboard.putNumber("xPos", odo.getXPos());
    SmartDashboard.putNumber("yPos", odo.getYPos());
    SmartDashboard.putNumber("Radians", odo.getRadians());
  }

  public void setMotors(double left, double right){
    leftMotor.set(left);
    rightMotor.set(right);
  }

  public void drive(double drive, double rotation, boolean norm){

    double leftInputSpeed = drive - .5 * rotation;
    double rightInputSpeed = drive + .5 * rotation;

    if (norm){
      double max = Math.abs(drive) + Math.abs(rotation);

      leftInputSpeed /= max;
      rightInputSpeed /= max;
    }

    this.setMotors(leftInputSpeed, rightInputSpeed);
  }

  public double getLeftDistance(){
    return leftEncoder.getDistance();
  }

  public double getRightDistance(){
    return rightEncoder.getDistance();
  }
}