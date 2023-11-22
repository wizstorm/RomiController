// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.RomiDrivetrain;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final RomiDrivetrain drive = new RomiDrivetrain();
  private XboxController controller = new XboxController (0);
  

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link edu.wpi.first.wpilibj.GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {}

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
  public void Gooo(double x, double y){
    drive.go(x,y);
  }
  public void goWithControl(){
    double x;
    double y;
    x=controller.getLeftX();
    y=controller.getLeftY();
    
    //turn
    if (controller.getLeftBumperReleased()){
      drive.turn90(1);
    }
    if (controller.getRightBumperReleased()){
      drive.turn90(-1);
    }
    
    drive.go(((-y/2)-(x/2))*2,((-y/2)+(x/2))*2);

    
  }
  public void vgo(){
    double xv;
    double yv;
    xv=controller.getRightY();
    yv=controller.getLeftY();
    //turn
    if (controller.getLeftBumperReleased()){
      drive.turn90(1);
    }
    if (controller.getRightBumperReleased()){
      drive.turn90(-1);
    }
    drive.go(-xv,-yv);
    
  }
  public void go_square(){
    
    drive.go_in_Square(12.5);
  }
  
  public void turn(){
    drive.turn90();
  }
  public void go_for(double distance){
    drive.go_for(distance);
  }



}
