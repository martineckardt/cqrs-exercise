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

import java.time.LocalDateTime;

import org.lhind.reservation_mgmt.common.model.flight.FlightId;

/**
 * DTO of the result of a flight search
 * 
 * @author U519643
 *
 */
public class FlightSearchResultItem {
  private FlightId flightId;
  private LocalDateTime departureTime;
  private LocalDateTime arrivalTime;
  private boolean seatsAvailable;
  private int price;

  public FlightSearchResultItem(FlightId flightId, LocalDateTime departureTime,
      LocalDateTime arrivalTime, boolean seatsAvailable, int price) {
    super();
    this.flightId = flightId;
    this.departureTime = departureTime;
    this.arrivalTime = arrivalTime;
    this.seatsAvailable = seatsAvailable;
    this.price = price;
  }

  /**
   * @return the FLIGHT_ID
   */
  public FlightId getFlightId() {
    return flightId;
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
   * @return the seatsAvailable
   */
  public boolean isSeatsAvailable() {
    return seatsAvailable;
  }

  /**
   * @return the price
   */
  public int getPrice() {
    return price;
  }
}
