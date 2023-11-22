// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.RomiDrivetrain;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  //private final RomiDrivetrain drive = new RomiDrivetrain();

  private RobotContainer m_robotContainer;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();

  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
    /* 
    ExampleCommand command = new ExampleCommand(m_robotContainer.drive, 5, 5, 0.5);

    SequentialCommandGroup square = new SequentialCommandGroup(
      new ExampleCommand(m_robotContainer.drive, 5, 5, 0.5),
      new ExampleCommand(m_robotContainer.drive, 0, 0.52, 0.5),
      new ExampleCommand(m_robotContainer.drive, 5, 5, 0.5),
      new ExampleCommand(m_robotContainer.drive, 0, 0.52, 0.5),
      new ExampleCommand(m_robotContainer.drive, 5, 5, 0.5),
      new ExampleCommand(m_robotContainer.drive, 0, 0.52, 0.5),
      new ExampleCommand(m_robotContainer.drive, 5, 5, 0.5),
      new ExampleCommand(m_robotContainer.drive, 0, 0.52, 0.5)
    );

    square.schedule();
    //m_robotContainer.turn();*/
    //m_robotContainer.turn();
    
    m_robotContainer.go_square();
    m_robotContainer.go_square();
    m_robotContainer.go_square();
    m_robotContainer.go_square();
    

  }
  

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    

  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
      m_autonomousCommand = null;
      m_robotContainer.Gooo(0, 0);
    }
    
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    
    // comment out mode that you don't one
    
    //first controller binds control to 1 joystick
    m_robotContainer.goWithControl();

    //first controller binds control to 2 joysticks (Sugested by V)
    //m_robotContainer.vgo();
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
    m_robotContainer.go_for(12);
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {
    //m_robotContainer.Gooo(1, 1);
  }
}
