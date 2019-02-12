/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Intake extends PIDSubsystem {
  private static WPI_VictorSPX leftIntakeWheel;
  private static WPI_VictorSPX rightIntakeWheel;
  private static WPI_TalonSRX pivoteIntake;
  private static DoubleSolenoid hPiston;

  public Intake() {
    super("Intake", RobotMap.KpIntakePivot, RobotMap.KiIntakePivot, RobotMap.KdIntakePivot);
    leftIntakeWheel = RobotMap.intakeLeft; 
    rightIntakeWheel = RobotMap.intakeRight;
    pivoteIntake = RobotMap.intakePivote;
    hPiston= RobotMap.HPiston;
  }
  /*public void Up_Intake(){
    SmartDashboard.putNumber("Encoder Pivote", pivoteIntake.getSelectedSensorPosition(0));
    if(pivoteIntake.getSelectedSensorPosition()> PUT SENOSR VALUE){
      pivoteIntake.set(ControlMode.PercentOutput, 1);
    }
    pivoteIntake.set(ControlMode.PercentOutput, 1);
  }
  public void Down_Intake(){
    SmartDashboard.putNumber("Encoder Pivote", pivoteIntake.getSelectedSensorPosition(0));
    if(pivoteIntake.getSelectedSensorPosition()< PUT SENOSR VALUE){
      pivoteIntake.set(ControlMode.PercentOutput, 1);
    }
    pivoteIntake.set(ControlMode.PercentOutput, -0.6);
  }*/
  public void Ball_In_Intake(){
    leftIntakeWheel.set(ControlMode.PercentOutput, -1);
    rightIntakeWheel.set(ControlMode.PercentOutput, -1);
  }
  public void Ball_Out_Intake(){
    leftIntakeWheel.set(ControlMode.PercentOutput, 1);
    rightIntakeWheel.set(ControlMode.PercentOutput, 1);
  }
  public void Hatch_Push(){
    hPiston.set(Value.kForward);
  }
  public void Hatch_In(){
    hPiston.set(Value.kReverse);

  }

  public void initDefaultState(){
      Stop_Intake();
  }
  public void Stop_Intake() {
    leftIntakeWheel.set(ControlMode.PercentOutput, 0.0);
    rightIntakeWheel.set(ControlMode.PercentOutput, 0.0);
    pivoteIntake.set(ControlMode.PercentOutput, 0.0);
}

  
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  @Override
  public void periodic() {
    // Put code here to be run every loop

  }

  @Override
  protected double returnPIDInput() {
    return 0;
  }

  @Override
  protected void usePIDOutput(double output) {

  }
 
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

}
