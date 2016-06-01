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

import static org.fest.assertions.Assertions.assertThat;

import java.time.Duration;
import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.lhind.reservation_mgmt.command.flight.exceptions.DuplicateReservationException;
import org.lhind.reservation_mgmt.command.flight.exceptions.FlightCancelledException;
import org.lhind.reservation_mgmt.common.model.flight.Capacity;
import org.lhind.reservation_mgmt.common.model.flight.FlightId;
import org.lhind.reservation_mgmt.common.model.flight.Route;
import org.lhind.reservation_mgmt.common.model.flight.events.ConfirmedReservationCancelledEvent;
import org.lhind.reservation_mgmt.common.model.flight.events.FlightRescheduledEvent;
import org.lhind.reservation_mgmt.common.model.flight.events.ReservationFromWaitlistRemovedEvent;
import org.lhind.reservation_mgmt.common.model.passenger.PassengerId;

/**
 * @author U519643
 *
 */
public class FlightEventHandlerTest {
  // Test values
  private static final FlightId FLIGHT_ID = FlightId
      .valueOf("58e677da-ccbb-47a2-92e3-237af92201d7");

  private static final PassengerId PASSENGER_ID = PassengerId
      .valueOf("63e677bb-657a-92e3-201d-657af92201e5");
  private static final PassengerId PASSENGER_ID_2 = PassengerId
      .valueOf("657af922-cacb-9220-ccbb-237af92201d7");
  private static final PassengerId PASSENGER_ID_3 = PassengerId
      .valueOf("92e377bb-9220-92e3-af92-657af92201e5");

  private static final LocalDateTime DATE = LocalDateTime.of(2020, 10, 22, 14,
      30);
  private static final Duration DURATION = Duration.ofHours(2);
  private static final Capacity CAPACITY = new Capacity(2);

  private Flight flight;
  private Route route;

  @Before
  public void setUp() throws Exception {
    route = new Route("HAM", "FRA");
    flight = new Flight();
    flight.schedule(FLIGHT_ID, DATE, DURATION, route, CAPACITY);

    flight.markChangesAsCommitted();
  }

  @Test
  public void testHandleFlightRescheduledEvent() {
    // Prepare (create an event)
    LocalDateTime newDepartureTime = LocalDateTime.of(2021, 05, 05, 10, 00);
    Duration newDuration = Duration.ofHours(3);
    FlightRescheduledEvent event = new FlightRescheduledEvent(FLIGHT_ID,
        newDepartureTime, newDuration);

    // test (call event handler)
    flight.loadFromHistory(event);

    // verify (check if the values of the aggregate changed according to the
    // event)
    assertThat(flight.getDepartureTime()).isEqualTo(newDepartureTime);
    assertThat(flight.getDuration()).isEqualTo(newDuration);
  }

  @Test
  public void testHandleConfirmedReservationCancelledEvent()
      throws FlightCancelledException, DuplicateReservationException {
    // Prepare
    flight.makeReservation(PASSENGER_ID);
    flight.markChangesAsCommitted();

    ConfirmedReservationCancelledEvent event = new ConfirmedReservationCancelledEvent(
        FLIGHT_ID, PASSENGER_ID);

    // test
    flight.loadFromHistory(event);

    // verify
    assertThat(flight.getConfirmedReservations().size()).isEqualTo(0);
  }

  @Test
  public void testHandleReservationFromWaitlistRemovedEvent()
      throws FlightCancelledException, DuplicateReservationException {
    // Prepare
    flight.makeReservation(PASSENGER_ID);
    flight.makeReservation(PASSENGER_ID_2);
    flight.makeReservation(PASSENGER_ID_3);
    flight.markChangesAsCommitted();

    ReservationFromWaitlistRemovedEvent event = new ReservationFromWaitlistRemovedEvent(
        FLIGHT_ID, PASSENGER_ID_3);

    // test
    flight.loadFromHistory(event);

    // verify
    assertThat(flight.getWaitlist().size()).isEqualTo(0);
  }

  /*
   * Exercise: Write jUnit-Test for
   */
  @Test
  public void testHandleReservationOnWaitlistConfirmedEvent() {
    // Prepare
    // ...

    // test
    // ...

    // verify
    // ...
  }

  /*
   * Test of exceptions omitted
   */
}
