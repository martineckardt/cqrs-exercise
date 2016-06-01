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
package org.lhind.reservation_mgmt.command.passenger;

import org.fuin.ddd4j.ddd.AggregateDeletedException;
import org.fuin.ddd4j.ddd.AggregateNotFoundException;
import org.fuin.ddd4j.ddd.AggregateVersionConflictException;
import org.lhind.reservation_mgmt.command.AbstractCommandHandler;
import org.lhind.reservation_mgmt.command.passenger.commands.RegisterPassengerCommand;

/**
 * @author U519643
 *
 */
public class PassengerCommandHandler extends AbstractCommandHandler {

  private PassengerRepository passengerRepository;

  public void handle(RegisterPassengerCommand cmd) {
    Passenger passenger = passengerRepository.create();

    passenger.register(cmd.getPassengerId(), cmd.getFirstname(),
        cmd.getLastname(), cmd.getAddress(), cmd.getPreferredLanguage(),
        cmd.isVegeterian());

    try {
      passengerRepository.update(passenger, null);
    } catch (AggregateVersionConflictException | AggregateNotFoundException
        | AggregateDeletedException e) {
      // TODO handle exception
    }
  };

}