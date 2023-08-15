// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase{

  private Spark leftMotor = new Spark(0);
  private Spark rightMotor = new Spark(1);


  public Drivetrain() {
    leftMotor.setInverted(true);
  }

  @Override
  public void periodic() {

  }

  public void setMotors(double left, double right){
    leftMotor.set(left);
    rightMotor.set(right);
  }

  public void drive(double drive, double rotation){

    double leftSpeed = drive - .5 * rotation;
    double rightSpeed = drive + .5 * rotation;

    double max = Math.abs(drive) + Math.abs(rotation);

    leftSpeed /= max;
    rightSpeed /= max;

    this.setMotors(leftSpeed, rightSpeed);
  }

}
