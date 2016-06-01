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

import org.fuin.ddd4j.ddd.AbstractAggregateRoot;
import org.fuin.ddd4j.ddd.ApplyEvent;
import org.fuin.ddd4j.ddd.EntityType;
import org.lhind.reservation_mgmt.common.model.passenger.Address;
import org.lhind.reservation_mgmt.common.model.passenger.Language;
import org.lhind.reservation_mgmt.common.model.passenger.PassengerId;
import org.lhind.reservation_mgmt.common.model.passenger.events.PassengerRegisteredEvent;

/**
 * @author U519643
 *
 */
public class Passenger extends AbstractAggregateRoot<PassengerId> {

  private PassengerId passengerId;
  private String firstname;
  private String lastname;
  private Address address;
  private Language preferredLanguage;
  private boolean isVegeterian;

  public Passenger() {

  }

  public void register(PassengerId passengerId, String firstname,
      String lastname, Address address, Language preferredLanguage,
      boolean isVegeterian) {

    // TODO validate

    apply(new PassengerRegisteredEvent(passengerId, firstname, lastname,
        address, preferredLanguage, isVegeterian));
  }

  /**
   * @return the firstname
   */
  public String getFirstname() {
    return firstname;
  }

  /**
   * @return the lastname
   */
  public String getLastname() {
    return lastname;
  }

  /**
   * @return the isVegeterian
   */
  public boolean isVegeterian() {
    return isVegeterian;
  }

  // EVENT HANDLERS

  @ApplyEvent
  private void handle(PassengerRegisteredEvent event) {
    this.passengerId = event.getEntityId();
    this.firstname = event.getFirstname();
    this.lastname = event.getLastname();
    this.address = event.getAddress();
    this.preferredLanguage = event.getPreferredLanguage();
    this.isVegeterian = event.isVegeterian();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.fuin.ddd4j.ddd.AggregateRoot#getId()
   */
  @Override
  public PassengerId getId() {
    return passengerId;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.fuin.ddd4j.ddd.Entity#getType()
   */
  @Override
  public EntityType getType() {
    return PassengerId.TYPE;
  }

}
