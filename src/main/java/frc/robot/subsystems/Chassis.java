/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.Robot;
import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;

/**
 * Add your docs here.
 */
public class Chassis extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private static final double TOLERANCE=0.15;  //tolerancia del joystick(quita el error)
	private static final double LIMITER=0.7;  //Por si quieren limitar la velocidad del drive
	private static int direction = 1;  //para invertir los ejes si necesario
	private static WPI_TalonSRX[] talons;  //arreglo para guadar los talon del chasis

  //////////constructor de la clase/////////////////////
	public Chassis(){
		talons=new WPI_TalonSRX[]{RobotMap.frontLeft,RobotMap.frontRight,
			   RobotMap.backLeft,RobotMap.backRight};
	}
	//////////////////////////////////////////////////////

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
  
}
