/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.Robot;
import frc.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class Elevator extends PIDSubsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private WPI_TalonSRX elevatorMotor;
  private static double Kp = 0.001;
  private static double Ki = 0.0;
  private static double Kd = 0.0;
  private static double tolerance = 10.00;
  boolean override = false;
  
  public Elevator(){
    super("Elevator", Kp, Ki, Kd);
    elevatorMotor = RobotMap.lift;
    setOutputRange(-1, 1);
    setAbsoluteTolerance(tolerance);
    getPIDController().setContinuous(true);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void Stop_Elevador() {
    elevatorMotor.set(ControlMode.PercentOutput, 0.0);
  }

  public void elevate(double input) {

  }

  @Override
  protected double returnPIDInput() {
    return elevatorMotor.getSelectedSensorPosition();
  }

  @Override
  protected void usePIDOutput(double output) {
    elevatorMotor.set(ControlMode.PercentOutput, output);
  }
}
