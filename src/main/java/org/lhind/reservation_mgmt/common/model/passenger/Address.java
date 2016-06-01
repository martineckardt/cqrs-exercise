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
package org.lhind.reservation_mgmt.common.model.passenger;

/**
 * @author U519643
 *
 */
public class Address {
  private String street;
  private String number;
  private String zipCode;
  private String city;
  private String countryCode;

  public Address(String street, String number, String zipCode, String city,
      String countryCode) {
    super();
    this.street = street;
    this.number = number;
    this.zipCode = zipCode;
    this.city = city;
    this.countryCode = countryCode;
  }

  /**
   * @return the street
   */
  public String getStreet() {
    return street;
  }

  /**
   * @return the number
   */
  public String getNumber() {
    return number;
  }

  /**
   * @return the zipCode
   */
  public String getZipCode() {
    return zipCode;
  }

  /**
   * @return the city
   */
  public String getCity() {
    return city;
  }

  /**
   * @return the countryCode
   */
  public String getCountryCode() {
    return countryCode;
  }

}
