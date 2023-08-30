// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class Rotate extends CommandBase {
  /** Creates a new Rotate. */

  Drivetrain drive; 
  double degrees = 0;
  public Rotate(Drivetrain drivetrain, double deg) {
    // Use addRequirements() here to declare subsystem dependencies.
    degrees = deg;
    drive = drivetrain;
    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    drive.gyro.reset();
    drive.setMotors(-.1, .1);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drive.setMotors(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(drive.gyro.getAngle() < -degrees){
      return true;
    }else{
      return false;
    }
  }
}
