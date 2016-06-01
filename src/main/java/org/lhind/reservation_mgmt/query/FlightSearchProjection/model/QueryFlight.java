/**
 * Copyright (C) 2015 Lufthansa Industry Solutions AS GmbH. All rights reserved. 
 * <http://www.lhind.org/>
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 3 of the License, or (at your option) any
 * later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library. If not, see <http://www.gnu.org/licenses/>.
 */
package org.lhind.reservation_mgmt.query.FlightSearchProjection.model;

import java.time.Duration;
import java.time.LocalDateTime;

import org.lhind.reservation_mgmt.common.model.flight.FlightId;
import org.lhind.reservation_mgmt.common.model.flight.Route;

/**
 * @author U519643
 *
 */
public class QueryFlight {

  private FlightId flightId;
  private Route route;
  private LocalDateTime departureTime;
  private LocalDateTime arrivalTime;
  private int numberOfAvailableSeats;

  public QueryFlight(FlightId flightId, Route route,
      LocalDateTime departureTime, LocalDateTime arrivalTime,
      int numberOfAvailableSeats) {
    super();
    this.flightId = flightId;
    this.route = route;
    this.departureTime = departureTime;
    this.arrivalTime = arrivalTime;
    this.numberOfAvailableSeats = numberOfAvailableSeats;
  }

  /**
   * Adds the amount to the available seats (more seats are available)
   * 
   * @param amount
   */
  public void addToAvailableSeats(int amount) {
    numberOfAvailableSeats += amount;
  }

  /**
   * Substracts the amount from the available seats (less seats are available)
   * 
   * Note: the read model is not concerned, if the numberOfAvailableSeats is
   * less than 0. Consistency is the responsibility of the command model.
   * 
   * @param amount
   */
  public void substractFromAvailableSeats(int amount) {
    numberOfAvailableSeats -= amount;
  }

  /**
   * @return the FLIGHT_ID
   */
  public FlightId getFlightId() {
    return flightId;
  }

  /**
   * @return the route
   */
  public Route getRoute() {
    return route;
  }

  /**
   * @return the DEPARTURE_TIME
   */
  public LocalDateTime getDepartureTime() {
    return departureTime;
  }

  /**
   * @return the ARRIVAL_TIME
   */
  public LocalDateTime getArrivalTime() {
    return arrivalTime;
  }

  /**
   * @return the numberOfAvailableSeats
   */
  public int getNumberOfAvailableSeats() {
    return numberOfAvailableSeats;
  }

  /**
   * @param newDepartureTime
   * @param newDuration
   */
  public void reschedule(LocalDateTime newDepartureTime, Duration newDuration) {
    arrivalTime = newDepartureTime.plus(newDuration);
  }
}
