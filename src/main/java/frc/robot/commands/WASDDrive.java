// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive.WheelSpeeds;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class WASDDrive extends CommandBase {
  DifferentialDriveKinematics kinematics = new DifferentialDriveKinematics(14.1 / 100);
  Joystick joystick = new Joystick(0);
  Drivetrain drive;
  /** Creates a new WASDDrive. */
  public WASDDrive(Drivetrain drivetrain) {
    // Use addRequirements() here to declare subsystem dependencies.
    drive = drivetrain;
    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}
 
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double xInput = joystick.getX();
    double yInput = joystick.getY();

    ChassisSpeeds desiredChassisSpeeds = new ChassisSpeeds(-yInput*0.7, 0, -xInput*2*Math.PI);
    
    //ChassisSpeeds desiredChassisSpeeds = new ChassisSpeeds(-yInput*0.7, 0, pid.calculate(drive.gyro.getRotation2d().getRadians(), 0));
    DifferentialDriveWheelSpeeds speeds = kinematics.toWheelSpeeds(desiredChassisSpeeds);

    drive.setMotors(speeds.leftMetersPerSecond, speeds.rightMetersPerSecond);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
