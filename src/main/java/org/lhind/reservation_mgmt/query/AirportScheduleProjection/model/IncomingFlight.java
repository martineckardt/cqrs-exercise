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
package org.lhind.reservation_mgmt.query.AirportScheduleProjection.model;

import java.time.LocalDateTime;

import org.lhind.reservation_mgmt.common.model.flight.FlightId;

/**
 * @author U519643
 *
 */
public class IncomingFlight {
  private FlightId flightId;
  private LocalDateTime arrivalTime;
  private String originCode;

  public IncomingFlight(FlightId flightId, LocalDateTime arrivalTime,
      String originCode) {
    super();
    this.flightId = flightId;
    this.arrivalTime = arrivalTime;
    this.originCode = originCode;
  }

  /**
   * @return the arrivalTime
   */
  public LocalDateTime getArrivalTime() {
    return arrivalTime;
  }

  /**
   * @param arrivalTime
   *          the arrivalTime to set
   */
  public void setArrivalTime(LocalDateTime arrivalTime) {
    this.arrivalTime = arrivalTime;
  }

  /**
   * @return the flightId
   */
  public FlightId getFlightId() {
    return flightId;
  }

  /**
   * @return the originCode
   */
  public String getOriginCode() {
    return originCode;
  }
}
