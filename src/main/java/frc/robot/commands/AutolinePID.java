// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class AutolinePID extends CommandBase {

  PIDController pidController = new PIDController(0.1,0,0);
  Drivetrain drive;
  /** Creates a new AutolinePID. */
  public AutolinePID(Drivetrain drivetrain, double travelDistance) {
    pidController.setSetpoint(travelDistance);
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
    double leftOutput = pidController.calculate(drive.getLeftDistance());
    double rightOutput = pidController.calculate(drive.getRightDistance());
    drive.setMotors(leftOutput, rightOutput);
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
