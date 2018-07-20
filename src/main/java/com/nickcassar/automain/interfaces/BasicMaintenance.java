/**
 * This interface defines basic maintenance tasks shared by all vehicle types
 */
package com.nickcassar.automain.interfaces;

public interface BasicMaintenance {

  // Tire rotation
  public void rotateTires(double cost, double time);

  // Tire change
  public void changeTires(double cost, double time);

  // Brake change
  public void changeBrakes(double cost, double time);

  // Autobody repair
  public void repairBody(double cost, double time);
}