/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.Robot;
import frc.robot.RobotMap;

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
	private static WPI_TalonSRX[] talons;  //arreglo para guadar los talon del chasis

  //////////constructor de la clase/////////////////////
	public Chassis(){
		talons=new WPI_TalonSRX[]{RobotMap.frontLeft,RobotMap.frontRight,
			   RobotMap.backLeft,RobotMap.backRight};
	}
  //////////////////////////////////////////////////////
  
	////para poner todo en la posicion inicial/////
	public void InitDefaultState() {
		Stop_drive();
	}
  //////////////////////////////////////////////
  ////////funcion principal del drive(tanque)//////////////////////////////////////
	public void Main_drive() {
		//lee el control 1
		Joystick joystick=Robot.m_oi.Stick1;
		//lee cada eje de los joystick y les quita el error y mapea
		double LeftStick = mapDoubleT(joystick.getRawAxis(1),TOLERANCE,1,0,1)*direction, 
		RightStick = mapDoubleT(joystick.getRawAxis(3),TOLERANCE,1,0,1)*direction;
	
		talons[0].set(ControlMode.PercentOutput,LeftStick*LIMITER);
		talons[1].set(ControlMode.PercentOutput,RightStick*LIMITER);
		talons[2].set(ControlMode.PercentOutput,LeftStick*LIMITER);
		talons[3].set(ControlMode.PercentOutput,RightStick*LIMITER);	
	}
  /////////////////////////////////////////////////////////////////

    
  //////funcion para detener el drive///////////
  public void Stop_drive() {
    for(WPI_TalonSRX talon:talons) {
      talon.set(ControlMode.PercentOutput, 0.0);
    }
  }
    ///////////////////////////////////////////////

  ///////////////////////////////////////////////

	///para mapear un numero de un rango a otro rango
	private double map(double x, double in_min, double in_max, double out_min, double out_max)
	{
		return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
	}
	
	private double mapDoubleT(double x, double in_min, double in_max, double out_min, double out_max)
	{
		if(Math.abs(x)<TOLERANCE) {
			return 0;
		}
		if(x<0){
			return map(x,-in_min,-in_max,-out_min,-out_max);
		}
		return map(x,in_min,in_max,out_min,out_max);
	}
	///////////////////////////////////////////////////////////////////
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }  
}
