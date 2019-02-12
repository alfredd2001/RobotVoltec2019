/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Control extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  Compressor compressor = RobotMap.Compressor;
  public Control() {
    compressor = RobotMap.Compressor;
    addChild("Compressor",compressor);        
}
  @Override
  public void initDefaultCommand() {

  }
  
  public void turnCompressorOn() {
    compressor.setClosedLoopControl(true);
  }
  
  public void turnCompressorOff() {
    compressor.setClosedLoopControl(false);
  }

  
  
}
