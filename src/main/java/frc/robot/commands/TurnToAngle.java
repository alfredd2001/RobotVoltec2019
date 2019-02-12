/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class TurnToAngle extends Command {
  double Angle;
  boolean isFinished = false;
  boolean inErrorZone = false;
  int count;
  double sensitivity = 2.7;
  public TurnToAngle(int angle) {
    // Use requires() here to declare subsystem dependencies
    Angle = angle;
    requires(Robot.chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.chassis.setSetpoint(Angle);
    Robot.chassis.enable();

  }

  // Called repeatedlyP when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.chassis.setPIDValues();
    
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    SmartDashboard.putNumber("difference", Angle-Robot.chassis.getSetpoint());
    SmartDashboard.putNumber("setAngle", Angle);
    SmartDashboard.putBoolean("isFinished", Math.abs(Angle-Robot.chassis.getSetpoint())<10.00);
    SmartDashboard.putNumber("Angle", Robot.chassis.ret);
    return Math.abs(Angle-Robot.chassis.ret)<10.00;
    //return false;
    
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.chassis.disable();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
