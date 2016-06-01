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
package org.lhind.reservation_mgmt;

import java.time.Duration;
import java.time.LocalDateTime;

import org.lhind.reservation_mgmt.common.model.flight.Capacity;
import org.lhind.reservation_mgmt.common.model.flight.FlightId;
import org.lhind.reservation_mgmt.common.model.passenger.PassengerId;

/**
 * @author U519643
 *
 */
public class DummyData {
  public static final FlightId FLIGHT_ID = FlightId
      .valueOf("58e677da-ccbb-47a2-92e3-237af92201d7");
  public static final LocalDateTime DEPARTURE_TIME = LocalDateTime.of(2020, 10,
      22, 14, 30);
  public static final LocalDateTime DEPARTURE_TIME_IN_PAST = LocalDateTime.of(
      2010, 05, 02, 10, 00);
  public static final LocalDateTime ARRIVAL_TIME = LocalDateTime.of(2020, 10,
      22, 16, 30);

  public static final Duration DURATION = Duration.ofHours(2);
  public static final Capacity CAPACITY = new Capacity(2);

  public static final PassengerId PASSENGER_ID_1 = PassengerId
      .valueOf("f4ed5afc-a88b-4e26-a130-73150a6fa439");
  public static final PassengerId PASSENGER_ID_2 = PassengerId
      .valueOf("e0d7dc70-d6aa-46de-8721-2330361ed5b9");
  public static final PassengerId PASSENGER_ID_3 = PassengerId
      .valueOf("a2afced5-8721-a130-4e26-6fa4361ed361");

}
