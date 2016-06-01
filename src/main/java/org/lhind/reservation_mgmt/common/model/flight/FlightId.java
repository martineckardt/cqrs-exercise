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
package org.lhind.reservation_mgmt.common.model.flight;

import java.util.UUID;
import java.util.regex.Pattern;

import org.apache.commons.lang3.Validate;
import org.fuin.ddd4j.ddd.AggregateRootId;
import org.fuin.ddd4j.ddd.EntityType;
import org.fuin.ddd4j.ddd.StringBasedEntityType;
import org.fuin.objects4j.common.Contract;
import org.fuin.objects4j.common.Nullable;

/**
 * Represents the identifier of an aggregate node.
 *
 */
public class FlightId implements AggregateRootId {

  private static final long serialVersionUID = 1000L;

  /**
   * Type of the referenced aggregate.
   */
  public static final EntityType TYPE = new StringBasedEntityType("Flight");

  protected UUID value;

  /**
   * Constructor with all mandatory data.
   * 
   * @param value
   *          a number equal or greater than 1
   */
  public FlightId(final UUID value) {
    Contract.requireArgNotNull("value", value);
    this.value = value;
  }

  /**
   * Protected default constructor for deserialization.
   */
  protected FlightId() {
    super();
  }

  @Override
  public final String toString() {
    return asString();
  }

  public final String asString() {
    return "" + value;
  }

  public final String asTypedString() {
    return "Flight " + asString();
  }

  public final EntityType getType() {
    return TYPE;
  }

  /**
   * Parses an id.
   * 
   * @param value
   *          Value to convert. A <code>null</code> value returns
   *          <code>null</code>.
   * 
   * @return Converted value.
   */
  @Nullable
  public static FlightId valueOf(final String value) {
    if (value == null) {
      return null;
    }

    // Check
    Validate.isTrue(isValid(value));

    final UUID uuidValue = UUID.fromString(value);
    return new FlightId(uuidValue);
  }

  /**
   * Static factory to retrieve a (pseudo randomly generated) FlightId.
   * 
   * @return random FlightId.
   */
  public static FlightId randomId() {
    return new FlightId(UUID.randomUUID());
  }

  /**
   * Returns the information if a given string is a valid UUID.
   * 
   * @param value
   *          Value to check. A <code>null</code> value returns
   *          <code>true</code>.
   * 
   * @return TRUE if it's a valid key, else FALSE.
   */
  public static boolean isValid(final String value) {
    if (value == null) {
      return true;
    }

    final String uuidPattern = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-"
        + "[0-9a-f]{4}-[0-9a-f]{12}$";

    return Pattern.matches(uuidPattern, value);
  }

  // CHECKSTYLE:OFF generated methods
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((value == null) ? 0 : value.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    FlightId other = (FlightId) obj;
    if (value == null) {
      if (other.value != null)
        return false;
    } else if (!value.equals(other.value))
      return false;
    return true;
  }
  // CHECKSTYLE:ON

}
