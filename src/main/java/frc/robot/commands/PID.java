// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.


package frc.robot.commands;


import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;


public class PID extends CommandBase {
  Drivetrain drive;

  double proportional = .05;
  double derivative = 0;

  double endErrorTolerance = 0;

  double pidSetpoint;

  double lastLeftError = 0;
  double lastRightError = 0;
  double lastTime = 0;


  public PID(Drivetrain drivetrain, double travelDistance) {
    pidSetpoint = travelDistance;
    drive = drivetrain;
    addRequirements(drivetrain);
  }


  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    drive.leftEncoder.reset();
    drive.rightEncoder.reset();
  }


  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double changeInTime = Timer.getFPGATimestamp() - lastTime;

    double leftErrorDistance = pidSetpoint - drive.leftEncoder.getDistance();
    double rightErrorDistance =  pidSetpoint - drive.rightEncoder.getDistance();

    double leftErrorRate = (leftErrorDistance - lastLeftError) / changeInTime;
    double rightErrorRate = (rightErrorDistance - lastRightError) / changeInTime;

    double leftSpeed = proportional * leftErrorDistance + derivative * leftErrorRate;
    double rightSpeed = proportional * rightErrorDistance + derivative * rightErrorRate;

    drive.setMotors(leftSpeed, rightSpeed);

    lastLeftError = leftErrorDistance;
    lastRightError = rightErrorDistance;
    lastTime = Timer.getFPGATimestamp();
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






