/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.Intake_AngleDown;
import frc.robot.commands.Intake_AngleUp;
import frc.robot.commands.TurnToAngle;
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  public Joystick Stick1; //declara un joystick
  public Joystick Stick2; //declara un joystick

  public JoystickButton LogitechB1;
  public JoystickButton LogitechB2; //declara un boton de joystick LT (IO - 6)
  public JoystickButton LogitechB3;
  public JoystickButton LogitechB4;

  
  public OI(){

    ////////////los dos jostick a utilizar(Joystick1 = Logitech, Joystick2 = xboxone)////////////////
    Stick1 = new Joystick(0);  //une el josytick al objeto stick principal
    Stick2 = new Joystick(1);  //une el josytick al objeto
    
    LogitechB1 = new JoystickButton(Stick1, 6);    
    LogitechB2 = new JoystickButton(Stick1, 7);

    LogitechB1.whenPressed(new Intake_AngleUp());
    LogitechB2.whenPressed(new Intake_AngleDown());
    /*LogitechPOVUp = new JoystickButton(Stick1, 8);
    LogitechPOVLeft = new JoystickButton(Stick1, 9);

    LogitechPOVDown.whenReleased(new TurnToAngle(180)); 
    LogitechPOVUp.whenReleased(new TurnToAngle(0));
    LogitechPOVRight.whenReleased(new TurnToAngle(90));
    LogitechPOVLeft.whenReleased(new TurnToAngle(270));
*/

  }

  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());
}
