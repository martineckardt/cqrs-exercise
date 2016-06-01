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

import org.fuin.cqrs4j.AbstractCommand;
import org.lhind.reservation_mgmt.common.model.flight.FlightId;
import org.lhind.reservation_mgmt.common.model.passenger.PassengerId;

/**
 * Make a reservation for a flight with a registered passenger
 */
public class MakeReservationCommand extends AbstractCommand {

  private final FlightId flightId;
  private final PassengerId passengerId;
  private final int quantity;

  public MakeReservationCommand(FlightId flightId, PassengerId passengerId,
      int quantity) {
    super();
    this.flightId = flightId;
    this.passengerId = passengerId;
    this.quantity = quantity;
  }

  /**
   * @return the FLIGHT_ID the passengerId is added to
   */
  public FlightId getFlightId() {
    return flightId;
  }

  /**
   * @return the passengerId
   */
  public PassengerId getReservationDetails() {
    return passengerId;
  }

  /**
   * @return the quantity
   */
  public int getQuantity() {
    return quantity;
  }
}
