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
package org.lhind.reservation_mgmt.command.flight.commands;

import java.time.Duration;
import java.time.LocalDateTime;

import org.fuin.cqrs4j.AbstractCommand;
import org.fuin.objects4j.common.Contract;
import org.lhind.reservation_mgmt.common.model.flight.FlightId;

/**
 * Change the departure time and the duration of the flight
 */
public class RescheduleFlightCommand extends AbstractCommand {

  private FlightId flightId;
  private LocalDateTime newDepartureTime;
  private Duration newDuration;

  public RescheduleFlightCommand(FlightId flightId,
      LocalDateTime newDepartureTime, Duration newDuration) {
    super();
    Contract.requireArgNotNull("FLIGHT_ID", flightId);
    Contract.requireArgNotNull("newDepartureTime", newDepartureTime);
    Contract.requireArgNotNull("newDuration", newDuration);
    this.flightId = flightId;
    this.newDepartureTime = newDepartureTime;
    this.newDuration = newDuration;
  }

  /**
   * @return the FLIGHT_ID
   */
  public FlightId getFlightId() {
    return flightId;
  }

  /**
   * @return the newDepartureTime
   */
  public LocalDateTime getNewDepartureTime() {
    return newDepartureTime;
  }

  /**
   * @return the newDuration
   */
  public Duration getNewDuration() {
    return newDuration;
  }
}
