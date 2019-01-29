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
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.ExampleCommand;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.VictorSP;
import com.ctre.phoenix.motorcontrol.ControlMode;

/**
 * Add your docs here.
 */
public class Chassis extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private static final double TOLERANCE=0.15;  //tolerancia del joystick(quita el error)
	private static final double LIMITER=1;  //Por si quieren limitar la velocidad del drive
	private static int direction = 1;  //para invertir los ejes si necesario
	//private static WPI_TalonSRX[] talons;  //arreglo para guadar los talon del chasis
	private static DifferentialDrive differentialDrive;
	private static SpeedControllerGroup m_left;
	private static SpeedControllerGroup m_right;
	private static VictorSP mid;
  //////////constructor de la clase/////////////////////
	public Chassis(){
		/*talons=new WPI_TalonSRX[]{RobotMap.frontLeft,RobotMap.frontRight,
				 RobotMap.backLeft,RobotMap.backRight};*/
		m_left = new SpeedControllerGroup(RobotMap.frontLeft, RobotMap.backLeft);
		m_right = new SpeedControllerGroup(RobotMap.frontRight, RobotMap.backRight); 
		differentialDrive = new DifferentialDrive(m_left, m_right);
		mid= RobotMap.midMotor;
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
		
		differentialDrive.arcadeDrive(-joystick.getRawAxis(2), joystick.getRawAxis(1));
		mid.set(joystick.getRawAxis(0));

	}
  /////////////////////////////////////////////////////////////////

	public void Stop_drive() {
   	differentialDrive.stopMotor();
  }
  
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
		// setDefaultCommand(new ExampleCommand());
  }  
}
