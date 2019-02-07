/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Compressor;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;

public class RobotMap {

  /*************** Numero de los Talons *********************/
  private static final int TALON_ELEVATOR = 3; // Puerto del talon

  private static final int INTAKE_LEFT_VERSA = 0;
  private static final int INTAKE_RIGHT_VERSA = 1;
  private static final int INTAKE_PIVOT_VERSA = 2;

  private static final int FRONT_LEFT_CHASSIS_PORT = 7;
  private static final int FRONT_RIGHT_CHASSIS_PORT = 5;
  private static final int BACK_LEFT_CHASSIS_PORT = 8;
  private static final int BACK_RIGHT_CHASSIS_PORT = 6;

  private static final int MID_PORT = 4;
  /********************************************/
  /************* chasis ***********************/
  public static PowerDistributionPanel pdp;
	public static Compressor Compressor;

  public static WPI_TalonSRX frontLeft;
  public static WPI_TalonSRX frontRight;
  public static WPI_TalonSRX backLeft;
  public static WPI_TalonSRX backRight;

  public static WPI_TalonSRX midMotor;
  public static final double RAMPDRIVE = 0.3;
  /********************************************/
  /*****************Elevator*****************/
  public static WPI_TalonSRX elevator;
  /******************************************/
  /***************Intake***********************/
  public static WPI_TalonSRX intakeleft;
  public static WPI_TalonSRX intakeright;
  public static WPI_TalonSRX intakepivote;
  /********************************************/
 
  /********************PID CONSTANTS*********/
  public static double KpChassisGyro = 0.03;
  public static double KiChassisGyro = 0.0001;
  public static double KdChassisGyro = 0.01;
  /******************************************/

  /**************** Encoders *****************/
  // Setear encoding type correcto
  public static Encoder ENCODER_ELEVATOR = new Encoder(0, 1, false, EncodingType.k1X);

  // Port numbers, invert counting direction false, and encodingtype
  /*****************************************/
	/********numeros de senales de PCM**************/
	private static final int FORWARD_PISTON1_PORT = 1;
	private static final int REVERSE_PISTON1_PORT = 2;
	private static final int FORWARD_PISTON2_PORT = 3;
	private static final int REVERSE_PISTON2_PORT = 4;
	/***********************************************/
  /***************Potentiometer**************/
  AnalogInput ai = new AnalogInput(1);
  Potentiometer potIntake = new AnalogPotentiometer(ai, 3600);
  /******************************************/
	
  public static void init(){
    /*************chasis*****************************/
    pdp = new PowerDistributionPanel();
    Compressor = new Compressor(0);

    frontLeft = new WPI_TalonSRX(FRONT_LEFT_CHASSIS_PORT);
		frontRight = new WPI_TalonSRX(FRONT_RIGHT_CHASSIS_PORT);
		backLeft = new WPI_TalonSRX(BACK_LEFT_CHASSIS_PORT);
    backRight = new WPI_TalonSRX(BACK_RIGHT_CHASSIS_PORT);
    midMotor = new WPI_TalonSRX(MID_PORT);

    backLeft.follow(frontLeft);
    backRight.follow(frontRight);

    frontLeft.setInverted(true);
    backLeft.setInverted(InvertType.FollowMaster);
    frontRight.setInverted(false);
    backRight.setInverted(InvertType.FollowMaster);
    midMotor.setInverted(false);

    frontLeft.setNeutralMode(NeutralMode.Brake);
		frontRight.setNeutralMode(NeutralMode.Brake);
		backLeft.setNeutralMode(NeutralMode.Brake);
    backRight.setNeutralMode(NeutralMode.Brake);
    midMotor.setNeutralMode(NeutralMode.Brake); 

    //LEFT ENCODER
    frontLeft.configClosedloopRamp(RAMPDRIVE, 0);
    frontLeft.configOpenloopRamp(RAMPDRIVE, 0);
    frontLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
    frontLeft.setSensorPhase(false);
    frontLeft.setSelectedSensorPosition(0, 0, 0); 

    //RIGHT ENCODER
    frontRight.configClosedloopRamp(RAMPDRIVE, 0);
    frontRight.configOpenloopRamp(RAMPDRIVE, 0);
    frontRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
    frontRight.setSensorPhase(false);
    frontRight.setSelectedSensorPosition(0, 0, 0);

    /**********************INTAKE**************************/
    intakeleft = new WPI_TalonSRX(INTAKE_LEFT_VERSA);
    intakeright = new WPI_TalonSRX(INTAKE_RIGHT_VERSA);
    intakepivote = new WPI_TalonSRX(INTAKE_PIVOT_VERSA);

    intakeleft.setInverted(false);
    intakeright.follow(intakeleft);
    intakeright.setInverted(InvertType.OpposeMaster);

    intakepivote.setInverted(false);

    intakeleft.setNeutralMode(NeutralMode.Coast);
    intakeright.setNeutralMode(NeutralMode.Coast);
    intakepivote.setNeutralMode(NeutralMode.Brake);

    /************************ELEVATOR************************/
    elevator = new WPI_TalonSRX(TALON_ELEVATOR);
    elevator.setNeutralMode(NeutralMode.Brake);
    elevator.configClosedloopRamp(RAMPDRIVE, 0);
    elevator.configOpenloopRamp(RAMPDRIVE, 0);
    elevator.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
    elevator.setSensorPhase(false);
    elevator.setSelectedSensorPosition(0, 0, 0);
  }
}
