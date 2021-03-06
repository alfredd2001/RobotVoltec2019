/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Robot;
import frc.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.SerialPort.Port;

import com.ctre.phoenix.motorcontrol.ControlMode;

/**
 * Add your docs here.
 */
public class Chassis extends PIDSubsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private static final double TOLERANCE=0.15; 
	private static int direction = 1;  //para invertir los ejes si necesario
	
	public static DifferentialDrive differentialDrive;
	private static WPI_TalonSRX mid;
	public static AHRS ahrs;
	public static double kToleranceDegrees = 10.0;
	public static int ret;
  //////////constructor de la clase/////////////////////
	public Chassis(){
		super("Chassis", RobotMap.KpChassisGyro, RobotMap.KiChassisGyro, RobotMap.KdChassisGyro);
		ahrs = new AHRS(Port.kUSB);
		differentialDrive = new DifferentialDrive(RobotMap.frontLeft, RobotMap.frontRight);
		mid= RobotMap.midMotor;
		setInputRange(-180.0f,  180.0f);
    setOutputRange(-1, 1);
    setAbsoluteTolerance(kToleranceDegrees);
		getPIDController().setContinuous(true);
	}
  //////////////////////////////////////////////////////
  
	////para poner todo en la posicion inicial/////
	public void InitDefaultState() {
		
	}
  //////////////////////////////////////////////
  ////////funcion principal del drive(tanque)//////////////////////////////////////
	public void Main_drive() {
		//lee el control 1
		Joystick joystick=Robot.m_oi.Stick1;
		//lee cada eje de los joystick y les quita el error y mapea
		double throttle = (1.0-joystick.getThrottle()) / 2.0;
		differentialDrive.arcadeDrive(-joystick.getRawAxis(2)*throttle*.7, joystick.getRawAxis(1)*throttle);
		mid.set(joystick.getRawAxis(0)*throttle);

	}
  /////////////////////////////////////////////////////////////////
	public void setPIDValues(){
		getPIDController().setPID(RobotMap.KpChassisGyro, RobotMap.KiChassisGyro, RobotMap.KdChassisGyro);
	}
	public void Stop_drive() {
   	differentialDrive.stopMotor();
  }
  
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
		// setDefaultCommand(new ExampleCommand());
  }

	@Override
	protected double returnPIDInput() {
		ret= (int)(ahrs.getAngle()) % 360;
		
		return ret;
	}

	@Override
	protected void usePIDOutput(double output) {
		differentialDrive.tankDrive(-output, -output);
	}
}
