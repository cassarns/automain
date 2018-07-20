/**
 * This interface defines methods for maintenance on an internal combustion (IC) vehicle
 */
package com.nickcassar.automain.interfaces;

public interface ICMaintenance {

  public void changeOil(double cost, double time);

  public void changePlugs(double cost, double time);

  public void changeTransmissionFluid(double cost, double time);

  public void changeBattery(double cost, double time);

}