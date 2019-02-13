/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;

public class RobotMap {

 /*************** Numero de los Talons y Victors *********************/
 private static final int LIFT_MOTOR = 6; // Puerto del talon
 private static final int LIFT_MOTOR_FOLLOWER = 7;

 private static final int INTAKE_LEFT_WHEEL = 8;
 private static final int INTAKE_RIGHT_WHEEL = 9;
 private static final int INTAKE_PIVOT = 10;

 private static final int FRONT_LEFT_CHASSIS_PORT = 1;
 private static final int FRONT_RIGHT_CHASSIS_PORT = 2;
 private static final int BACK_LEFT_CHASSIS_PORT = 3;
 private static final int BACK_RIGHT_CHASSIS_PORT = 4;

 private static final int MID_PORT = 5;
 /********************************************/
 /************* chasis ***********************/
 public static PowerDistributionPanel pdp;
 public static Compressor Compressor;

 public static WPI_TalonSRX frontLeft;
 public static WPI_TalonSRX frontRight;
 public static WPI_VictorSPX backLeft;
 public static WPI_VictorSPX backRight;
 public static WPI_TalonSRX midMotor;

 public static final int FRONT_HAB_PISTON_ACTIVATE = 0;
public static final int FRONT_HAB_PISTON_DEACTIVATE = 1;
public static final int REAR_HAB_PISTON_ACTIVATE = 2;
 public static final int REAR_HAB_PISTON_DEACTIVATE = 3;
 
 public static DoubleSolenoid frontPiston;
 public static DoubleSolenoid rearPiston;
 /********************************************/
 /*****************Elevator*****************/
 public static WPI_TalonSRX lift;
 public static WPI_VictorSPX liftFollower;
 /******************************************/
 /***************Intake***********************/
 public static WPI_VictorSPX intakeLeft;
 public static WPI_VictorSPX intakeRight;
 public static WPI_TalonSRX intakePivote;

 public static final int PISTON_H_ACTIVATE = 4;
 public static final int PISTON_H_DEACTIVATE = 5;
 public static DoubleSolenoid HPiston;
 
  /********************PID CONSTANTS*********/
  public static double KpChassisGyro = 0.03;
  public static double KiChassisGyro = 0.0001;
  public static double KdChassisGyro = 0.01;

  public static double KpIntakePivot = 0.001;
  public static double KiIntakePivot = 0.0;
  public static double KdIntakePivot = 0.0;

  public static double KpElevator = 0.001;
  public static double KiElevator = 0.0;
  public static double KdElevator = 0.0;
  /******************************************/
  /****************ELEVATOR LEVELS VALUE********/
  public static int HatchLevel1 = 0;
  public static int HatchLevel2 = 0;
  public static int HatchLevel3 = 0;
  public static int CargoLevel1 = 0;
  public static int CargoLevel2 = 0;
  public static int CargoLevel3 = 0;
  public static int CargoToCargoShip = 0;
  public static int PivotUp = 0;
  public static int PivotDown = 0;
  /*********************************************/
  public static void init(){
    /*************chasis*****************************/
    pdp = new PowerDistributionPanel();
    Compressor = new Compressor(0);

    frontLeft = new WPI_TalonSRX(FRONT_LEFT_CHASSIS_PORT);		
    frontRight = new WPI_TalonSRX(FRONT_RIGHT_CHASSIS_PORT);
    backLeft = new WPI_VictorSPX(BACK_LEFT_CHASSIS_PORT);
    backRight = new WPI_VictorSPX(BACK_RIGHT_CHASSIS_PORT);
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
    
    frontPiston = new DoubleSolenoid(FRONT_HAB_PISTON_ACTIVATE, FRONT_HAB_PISTON_DEACTIVATE);
    rearPiston = new DoubleSolenoid(REAR_HAB_PISTON_ACTIVATE, REAR_HAB_PISTON_DEACTIVATE);

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
    
    frontPiston = new DoubleSolenoid(FRONT_HAB_PISTON_ACTIVATE, FRONT_HAB_PISTON_DEACTIVATE);
    rearPiston = new DoubleSolenoid(REAR_HAB_PISTON_ACTIVATE, REAR_HAB_PISTON_DEACTIVATE);
    
    /**********************INTAKE**************************/
    intakeLeft = new WPI_VictorSPX(INTAKE_LEFT_WHEEL);
    intakeRight = new WPI_VictorSPX(INTAKE_RIGHT_WHEEL);
    intakePivote = new WPI_TalonSRX(INTAKE_PIVOT);

    intakeLeft.setInverted(false);
    intakeRight.setInverted(true);
    intakePivote.setInverted(false);

    intakeLeft.setNeutralMode(NeutralMode.Coast);
    intakeRight.setNeutralMode(NeutralMode.Coast);
    intakePivote.setNeutralMode(NeutralMode.Brake);

    HPiston = new DoubleSolenoid(PISTON_H_ACTIVATE, PISTON_H_DEACTIVATE);

    /************************ELEVATOR************************/
    lift = new WPI_TalonSRX(LIFT_MOTOR);
    liftFollower = new WPI_VictorSPX(LIFT_MOTOR_FOLLOWER);

    liftFollower.follow(lift);

    lift.setInverted(false);
    liftFollower.setInverted(InvertType.FollowMaster);

    lift.setNeutralMode(NeutralMode.Brake);
    liftFollower.setNeutralMode(NeutralMode.Brake);

  }
}
