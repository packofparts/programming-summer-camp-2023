// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.romi.RomiGyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {
  
  public DifferentialDriveKinematics kinematics = new DifferentialDriveKinematics(14.1 / 100); //kinematics 
  public DifferentialDriveOdometry odometry; // represents the POSE of the robot | POSE = position and rotation

  public static final double kPulsesPerRevolution = 1440.0;
  public static final double kWheelDiameterCM = 6.75;
  public static final double kWheelCircumference = kWheelDiameterCM * Math.PI;

  private Spark leftMotor = new Spark(0);
  private Spark rightMotor = new Spark(1);

  public Encoder leftEncoder = new Encoder(4, 5);
  public Encoder rightEncoder = new Encoder(6, 7);

  public RomiGyro gyro = new RomiGyro();

  /** Creates a new Drivetrain. */
  public Drivetrain() {
    rightMotor.setInverted(true);

    leftEncoder.setDistancePerPulse(kWheelCircumference / kPulsesPerRevolution);
    rightEncoder.setDistancePerPulse(kWheelCircumference / kPulsesPerRevolution);
    leftEncoder.reset();
    rightEncoder.reset();

    //divide by 100 because constructor takes in meters and we've set encoders to return centimeters
    odometry = new DifferentialDriveOdometry(gyro.getRotation2d(), leftEncoder.getDistance()/100, leftEncoder.getDistance()/100);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    //
    odometry.update(gyro.getRotation2d(), leftEncoder.getDistance()/100, rightEncoder.getDistance()/100); //will perform FORWARD KINEMATICS to compute new position
    
    
    SmartDashboard.putNumber("XPos", odometry.getPoseMeters().getX());
    SmartDashboard.putNumber("YPos", odometry.getPoseMeters().getY());
    SmartDashboard.putNumber("Heading", odometry.getPoseMeters().getRotation().getDegrees());
  }

  public void setMotors(double leftPower, double rightPower){
    leftMotor.set(leftPower);
    rightMotor.set(rightPower);
  }

  public double getLeftDistance(){
    return leftEncoder.getDistance() / 2.54; //divide by 2.54 to convert into inches
  }


  public double getRightDistance(){
    return rightEncoder.getDistance() / 2.54; //divide by 2.54 to convert into inches
  }


  //splits a final desired position into multiple points for better PID control
  public double[][] buildTrajectory(double xPos, double yPos){
    double dx = xPos - odometry.getPoseMeters().getX();
    double dy = yPos - odometry.getPoseMeters().getY();
    double distance = Math.sqrt(dx*dx+dy*dy);

    dx = dx / distance;
    dy = dy / distance;

    double[][] trajectory = new double[(int)distance][2];

    for(int i = 0; i < trajectory.length; i++){
      trajectory[i][0] = dx * (i+1);
      trajectory[i][0] = dy * (i+1);
    }
    return trajectory;
  }

}
