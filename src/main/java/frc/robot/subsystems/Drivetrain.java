// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.romi.RomiGyro;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {
  
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
    // leftMotor.setInverted(true);


    leftEncoder.setDistancePerPulse(kWheelCircumference / kPulsesPerRevolution);
    rightEncoder.setDistancePerPulse(kWheelCircumference / kPulsesPerRevolution);
    leftEncoder.reset();
    rightEncoder.reset();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setMotors(double leftPower, double rightPower){
    leftMotor.set(leftPower);
    rightMotor.set(rightPower);
  }

  public double getLeftDistance(){
    return leftEncoder.getDistance() / 2.54;
  }


  public double getRightDistance(){
    return rightEncoder.getDistance() / 2.54;
  }


}
