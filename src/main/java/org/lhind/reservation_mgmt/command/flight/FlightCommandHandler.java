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
package org.lhind.reservation_mgmt.command.flight;

import org.fuin.ddd4j.ddd.AggregateDeletedException;
import org.fuin.ddd4j.ddd.AggregateNotFoundException;
import org.fuin.ddd4j.ddd.AggregateVersionConflictException;
import org.lhind.reservation_mgmt.command.AbstractCommandHandler;
import org.lhind.reservation_mgmt.command.flight.commands.CancelFlightCommand;
import org.lhind.reservation_mgmt.command.flight.commands.MakeReservationCommand;
import org.lhind.reservation_mgmt.command.flight.commands.RescheduleFlightCommand;
import org.lhind.reservation_mgmt.command.flight.commands.ScheduleFlightCommand;
import org.lhind.reservation_mgmt.command.flight.exceptions.DateInPastException;

/**
 * Handles all the commands issued by a client. May be implemented as a REST-API
 *
 */
public class FlightCommandHandler extends AbstractCommandHandler {

  private FlightRepository flightRepository;

  public void handle(ScheduleFlightCommand cmd) throws DateInPastException,
      AggregateVersionConflictException, AggregateNotFoundException,
      AggregateDeletedException {
    // Check Authorization
    checkAuthorization(cmd);

    // Logging
    LOG.info("ScheduleFlightCommand for flight " + cmd.getFlightId()
        + " issued");

    // Retrieving aggregate instance
    Flight flight = flightRepository.create();

    // Invoking method of aggregate
    flight.schedule(cmd.getFlightId(), cmd.getDepartureTime(),
        cmd.getDuration(), cmd.getRoute(), cmd.getCapacity());

    // Persisting aggregate instance
    flightRepository.update(flight, null);
  }

  public void handle(RescheduleFlightCommand cmd) throws DateInPastException,
      AggregateVersionConflictException, AggregateNotFoundException,
      AggregateDeletedException {
    // Check Authorization
    checkAuthorization(cmd);

    // Logging
    LOG.info("MakeReservationCommand for flight " + cmd.getFlightId()
        + " issued");

    // Retrieving aggregate instance
    Flight flight = flightRepository.read(cmd.getFlightId());

    // Invoking method of aggregate
    flight.reschedule(cmd.getNewDepartureTime(), cmd.getNewDuration());

    // Persisting aggregate instance
    flightRepository.update(flight, null);
  }

  public void handle(MakeReservationCommand cmd) {
    /*
     * Exercise: Writing a Command Handler
     */
  }

  public void handle(CancelFlightCommand cmd)
      throws AggregateNotFoundException, AggregateDeletedException,
      AggregateVersionConflictException {
    // Check Authorization
    checkAuthorization(cmd);

    // Logging
    LOG.info("CancelFlightCommand for order " + cmd.getFlightId() + " issued");

    // Retrieving aggregate (exception handling pending)
    Flight flight = flightRepository.read(cmd.getFlightId());

    // Invoking method of aggregate
    flight.cancel();

    // Persisting new aggregate instance
    flightRepository.update(flight, null);
  }

}