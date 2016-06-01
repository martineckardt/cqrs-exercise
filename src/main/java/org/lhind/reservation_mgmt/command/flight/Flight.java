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

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import org.fuin.ddd4j.ddd.AbstractAggregateRoot;
import org.fuin.ddd4j.ddd.ApplyEvent;
import org.fuin.ddd4j.ddd.EntityType;
import org.lhind.reservation_mgmt.command.flight.exceptions.DateInPastException;
import org.lhind.reservation_mgmt.command.flight.exceptions.DuplicateReservationException;
import org.lhind.reservation_mgmt.command.flight.exceptions.FlightCancelledException;
import org.lhind.reservation_mgmt.command.flight.exceptions.ReservationDoesNotExistException;
import org.lhind.reservation_mgmt.common.model.flight.Capacity;
import org.lhind.reservation_mgmt.common.model.flight.FlightId;
import org.lhind.reservation_mgmt.common.model.flight.Route;
import org.lhind.reservation_mgmt.common.model.flight.events.ConfirmedReservationCancelledEvent;
import org.lhind.reservation_mgmt.common.model.flight.events.FlightCancelledEvent;
import org.lhind.reservation_mgmt.common.model.flight.events.FlightRescheduledEvent;
import org.lhind.reservation_mgmt.common.model.flight.events.FlightScheduledEvent;
import org.lhind.reservation_mgmt.common.model.flight.events.ReservationConfirmedEvent;
import org.lhind.reservation_mgmt.common.model.flight.events.ReservationFromWaitlistRemovedEvent;
import org.lhind.reservation_mgmt.common.model.flight.events.ReservationOnWaitlistConfirmedEvent;
import org.lhind.reservation_mgmt.common.model.flight.events.ReservationPutOnWaitlistEvent;
import org.lhind.reservation_mgmt.common.model.passenger.PassengerId;

/**
 * <pre>
 * Represents a flight. Contains three types of methods: 
 * - Constructor 
 * - Business Methods that result in events 
 * - Event Handlers that apply the changes represented by an event 
 * - Getters & Setters
 * </pre>
 *
 */
public class Flight extends AbstractAggregateRoot<FlightId> {

  private FlightId flightId;
  private FlightStatus flightStatus;
  private Route route;
  private LocalDateTime departureTime;
  private Duration duration;
  private Capacity capacity;

  private List<PassengerId> confirmedReservations;
  private List<PassengerId> waitlist;

  /**
   * Constructor
   */
  public Flight() {
    flightStatus = FlightStatus.SCHEDULED;
    confirmedReservations = new LinkedList<>();
    waitlist = new LinkedList<>();
  }

  // === BUSINESS METHODS ===

  /**
   * Schedules a flight.
   * 
   * @param id
   * @param DEPARTURE_TIME
   *          of the flight
   * @param route
   *          of the flight
   * @throws DateInPastException
   *           thrown if the DEPARTURE_TIME is in the past.
   */
  public void schedule(FlightId id, LocalDateTime departureTime,
      Duration duration, Route route, Capacity capacity)
      throws DateInPastException {
    validateDepartureTime(departureTime);
    apply(new FlightScheduledEvent(id, departureTime, duration, route, capacity));
  }

  /**
   * Business method to change the departure time and duration of the flight.
   * 
   * @param newDepartureTime
   * @param new Duration
   * @throws DateInPastException
   *           thrown if the DEPARTURE_TIME is in the past
   */
  public void reschedule(LocalDateTime newDepartureTime, Duration newDuration)
      throws DateInPastException {
    validateDepartureTime(newDepartureTime);

    apply(new FlightRescheduledEvent(flightId, newDepartureTime, newDuration));
  }

  /**
   * Business method to cancel the flight
   */
  public void cancel() {
    apply(new FlightCancelledEvent(flightId));
  }

