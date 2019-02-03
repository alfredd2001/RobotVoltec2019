/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.TurnToAngle;
import frc.robot.subsystems.Chassis;
import frc.robot.subsystems.Control;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.IntakeCargo;
import frc.robot.subsystems.IntakeHatches;
import frc.robot.subsystems.IntakePivot;
import frc.robot.subsystems.PistonHab;
import frc.robot.subsystems.Vision;
import frc.robot.RobotMap;

public class Robot extends TimedRobot {
  public static OI m_oi;


  /********subsistemas del robot*******************************/
	public static Chassis chassis;
	public static Elevator elevator;
	public static IntakeCargo intakeCargo;
	public static IntakeHatches intakeHatches;
	public static IntakePivot intakePivot;
	public static PistonHab pistonHab;
  public static Vision vision;
  public static Control control;
  public static ExampleSubsystem m_subsystem = new ExampleSubsystem();
	/****************************************************************************************/
  public static TurnToAngle turnToAngle;

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {

    RobotMap.init(); //Codigo agregado por Ian
    
    
   /*******aqui vamos inicializar los subsystemas que se necesite*************************/
		chassis = new Chassis();
		elevator = new Elevator();
		intakeCargo = new IntakeCargo();
		intakeHatches = new IntakeHatches();
		intakePivot = new IntakePivot();
    pistonHab = new PistonHab();
    vision = new Vision();
		control = new Control();
		/*************************************************************************************/

    m_oi = new OI();

    m_chooser.setDefaultOption("Default Auto", new ExampleCommand());
    // chooser.addOption("My Auto", new MyAutoCommand());
    chassis.ahrs.reset();
    SmartDashboard.putNumber("Gyro", chassis.ahrs.getAngle());
    SmartDashboard.putData("Auto mode", m_chooser);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    //m_autonomousCommand = m_chooser.getSelected();
    turnToAngle = new TurnToAngle(0);
    //chassis.ahrs.reset();
    m_autonomousCommand = turnToAngle;
    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */
    SmartDashboard.putNumber("Kp", RobotMap.KpChassisGyro);
    SmartDashboard.putNumber("Ki", RobotMap.KiChassisGyro);
    SmartDashboard.putNumber("Kd", RobotMap.KdChassisGyro);
    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
    SmartDashboard.putNumber("Gyro", chassis.ahrs.getAngle());
    RobotMap.KpChassisGyro = SmartDashboard.getNumber("Kp", RobotMap.KpChassisGyro);
    RobotMap.KiChassisGyro = SmartDashboard.getNumber("Ki", RobotMap.KiChassisGyro);
    RobotMap.KdChassisGyro = SmartDashboard.getNumber("Kd", RobotMap.KdChassisGyro);
    chassis.setPIDValues();

    
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    chassis.Main_drive();
    SmartDashboard.putNumber("Gyro", chassis.ahrs.getAngle());
    RobotMap.KpChassisGyro = SmartDashboard.getNumber("Kp", RobotMap.KpChassisGyro);
    RobotMap.KiChassisGyro = SmartDashboard.getNumber("Ki", RobotMap.KiChassisGyro);
    RobotMap.KdChassisGyro = SmartDashboard.getNumber("Kd", RobotMap.KdChassisGyro);
    chassis.setPIDValues();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
