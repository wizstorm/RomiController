// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import java.util.concurrent.TimeUnit;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class RomiDrivetrain extends SubsystemBase {
  PIDController pid = new PIDController(.123, 0.00001, .01);
  //PIDController pid = new PIDController(.5, 0, 0);
  private static final double kCountsPerRevolution = 1440.0;
  private static final double kWheelDiameterInch = 2.75591; // 70 mm

  // The Romi has the left and right motors set to
  // PWM channels 0 and 1 respectively
  
  private final Spark m_leftMotor = new Spark(0);
  
  private final Spark m_rightMotor = new Spark(1);

  // The Romi has onboard encoders that are hardcoded
  // to use DIO pins 4/5 and 6/7 for the left and right
  private final Encoder m_leftEncoder = new Encoder(4, 5);
  private final Encoder m_rightEncoder = new Encoder(6, 7);

  // Set up the differential drive controller
  private final DifferentialDrive m_diffDrive = new DifferentialDrive(m_leftMotor, m_rightMotor);

  /** Creates a new RomiDrivetrain. */
  public RomiDrivetrain() {
    // Use inches as unit for encoder distances
    m_leftEncoder.setDistancePerPulse((Math.PI * kWheelDiameterInch) / kCountsPerRevolution);
    m_rightEncoder.setDistancePerPulse((Math.PI * kWheelDiameterInch) / kCountsPerRevolution);
    resetEncoders();

    // Invert right side since motor is flipped
    m_rightMotor.setInverted(true);
  }

  public void arcadeDrive(double xaxisSpeed, double zaxisRotate) {
    m_diffDrive.arcadeDrive(xaxisSpeed, zaxisRotate);
  }

  public void resetEncoders() {
    m_leftEncoder.reset();
    m_rightEncoder.reset();
  }

  public double getLeftDistanceInch() {
    return m_leftEncoder.getDistance();
  }

  public double getRightDistanceInch() {
    return m_rightEncoder.getDistance();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  public void go(double x, double y){
    m_rightMotor.setInverted(true);
    m_leftMotor.setInverted(false);
    m_rightMotor.set(x);
    m_leftMotor.set(y);
    System.out.println("In Here");

  }

  public void go_in_Square(double sideLength){
    
    
    resetEncoders();
    while(getRightDistanceInch() < sideLength){
      double speed=pid.calculate(m_rightEncoder.getDistance(), sideLength);
      go(speed ,speed);
    }

    go(0,0);
    resetEncoders();
    //Thread.sleep(1);
    turn90();
    resetEncoders();

    
    
  }
  public void turn90(){
    resetEncoders();
    while (getRightDistanceInch() <6.75){
      go(1,0);
    }
  }
  public void turn90(int go_left){
    resetEncoders();
    if (go_left ==1){
      while (getRightDistanceInch() <6.75){
        go(1,0);
      }
    }
    if (go_left ==-1){
      double speed=pid.calculate(m_rightEncoder.getDistance(), 6.75);
      while (getLeftDistanceInch() <6.75){
        go(0,1);
      }
      
    }
    go(0,0);
    
  }
  public void go_for(double distance){
    resetEncoders();
    double s=0;
    while(true){
      
      double speed=pid.calculate(m_rightEncoder.getDistance(), distance);
      go(speed ,speed);
      if (s!=speed){
        System.out.println(m_rightEncoder.getDistance());
      }
      s=speed;
      
    }
  }

  
}
