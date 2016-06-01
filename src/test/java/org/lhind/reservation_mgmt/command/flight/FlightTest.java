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
import org.lhind.reservation_mgmt.DummyData;
import org.lhind.reservation_mgmt.command.flight.exceptions.DateInPastException;
import org.lhind.reservation_mgmt.command.flight.exceptions.DuplicateReservationException;
import org.lhind.reservation_mgmt.command.flight.exceptions.FlightCancelledException;
import org.lhind.reservation_mgmt.common.model.flight.Route;
import org.lhind.reservation_mgmt.common.model.flight.events.FlightCancelledEvent;
import org.lhind.reservation_mgmt.common.model.flight.events.FlightRescheduledEvent;
import org.lhind.reservation_mgmt.common.model.flight.events.FlightScheduledEvent;
import org.lhind.reservation_mgmt.common.model.flight.events.ReservationConfirmedEvent;
import org.lhind.reservation_mgmt.common.model.flight.events.ReservationPutOnWaitlistEvent;

/**
 * Test for the {@link org.lhind.reservation_mgmt.command.flight.Flight}
 * aggregate.
 */
public class FlightTest {

  private static Route ROUTE;

  @Before
  public void setUp() throws Exception {
    ROUTE = new Route("HAM", "FRA");
  }

  @Test
  public void testScheduleFlight() throws DateInPastException {
    // Prepare
    Flight flight = new Flight();

    // Test
    flight.schedule(DummyData.FLIGHT_ID, DummyData.DEPARTURE_TIME,
        DummyData.DURATION, ROUTE, DummyData.CAPACITY);

    // verify
    FlightScheduledEvent event = (FlightScheduledEvent) flight
        .getUncommittedChanges().get(0);

    assertThat(event.getEntityId()).isEqualTo(DummyData.FLIGHT_ID);
    assertThat(event.getRoute()).isEqualTo(ROUTE);
    assertThat(event.getDepartureTime()).isEqualTo(DummyData.DEPARTURE_TIME);
    assertThat(event.getCapacity()).isEqualTo(DummyData.CAPACITY);
  }

  @Test(expected = DateInPastException.class)
  public void testScheduleFlightFailsWithDateInPastException()
      throws DateInPastException {
    // Prepare
    Flight flight = new Flight();

    // Test
    flight.schedule(DummyData.FLIGHT_ID, DummyData.DEPARTURE_TIME_IN_PAST,
        DummyData.DURATION, ROUTE, DummyData.CAPACITY);
  }

  @Test
  public void testRescheduleFlightSuccess() throws DateInPastException {
    // Prepare
    Flight flight = new Flight();
    flight.schedule(DummyData.FLIGHT_ID, DummyData.DEPARTURE_TIME,
        DummyData.DURATION, ROUTE, DummyData.CAPACITY);
    flight.markChangesAsCommitted();

    // Test
    LocalDateTime newDate = LocalDateTime.of(2021, 8, 10, 17, 30);
    Duration newDuration = Duration.ofHours(3);

    flight.reschedule(newDate, newDuration);

    // verify
    FlightRescheduledEvent event = (FlightRescheduledEvent) flight
        .getUncommittedChanges().get(0);

    assertThat(event.getEntityId()).isEqualTo(DummyData.FLIGHT_ID);
    assertThat(event.getNewDepartureTime()).isEqualTo(newDate);
    assertThat(event.getNewDuration()).isEqualTo(newDuration);
  }

  @Test(expected = DateInPastException.class)
  public void testRescheduleFlightFailsWithDateInPastException()
      throws DateInPastException {
    // Prepare
    Flight flight = new Flight();
    flight.schedule(DummyData.FLIGHT_ID, DummyData.DEPARTURE_TIME,
        DummyData.DURATION, ROUTE, DummyData.CAPACITY);
    flight.markChangesAsCommitted();
    Duration newDuration = Duration.ofHours(3);

    // Test
    flight.reschedule(DummyData.DEPARTURE_TIME_IN_PAST, newDuration);
  }

  @Test
  public void testCancelFlight() throws DateInPastException {
    // Prepare
    Flight flight = new Flight();
    flight.schedule(DummyData.FLIGHT_ID, DummyData.DEPARTURE_TIME,
        DummyData.DURATION, ROUTE, DummyData.CAPACITY);
    flight.markChangesAsCommitted();

    // Test
    flight.cancel();

    // Verify
    FlightCancelledEvent event = (FlightCancelledEvent) flight
        .getUncommittedChanges().get(0);

    assertThat(event.getEntityId()).isEqualTo(DummyData.FLIGHT_ID);
  }

  @Test
  public void testMakeReservationSuccessConfirmed() throws DateInPastException,
      FlightCancelledException, DuplicateReservationException {
    // Prepare
    Flight flight = new Flight();
    flight.schedule(DummyData.FLIGHT_ID, DummyData.DEPARTURE_TIME,
        DummyData.DURATION, ROUTE, DummyData.CAPACITY);
    flight.markChangesAsCommitted();

    // Test
    flight.makeReservation(DummyData.PASSENGER_ID_1);

    // verify
    ReservationConfirmedEvent event = (ReservationConfirmedEvent) flight
        .getUncommittedChanges().get(0);

    assertThat(event.getEntityId()).isEqualTo(DummyData.FLIGHT_ID);
    assertThat(event.getPassengerId()).isEqualTo(DummyData.PASSENGER_ID_1);
  }

  @Test
  public void testMakeReservationSuccessWaitlist() throws DateInPastException,
      FlightCancelledException, DuplicateReservationException {
    // Prepare
    Flight flight = new Flight();
    flight.schedule(DummyData.FLIGHT_ID, DummyData.DEPARTURE_TIME,
        DummyData.DURATION, ROUTE, DummyData.CAPACITY);
    flight.makeReservation(DummyData.PASSENGER_ID_1);
    flight.makeReservation(DummyData.PASSENGER_ID_2);
    flight.markChangesAsCommitted();

    // Test
    flight.makeReservation(DummyData.PASSENGER_ID_3);

    // verify
    ReservationPutOnWaitlistEvent event = (ReservationPutOnWaitlistEvent) flight
        .getUncommittedChanges().get(0);

    assertThat(event.getEntityId()).isEqualTo(DummyData.FLIGHT_ID);
    assertThat(event.getPassengerId()).isEqualTo(DummyData.PASSENGER_ID_3);
  }

  @Test(expected = FlightCancelledException.class)
  public void testMakeReservationFailsWithFlightCancelledException()
      throws DateInPastException, FlightCancelledException,
      DuplicateReservationException {
    // Prepare
    Flight flight = new Flight();
    flight.schedule(DummyData.FLIGHT_ID, DummyData.DEPARTURE_TIME,
        DummyData.DURATION, ROUTE, DummyData.CAPACITY);
    flight.cancel();
    flight.markChangesAsCommitted();

    // Test
    flight.makeReservation(DummyData.PASSENGER_ID_1);
  }

  @Test(expected = DuplicateReservationException.class)
  public void testMakeReservationFailsWithDuplicateReservationException()
      throws DateInPastException, FlightCancelledException,
      DuplicateReservationException {
    // Prepare
    Flight flight = new Flight();
    flight.schedule(DummyData.FLIGHT_ID, DummyData.DEPARTURE_TIME,
        DummyData.DURATION, ROUTE, DummyData.CAPACITY);
    flight.makeReservation(DummyData.PASSENGER_ID_1);
    flight.markChangesAsCommitted();

    // Test
    flight.makeReservation(DummyData.PASSENGER_ID_1);

  }

}
