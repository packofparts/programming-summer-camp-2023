// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class MoveTo extends CommandBase {
  Drivetrain drive;
  
  PIDController pospid = new PIDController(1.5,0,0);
  PIDController rotpid = new PIDController(1.5, 0, 0);
  double posTolerance = .4;
  double rotTolerance = .1;


  double xPos, yPos;

  double[][] trajectory; 
  int trajIndex = 0;

  /** Creates a new MoveTo. */
  public MoveTo(Drivetrain drivetrain, double x, double y) {
    this.xPos = x;
    this.yPos = y;

    drive = drivetrain;
    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    drive.leftEncoder.reset();
    drive.rightEncoder.reset();
    drive.gyro.reset();
    drive.odometry.resetPosition(drive.gyro.getRotation2d(), 0, 0, new Pose2d());

    trajectory = drive.buildTrajectory(xPos, yPos);
    
  }
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    double forwardOutput = pospid.calculate(drive.odometry.getPoseMeters().getX(), trajectory[trajIndex][0]);
    double moveAngle = Math.atan2(trajectory[trajIndex][1]-drive.odometry.getPoseMeters().getY(), trajectory[trajIndex][0]-drive.odometry.getPoseMeters().getX());
    double rotOutput = rotpid.calculate(drive.odometry.getPoseMeters().getRotation().getRadians(), moveAngle);

    SmartDashboard.putNumber("xOutput", forwardOutput);
    SmartDashboard.putNumber("RotOutput", rotOutput);

    ChassisSpeeds chassisSpeeds = new ChassisSpeeds(forwardOutput, 0, rotOutput);
    DifferentialDriveWheelSpeeds speeds = drive.kinematics.toWheelSpeeds(chassisSpeeds);

    drive.setMotors(speeds.leftMetersPerSecond, speeds.rightMetersPerSecond);

    if(Math.abs(trajectory[trajIndex][0]-drive.odometry.getPoseMeters().getX()) < posTolerance && Math.abs(moveAngle-drive.odometry.getPoseMeters().getRotation().getRadians()) < rotTolerance){
      trajIndex++;
    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return trajIndex >= trajectory.length;
  }
}