  /**
   * Business method to make a reservation on the flight. If enought seats are
   * avaiable the reservation is confirmed. Otherwise, the passenger is put on
   * the waitlist.
   * 
   * @param passengerId
   *          identifier of the passenger the reservation is made for
   * @throws FlightCancelledException
   *           thrown if the flight has been cancelled
   * @throws DuplicateReservationException
   *           thrown if a reservation for the passenger already exists
   */
  public void makeReservation(PassengerId passengerId)
      throws FlightCancelledException, DuplicateReservationException {

    // Check if flight is cancelled
    if (flightStatus.equals(FlightStatus.CANCELLED)) {
      throw new FlightCancelledException(flightId);
    }

    // Check if passenger already has a reservation
    if (confirmedReservations.contains(passengerId)
        || waitlist.contains(passengerId)) {
      throw new DuplicateReservationException(flightId, passengerId);
    }

    // Check if the CAPACITY is reached
    if (confirmedReservations.size() < capacity.getValue()) {
      // Sufficient seats are available. Confirm Reservation
      apply(new ReservationConfirmedEvent(flightId, passengerId));
    } else {
      // No seats available. Put Reservation on waitlist
      apply(new ReservationPutOnWaitlistEvent(flightId, passengerId));
    }

  }

  /**
   * Business method to cancel a reservation. If the reservation is a confirmed
   * reservation, the reservation is removed and given the waitlist is not empty
   * a reservation from the waitlist is confirmed. If the cancelled reservation
   * is on the waitlist it will simply be removed.
   * 
   * @param passengerId
   *          identifier of the passenger the reservation is for
   * @throws ReservationDoesNotExistException
   *           thrown if the passenger does not have a reservation
   */
  public void cancelReservation(PassengerId passengerId)
      throws ReservationDoesNotExistException {

    /*
     * Exercise: Writing an Aggregate Business Method
     */
  }

  /**
   * Validates a departure time
   * 
   * @param departureTime
   * @throws DateInPastException
   *           thrown if the provided date is in the past.
   */
  private void validateDepartureTime(LocalDateTime departureTime)
      throws DateInPastException {
    if (departureTime.isBefore(LocalDateTime.now())) {
      throw new DateInPastException(flightId, departureTime);
    }
  }

  // === EVENT HANDLERS ===

  @ApplyEvent
  private void handle(final FlightScheduledEvent event) {
    this.flightId = event.getEntityId();
    this.route = event.getRoute();
    this.departureTime = event.getDepartureTime();
    this.capacity = event.getCapacity();
  }

  @ApplyEvent
  private void handle(FlightRescheduledEvent event) {
    this.departureTime = event.getNewDepartureTime();
    this.duration = event.getNewDuration();
  }

  @ApplyEvent
  private void handle(final FlightCancelledEvent event) {
    flightStatus = FlightStatus.CANCELLED;
  }

  @ApplyEvent
  private void handle(final ReservationConfirmedEvent event) {
    confirmedReservations.add(event.getPassengerId());
  }

  @ApplyEvent
  private void handle(final ReservationPutOnWaitlistEvent event) {
    waitlist.add(event.getPassengerId());
  }

  @ApplyEvent
  private void handle(final ConfirmedReservationCancelledEvent event) {
    /*
     * Exercise: Writing Aggregate Event Handler
     */
  }

  @ApplyEvent
  private void handle(final ReservationFromWaitlistRemovedEvent event) {
    /*
     * Exercise: Writing Aggregate Event Handler
     */
  }

  @ApplyEvent
  private void handle(final ReservationOnWaitlistConfirmedEvent event) {
    /*
     * Exercise: Writing Aggregate Event Handler
     */
  }

  // === GETTERS & SETTERS

  /*
   * (non-Javadoc)
   * 
   * @see org.fuin.ddd4j.ddd.AggregateRoot#getId()
   */
  public FlightId getId() {
    return flightId;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.fuin.ddd4j.ddd.Entity#getType()
   */
  public EntityType getType() {
    return FlightId.TYPE;
  }

  /**
   * @return the FLIGHT_ID
   */
  public FlightId getFlightId() {
    return flightId;
  }

  /**
   * @return the flightStatus
   */
  public FlightStatus getFlightStatus() {
    return flightStatus;
  }

  /**
   * @return the confirmedReservations
   */
  public List<PassengerId> getConfirmedReservations() {
    return confirmedReservations;
  }

  /**
   * @return the waitlist
   */
  public List<PassengerId> getWaitlist() {
    return waitlist;
  }

  /**
   * @return the route
   */
  public Route getRoute() {
    return route;
  }

  /**
   * @return the DEPARTURE_TIME
   */
  public LocalDateTime getDepartureTime() {
    return departureTime;
  }

  /**
   * @return
   */
  public Duration getDuration() {
    return duration;
  }

}
