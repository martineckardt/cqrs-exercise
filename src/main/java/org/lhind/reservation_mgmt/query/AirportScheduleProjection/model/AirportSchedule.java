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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.lhind.reservation_mgmt.common.model.flight.FlightId;

/**
 * @author U519643
 *
 */
public class AirportSchedule {
  private String airportCode;
  private LocalDate date;

  private List<OutgoingFlight> outgoingFlights;
  private List<IncomingFlight> incomingFlights;

  public AirportSchedule(String airportCode, LocalDate date) {
    this.airportCode = airportCode;
    this.date = date;

    outgoingFlights = new ArrayList<OutgoingFlight>();
    incomingFlights = new ArrayList<IncomingFlight>();
  }

  public void addOutgoingFlight(OutgoingFlight flight) {
    outgoingFlights.add(flight);
  }

  public void addIncomingFlight(IncomingFlight flight) {
    incomingFlights.add(flight);
  }

  public void removeFlight(FlightId flightId) {
    incomingFlights.removeIf(flight -> flight.getFlightId().equals(flightId));
    outgoingFlights.removeIf(flight -> flight.getFlightId().equals(flightId));
  }

}
