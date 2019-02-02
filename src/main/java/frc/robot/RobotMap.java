/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.VictorSP;

import com.ctre.phoenix.motorcontrol.NeutralMode;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

  /***************Numero de los talons*********/
	private static final int FRONT_LEFT_CHASSIS_PORT = 7;
	private static final int FRONT_RIGHT_CHASSIS_PORT = 5;
	private static final int BACK_LEFT_CHASSIS_PORT = 8;
  private static final int BACK_RIGHT_CHASSIS_PORT = 6;
  
  private static final int MID_PORT = 0;
  /********************************************/
  
  /*************chasis************************/
	public static WPI_TalonSRX frontLeft;
	public static WPI_TalonSRX frontRight;
	public static WPI_TalonSRX backLeft;
  public static WPI_TalonSRX backRight;
  
  public static VictorSP midMotor;
  /*******************************************/
  public static double KpChassisGyro = 0.001;
  public static double KiChassisGyro = 0.00;
  public static double KdChassisGyro = 0.00;
	
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;

  public static void init(){
    frontLeft = new WPI_TalonSRX(FRONT_LEFT_CHASSIS_PORT);
		frontRight = new WPI_TalonSRX(FRONT_RIGHT_CHASSIS_PORT);
		backLeft = new WPI_TalonSRX(BACK_LEFT_CHASSIS_PORT);
    backRight = new WPI_TalonSRX(BACK_RIGHT_CHASSIS_PORT);

    midMotor = new VictorSP(MID_PORT);
    
    frontLeft.setInverted(true);
    backLeft.setInverted(true);
    frontRight.setInverted(false);
    backRight.setInverted(false);
    midMotor.setInverted(false);

    frontLeft.setNeutralMode(NeutralMode.Brake);
		frontRight.setNeutralMode(NeutralMode.Brake);
		backLeft.setNeutralMode(NeutralMode.Brake);
    backRight.setNeutralMode(NeutralMode.Brake);

  }
}
