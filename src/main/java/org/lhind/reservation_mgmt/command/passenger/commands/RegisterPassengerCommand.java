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
package org.lhind.reservation_mgmt.command.passenger.commands;

import org.fuin.cqrs4j.AbstractCommand;
import org.lhind.reservation_mgmt.common.model.passenger.Address;
import org.lhind.reservation_mgmt.common.model.passenger.Language;
import org.lhind.reservation_mgmt.common.model.passenger.PassengerId;

/**
 * @author U519643
 *
 */
public class RegisterPassengerCommand extends AbstractCommand {

  private PassengerId passengerId;
  private String firstname;
  private String lastname;
  private Address address;
  private Language preferredLanguage;
  private boolean isVegeterian;

  public RegisterPassengerCommand(PassengerId passengerId, String firstname,
      String lastname, Address address, Language preferredLanguage,
      boolean isVegeterian) {
    super();
    this.passengerId = passengerId;
    this.firstname = firstname;
    this.lastname = lastname;
    this.address = address;
    this.preferredLanguage = preferredLanguage;
    this.isVegeterian = isVegeterian;
  }

  /**
   * @return the passengerId
   */
  public PassengerId getPassengerId() {
    return passengerId;
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
   * @return the address
   */
  public Address getAddress() {
    return address;
  }

  /**
   * @return the preferredLanguage
   */
  public Language getPreferredLanguage() {
    return preferredLanguage;
  }

  /**
   * @return the isVegeterian
   */
  public boolean isVegeterian() {
    return isVegeterian;
  }

}
